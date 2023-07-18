package com.lupicus.bk.world;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.lupicus.bk.Main;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.LegacySinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePool.Projection;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;

public class ModVillage
{
	@SuppressWarnings({ "unchecked" })
	public static void updatePools(MinecraftServer server)
	{
		DynamicRegistryManager regs = server.getRegistryManager();
		Optional<Registry<StructurePool>> opt = regs.getOptional(RegistryKeys.TEMPLATE_POOL);
		if (opt.isEmpty())
			return;

		Registry<StructurePool> reg = opt.get();

		StructurePool pattern = reg.get(new Identifier("minecraft:village/plains/houses"));
		if (pattern == null)
			return;

		Function<Projection, LegacySinglePoolElement> funpiece = StructurePoolElement.ofLegacySingle(Main.MODID + ":village/plains/houses/plains_beekeeper_1");
		StructurePoolElement piece = funpiece.apply(Projection.RIGID);

		try {
			MappingResolver mapper = FabricLoader.getInstance().getMappingResolver();
			String name = mapper.mapFieldName("intermediary", "net.minecraft.class_3785", "field_16680", "Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"); // elements
			Field field = StructurePool.class.getDeclaredField(name);
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
