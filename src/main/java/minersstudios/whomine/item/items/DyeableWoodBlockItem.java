package minersstudios.whomine.item.items;

import minersstudios.whomine.block.ModProperties;
import minersstudios.whomine.block.properties.WoodType;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Iterator;

public class DyeableWoodBlockItem extends BlockItem implements DyeableItem {
    private final WoodType woodType;
    public DyeableWoodBlockItem(Block block, Item.Settings settings, WoodType woodType) {
        super(block, settings);
        this.woodType = woodType;
    }

    public boolean isPainted(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("BlockEntityTag") == null ? stack.getSubNbt("display") : DyeableBlockItem.getBlockEntityNbt(stack).getCompound("tag").getCompound("display");
        return nbtCompound != null && nbtCompound.contains("color", 99);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("BlockEntityTag") == null ? stack.getSubNbt("display") : DyeableBlockItem.getBlockEntityNbt(stack).getCompound("tag").getCompound("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
    }

    @Override
    public String getTranslationKey() {
        return this.getOrCreateTranslationKey();
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        if (!this.getBlock().isEnabled(context.getWorld().getEnabledFeatures())) {
            return ActionResult.FAIL;
        } else if (!context.canPlace()) {
            return ActionResult.FAIL;
        } else {
            ItemPlacementContext itemPlacementContext = this.getPlacementContext(context);
            BlockState state = this.getPlacementState(itemPlacementContext);
            if (itemPlacementContext == null || state == null) {
                return ActionResult.FAIL;
            } else {
                BlockState blockState = state.with(ModProperties.WOOD_TYPE, this.woodType);

                if (blockState == null) {
                    return ActionResult.FAIL;
                } else if (!this.place(itemPlacementContext, blockState)) {
                    return ActionResult.FAIL;
                } else {
                    BlockPos blockPos = itemPlacementContext.getBlockPos();
                    World world = itemPlacementContext.getWorld();
                    PlayerEntity playerEntity = itemPlacementContext.getPlayer();
                    ItemStack itemStack = itemPlacementContext.getStack();
                    BlockState blockState2 = world.getBlockState(blockPos);

                    if (blockState2.isOf(blockState.getBlock())) {
                        blockState2 = this.placeFromNbt(blockPos, world, itemStack, blockState2);
                        this.postPlacement(blockPos, world, playerEntity, itemStack, blockState2);
                        blockState2.getBlock().onPlaced(world, blockPos, blockState2, playerEntity, itemStack);
                        if (playerEntity instanceof ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
                        }
                    }

                    BlockSoundGroup blockSoundGroup = blockState2.getSoundGroup();
                    world.playSound(playerEntity, blockPos, this.getPlaceSound(blockState2), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
                    world.emitGameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Emitter.of(playerEntity, blockState2));
                    if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    return ActionResult.success(world.isClient);
                }
            }
        }
    }

    private BlockState placeFromNbt(BlockPos pos, World world, ItemStack stack, BlockState state) {
        BlockState blockState = state;
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null) {
            NbtCompound nbtCompound2 = nbtCompound.getCompound("BlockStateTag");
            StateManager<Block, BlockState> stateManager = state.getBlock().getStateManager();
            Iterator var9 = nbtCompound2.getKeys().iterator();

            while(var9.hasNext()) {
                String string = (String)var9.next();
                Property<?> property = stateManager.getProperty(string);
                if (property != null) {
                    String string2 = nbtCompound2.get(string).asString();
                    blockState = with(blockState, property, string2);
                }
            }
        }

        if (blockState != state) {
            world.setBlockState(pos, blockState, 2);
        }

        return blockState;
    }

    private static <T extends Comparable<T>> BlockState with(BlockState state, Property<T> property, String name) {
        return property.parse(name).map((value) -> state.with(property, value)).orElse(state);
    }
}