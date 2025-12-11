package com.lupicus.bk.sound;

import com.lupicus.bk.Main;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModSounds
{
	public static final SoundEvent ENTITY_VILLAGER_WORK_BEEKEEPER = create("entity.villager.bk.work_beekeeper");

	private static SoundEvent create(String key)
	{
		Identifier res = Identifier.fromNamespaceAndPath(Main.MODID, key);
		SoundEvent ret = SoundEvent.createVariableRangeEvent(res);
		return ret;
	}

	public static void register(IForgeRegistry<SoundEvent> registry)
	{
		registry.register(ENTITY_VILLAGER_WORK_BEEKEEPER.location(), ENTITY_VILLAGER_WORK_BEEKEEPER);
	}
}
