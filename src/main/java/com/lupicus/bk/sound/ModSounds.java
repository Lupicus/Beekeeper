package com.lupicus.bk.sound;

import com.lupicus.bk.Main;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds
{
	public static final SoundEvent ENTITY_VILLAGER_WORK_BEEKEEPER = create("entity.villager.bk.work_beekeeper");

	private static SoundEvent create(String key)
	{
		ResourceLocation res = ResourceLocation.fromNamespaceAndPath(Main.MODID, key);
		SoundEvent ret = SoundEvent.createVariableRangeEvent(res);
		return ret;
	}

	public static void register()
	{
		Registry.register(BuiltInRegistries.SOUND_EVENT, ENTITY_VILLAGER_WORK_BEEKEEPER.location(), ENTITY_VILLAGER_WORK_BEEKEEPER);
	}
}
