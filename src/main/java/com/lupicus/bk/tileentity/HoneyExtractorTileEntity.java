package com.lupicus.bk.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HoneyExtractorTileEntity extends BlockEntity
{
	public HoneyExtractorTileEntity(BlockPos pos, BlockState state) {
		super(ModTileEntities.HONEY_EXTRACTOR, pos, state);
	}
}
