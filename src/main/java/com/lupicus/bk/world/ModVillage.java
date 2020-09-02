package com.lupicus.bk.world;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;

import com.lupicus.bk.Main;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern.PlacementBehaviour;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.LegacySingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.PlainsVillagePools;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraftforge.coremod.api.ASMAPI;

public class ModVillage
{
	@SuppressWarnings({ "unchecked" })
	public static void updatePools()
	{
		PlainsVillagePools.init();

		JigsawPatternRegistry.func_244094_a(
				new JigsawPattern(new ResourceLocation(Main.MODID + ":village/common/bee"), new ResourceLocation("empty"),
						ImmutableList.of(Pair.of(JigsawPiece.func_242849_a(Main.MODID + ":village/common/animals/bee_1"), 1)),
						JigsawPattern.PlacementBehaviour.RIGID));

        JigsawPattern pattern = WorldGenRegistries.field_243656_h.getOrDefault(new ResourceLocation("minecraft:village/plains/houses"));
        if (pattern == null)
        	return;

		Function<PlacementBehaviour, LegacySingleJigsawPiece> funpiece = JigsawPiece.func_242851_a(Main.MODID + ":village/plains/houses/plains_beekeeper_1", ProcessorLists.field_244107_g);
        JigsawPiece piece = funpiece.apply(PlacementBehaviour.RIGID);

		try {
			String name = ASMAPI.mapField("field_214953_e"); // jigsawPieces
			Field field = JigsawPattern.class.getDeclaredField(name);
			field.setAccessible(true);
			String name2 = ASMAPI.mapField("field_214952_d"); // rawTemplates
			Field field2 = JigsawPattern.class.getDeclaredField(name2);
			field2.setAccessible(true);

			List<JigsawPiece> list = (List<JigsawPiece>) field.get(pattern);
			int n = 4;
			for (int i = 0; i < n; ++i)
				list.add(piece);
			List<Pair<JigsawPiece, Integer>> list2 = (List<Pair<JigsawPiece, Integer>>) field2.get(pattern);
			list2.add(Pair.of(piece, n));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
