package com.lupicus.bk;

import com.lupicus.bk.block.ModBlocks;
import com.lupicus.bk.entity.ModProfessions;
import com.lupicus.bk.item.ModItems;
import com.lupicus.bk.sound.ModSounds;
import com.lupicus.bk.tileentity.ModTileEntities;
import com.lupicus.bk.world.ModVillage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class Main implements ModInitializer
{
    public static final String MODID = "bk";

	@Override
	public void onInitialize()
	{
		ModSounds.register();
		ModBlocks.register();
		ModItems.register();
		ModTileEntities.register();
		ModProfessions.register();
		ModItems.setupTabs();
		ServerLifecycleEvents.SERVER_STARTING.register((s) -> ModVillage.updatePools(s));
	}
}
