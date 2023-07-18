package com.lupicus.bk.sound;

import com.lupicus.bk.Main;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
	public static final SoundEvent ENTITY_VILLAGER_WORK_BEEKEEPER = create("entity.villager.bk.work_beekeeper");

	private static SoundEvent create(String key)
	{
		Identifier res = new Identifier(Main.MODID, key);
		SoundEvent ret = SoundEvent.of(res);
		return ret;
	}

	public static void register()
	{
		Registry.register(Registries.SOUND_EVENT, ENTITY_VILLAGER_WORK_BEEKEEPER.getId(), ENTITY_VILLAGER_WORK_BEEKEEPER);
	}
}
