package com.lupicus.bk.sound;

import com.lupicus.bk.Main;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModSounds
{
	public static final SoundEvent ENTITY_VILLAGER_WORK_BEEKEEPER = create("entity.villager.bk.work_beekeeper");

	private static SoundEvent create(String key)
	{
		ResourceLocation res = new ResourceLocation(Main.MODID, key);
		SoundEvent ret = new SoundEvent(res);
		ret.setRegistryName(res);
		return ret;
	}

	public static void register(IForgeRegistry<SoundEvent> registry)
	{
		registry.register(ENTITY_VILLAGER_WORK_BEEKEEPER);
	}
}
