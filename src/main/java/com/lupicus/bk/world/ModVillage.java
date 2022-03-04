package com.lupicus.bk.world;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;

import com.lupicus.bk.Main;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;
import net.minecraftforge.coremod.api.ASMAPI;

public class ModVillage
{
	@SuppressWarnings({ "unchecked" })
	public static void updatePools()
	{
		PlainVillagePools.bootstrap();

		Pools.register(
				new StructureTemplatePool(new ResourceLocation(Main.MODID + ":village/common/bee"), new ResourceLocation("empty"),
						ImmutableList.of(Pair.of(StructurePoolElement.legacy(Main.MODID + ":village/common/animals/bee_1"), 1)),
						StructureTemplatePool.Projection.RIGID));

		StructureTemplatePool pattern = BuiltinRegistries.TEMPLATE_POOL.get(new ResourceLocation("minecraft:village/plains/houses"));
		if (pattern == null)
			return;

		Function<Projection, LegacySinglePoolElement> funpiece = StructurePoolElement.legacy(Main.MODID + ":village/plains/houses/plains_beekeeper_1", ProcessorLists.MOSSIFY_10_PERCENT);
		StructurePoolElement piece = funpiece.apply(Projection.RIGID);

		try {
			String name = ASMAPI.mapField("f_210560_"); // templates
			Field field = StructureTemplatePool.class.getDeclaredField(name);
			field.setAccessible(true);
			String name2 = ASMAPI.mapField("f_210559_"); // rawTemplates
			Field field2 = StructureTemplatePool.class.getDeclaredField(name2);
			field2.setAccessible(true);

			List<StructurePoolElement> list = (List<StructurePoolElement>) field.get(pattern);
			int n = 4;
			for (int i = 0; i < n; ++i)
				list.add(piece);
			List<Pair<StructurePoolElement, Integer>> list2 = (List<Pair<StructurePoolElement, Integer>>) field2.get(pattern);
			list2.add(Pair.of(piece, n));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
