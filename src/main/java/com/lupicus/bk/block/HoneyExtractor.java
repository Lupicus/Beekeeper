package com.lupicus.bk.block;

import com.lupicus.bk.tileentity.HoneyExtractorTileEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class HoneyExtractor extends RotateContainerBase
{
	protected HoneyExtractor(FabricBlockSettings builder) {
		super(builder);
		setDefaultState(getStateManager().getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return getDefaultState().with(HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new HoneyExtractorTileEntity(pos, state);
	}
}
