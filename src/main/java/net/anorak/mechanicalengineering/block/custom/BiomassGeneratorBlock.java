package net.anorak.mechanicalengineering.block.custom;

import net.anorak.mechanicalengineering.block.entity.BiomassGeneratorBlockEntity;
import net.anorak.mechanicalengineering.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class BiomassGeneratorBlock extends BaseEntityBlock {
    public BiomassGeneratorBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public RenderShape getRenderShape(@Nullable BlockState state) {
        return RenderShape.MODEL;
    }

    // one can add "@Nullable" before syntax like "Level pLevel" to get rid of the warning, like in line 36 it's not needed though
    @Override
    public void onRemove(BlockState pState,@Nullable Level pLevel,@Nullable BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            assert pLevel != null;
            assert pPos != null;
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof BiomassGeneratorBlockEntity) {
                ((BiomassGeneratorBlockEntity) blockEntity).drops();
            }
        }
        assert pLevel != null;
        assert pPos != null;
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(@Nullable BlockState state, Level level,
                                 @Nullable BlockPos pos, @Nullable Player player,
                                 @Nullable InteractionHand hand, @Nullable BlockHitResult hit) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer && pos != null) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof BiomassGeneratorBlockEntity blockEntity) {
                NetworkHooks.openScreen(serverPlayer, blockEntity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing! BlockEntity: " + be);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }



    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nullable BlockPos pPos,@Nullable BlockState pState) {
        return new BiomassGeneratorBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel,@Nullable BlockState pState,@Nullable BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntities.BIOMASS_GENERATOR_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}