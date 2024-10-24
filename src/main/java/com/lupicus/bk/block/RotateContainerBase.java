package com.lupicus.bk.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

/** allows Container blocks to rotate when part of a Structure */
public abstract class RotateContainerBase extends BaseEntityBlock
{
	public static final EnumProperty<Direction> HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

	protected RotateContainerBase(Properties builder) {
		super(builder);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(HORIZONTAL_FACING, rot.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}
}
