package com.lupicus.bk.world;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.lupicus.bk.Main;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;

public class ModVillage
{
	@SuppressWarnings({ "unchecked" })
	public static void updatePools(MinecraftServer server)
	{
		RegistryAccess regs = server.registryAccess();
		Optional<Registry<StructureTemplatePool>> opt = regs.lookup(Registries.TEMPLATE_POOL);
		if (opt.isEmpty())
			return;

		Registry<StructureTemplatePool> reg = opt.get();

		StructureTemplatePool pattern = reg.getValue(ResourceLocation.parse("minecraft:village/plains/houses"));
		if (pattern == null)
			return;

		Function<Projection, LegacySinglePoolElement> funpiece = StructurePoolElement.legacy(Main.MODID + ":village/plains/houses/plains_beekeeper_1");
		StructurePoolElement piece = funpiece.apply(Projection.RIGID);

		try {
			MappingResolver mapper = FabricLoader.getInstance().getMappingResolver();
			String name = mapper.mapFieldName("intermediary", "net.minecraft.class_3785", "field_16680", "Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"); // templates
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
