package com.lupicus.bk.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class HoneyExtractorTileEntity extends BlockEntity
{
	public HoneyExtractorTileEntity(BlockPos pos, BlockState state) {
		super(ModTileEntities.HONEY_EXTRACTOR, pos, state);
	}
}
