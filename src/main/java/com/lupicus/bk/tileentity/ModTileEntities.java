package com.lupicus.bk.tileentity;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModTileEntities
{
	public static final BlockEntityType<HoneyExtractorTileEntity> HONEY_EXTRACTOR = FabricBlockEntityTypeBuilder.create(HoneyExtractorTileEntity::new, ModBlocks.HONEY_EXTRACTOR).build();

	public static void register()
	{
		register("honey_extractor", HONEY_EXTRACTOR);
	}

	private static void register(String name, BlockEntityType<?> type)
	{
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MODID, name), type);
	}
}
