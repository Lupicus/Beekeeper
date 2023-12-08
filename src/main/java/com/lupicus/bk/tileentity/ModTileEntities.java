package com.lupicus.bk.tileentity;

import com.lupicus.bk.block.ModBlocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.IForgeRegistry;

public class ModTileEntities
{
	public static final BlockEntityType<HoneyExtractorTileEntity> HONEY_EXTRACTOR = BlockEntityType.Builder.of(HoneyExtractorTileEntity::new, ModBlocks.HONEY_EXTRACTOR).build(null);

	public static void register(IForgeRegistry<BlockEntityType<?>> forgeRegistry)
	{
		forgeRegistry.register("honey_extractor", HONEY_EXTRACTOR);
	}
}
