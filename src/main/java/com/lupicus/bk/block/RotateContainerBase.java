package com.lupicus.bk.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;

/** allows Container blocks to rotate when part of a Structure */
public abstract class RotateContainerBase extends BlockWithEntity
{
    public static final DirectionProperty HORIZONTAL_FACING = HorizontalFacingBlock.FACING;

	protected RotateContainerBase(FabricBlockSettings builder) {
		super(builder);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rot) {
		return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.get(HORIZONTAL_FACING)));
	}
}
