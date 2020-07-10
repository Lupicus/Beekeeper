package com.lupicus.bk.world;

import java.lang.reflect.Field;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.LegacySingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.PlainsVillagePools;
import net.minecraftforge.coremod.api.ASMAPI;

public class ModVillage
{
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void updatePools()
	{
		PlainsVillagePools.init();

		JigsawManager.REGISTRY.register(
				new JigsawPattern(new ResourceLocation("bk:village/common/bee"), new ResourceLocation("empty"),
						ImmutableList.of(new Pair<>(new LegacySingleJigsawPiece("bk:village/common/animals/bee_1"), 1)),
						JigsawPattern.PlacementBehaviour.RIGID));

		LegacySingleJigsawPiece piece = new LegacySingleJigsawPiece("bk:village/plains/houses/plains_beekeeper_1");

		JigsawPattern pattern = JigsawManager.REGISTRY.get(new ResourceLocation("minecraft:village/plains/houses"));
		try {
			String name = ASMAPI.mapField("field_214953_e"); // jigsawPieces
			Field field = JigsawPattern.class.getDeclaredField(name);
			field.setAccessible(true);
			List<JigsawPiece> list = (List<JigsawPiece>) field.get(pattern);
			for (int i = 0; i < 4; ++i)
				list.add(piece);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
