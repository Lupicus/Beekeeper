package com.lupicus.bk.tileentity;

import java.util.Set;

import com.lupicus.bk.block.ModBlocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.IForgeRegistry;

public class ModTileEntities
{
	public static final BlockEntityType<HoneyExtractorTileEntity> HONEY_EXTRACTOR = new BlockEntityType<>(HoneyExtractorTileEntity::new, Set.of(ModBlocks.HONEY_EXTRACTOR));

	public static void register(IForgeRegistry<BlockEntityType<?>> forgeRegistry)
	{
		forgeRegistry.register("honey_extractor", HONEY_EXTRACTOR);
	}
}
