package minersstudios.whomine.item.items;

import java.util.Collection;

import minersstudios.whomine.item.ModItemsRegistry;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class WrenchItem extends Item {
    public WrenchItem(Item.Settings settings) {
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
        Collection<Property<?>> collection = stateManager.getProperties();
        String string = Registries.BLOCK.getId(block).toString();
        if (collection.isEmpty()) {
            sendMessage(player, Text.translatable(this.getTranslationKey() + ".empty", string));
            return false;
        } else {
            NbtCompound nbtCompound = stack.getOrCreateSubNbt("DebugProperty");
            String string2 = nbtCompound.getString(string);
            Property<?> property = stateManager.getProperty(string2);
            if (update) {
                if (property == null) {
                    property = collection.iterator().next();
                }

                BlockState blockState = cycle(state, property, player.shouldCancelInteraction());
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
