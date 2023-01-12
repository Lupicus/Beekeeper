package com.lupicus.bk.world;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.lupicus.bk.Main;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;
import net.minecraftforge.coremod.api.ASMAPI;

public class ModVillage
{
	@SuppressWarnings({ "unchecked" })
	public static void updatePools(MinecraftServer server)
	{
		RegistryAccess regs = server.registryAccess();
		Optional<Registry<StructureTemplatePool>> opt = regs.registry(Registries.TEMPLATE_POOL);
		if (opt.isEmpty())
			return;

		Registry<StructureTemplatePool> reg = opt.get();

		StructureTemplatePool pattern = reg.get(new ResourceLocation("minecraft:village/plains/houses"));
		if (pattern == null)
			return;

		Function<Projection, LegacySinglePoolElement> funpiece = StructurePoolElement.legacy(Main.MODID + ":village/plains/houses/plains_beekeeper_1");
		StructurePoolElement piece = funpiece.apply(Projection.RIGID);

		try {
			String name = ASMAPI.mapField("f_210560_"); // templates
			Field field = StructureTemplatePool.class.getDeclaredField(name);
			field.setAccessible(true);

			List<StructurePoolElement> list = (List<StructurePoolElement>) field.get(pattern);
			for (int i = 0; i < 4; ++i)
				list.add(piece);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
