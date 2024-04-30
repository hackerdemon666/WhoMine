package minersstudios.whomine.item.items;

import minersstudios.whomine.block.ModProperties;
import minersstudios.whomine.item.ModItemsRegistry;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class WrenchItem extends Item {
    private static final ArrayList<Property> blockedProperties = new ArrayList<>(Arrays.asList(
            Properties.WATERLOGGED,
            Properties.CANDLES,
            Properties.EGGS,
            Properties.PICKLES,
            Properties.FLOWER_AMOUNT,
            Properties.LAYERS,
            Properties.LEVEL_8,
            Properties.AGE_1,
            Properties.AGE_2,
            Properties.AGE_3,
            Properties.AGE_4,
            Properties.AGE_5,
            Properties.AGE_7,
            Properties.AGE_15,
            Properties.AGE_25,
            Properties.STAGE,
            Properties.BERRIES,
            Properties.HONEY_LEVEL,
            Properties.DOUBLE_BLOCK_HALF,
            Properties.BED_PART,
            Properties.OCCUPIED,
            Properties.EYE,
            Properties.MOISTURE,
            Properties.CAN_SUMMON,
            Properties.CHARGES,
            Properties.LIT,
            Properties.TRIAL_SPAWNER_STATE,
            ConnectingBlock.FACING_PROPERTIES.get(Direction.NORTH),
            ConnectingBlock.FACING_PROPERTIES.get(Direction.SOUTH),
            ConnectingBlock.FACING_PROPERTIES.get(Direction.EAST),
            ConnectingBlock.FACING_PROPERTIES.get(Direction.WEST),
            ConnectingBlock.FACING_PROPERTIES.get(Direction.UP),
            ConnectingBlock.FACING_PROPERTIES.get(Direction.DOWN),
            ModProperties.WOOD_TYPE
    ));

    public WrenchItem(Settings settings) {
        super(settings);
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.getItem() instanceof WrenchItem) {
                if (!world.isClient && !world.getBlockState(pos).isAir() && !player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
                    ((WrenchItem) ModItemsRegistry.WRENCH).use(player, world.getBlockState(pos), world, pos, false, player.getStackInHand(Hand.MAIN_HAND));
                    player.getItemCooldownManager().set(ModItemsRegistry.WRENCH, 4);
                }
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        if (!world.isClient && playerEntity != null) {
            BlockPos blockPos = context.getBlockPos();
            if (!this.use(playerEntity, world.getBlockState(blockPos), world, blockPos, true, context.getStack())) {
                return ActionResult.FAIL;
            }
        }

        return ActionResult.success(world.isClient);
    }


    private boolean use(PlayerEntity player, BlockState state, WorldAccess world, BlockPos pos, boolean update, ItemStack stack) {
        Block block = state.getBlock();
        StateManager<Block, BlockState> stateManager = block.getStateManager();

        Collection<Property<?>> collection = new ArrayList<>(stateManager.getProperties());
        collection.removeIf(property -> property == null || blockedProperties.contains(property));

        String string = Registries.BLOCK.getId(block).toString();
        if (collection.isEmpty()) {
            sendMessage(player, Text.translatable(this.getTranslationKey() + ".empty", string));
            return false;
        } else {
            NbtCompound nbtCompound = stack.getOrCreateSubNbt("DebugProperty");
            String string2 = nbtCompound.getString(string);
            Property<?> property = stateManager.getProperty(string2);

            if (property != null && blockedProperties.contains(property)) property = null;

            if (update) {
                if (property == null) {
                    property = collection.iterator().next();
                }
                BlockState blockState = cycle(state, property, player.shouldCancelInteraction());
                if (blockState.contains(Properties.SLAB_TYPE) && blockState.get(Properties.SLAB_TYPE) == SlabType.DOUBLE) blockState = blockState.cycle(property);

                world.setBlockState(pos, blockState, 18);
                sendMessage(player, Text.translatable(this.getTranslationKey() + ".update", property.getName(), getValueString(blockState, property)));
            } else {
                property = (Property)cycle((Iterable)collection, (Object)property, player.shouldCancelInteraction());
                String string3 = property.getName();
                nbtCompound.putString(string, string3);
                sendMessage(player, Text.translatable(this.getTranslationKey() + ".select", string3, getValueString(state, property)));
            }

            return true;
        }
    }


    private static <T extends Comparable<T>> BlockState cycle(BlockState state, Property<T> property, boolean inverse) {
        return state.with(property, cycle(property.getValues(), state.get(property), inverse));
    }

    private static <T> T cycle(Iterable<T> elements, @Nullable T current, boolean inverse) {
        return inverse ? Util.previous(elements, current) : Util.next(elements, current);
    }

    private static void sendMessage(PlayerEntity player, Text message) {
        ((ServerPlayerEntity)player).sendMessageToClient(message, true);
    }

    private static <T extends Comparable<T>> String getValueString(BlockState state, Property<T> property) {
        return property.name(state.get(property));
    }
}