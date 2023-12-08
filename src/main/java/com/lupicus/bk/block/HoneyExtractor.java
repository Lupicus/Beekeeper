package com.lupicus.bk.block;

import com.lupicus.bk.tileentity.HoneyExtractorTileEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class HoneyExtractor extends RotateContainerBase
{
	public static final MapCodec<HoneyExtractor> CODEC = simpleCodec(HoneyExtractor::new);

	@Override
	protected MapCodec<HoneyExtractor> codec() {
		return CODEC;
	}

	protected HoneyExtractor(Properties builder) {
		super(builder);
		registerDefaultState(getStateDefinition().any().setValue(HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HoneyExtractorTileEntity(pos, state);
	}
}
