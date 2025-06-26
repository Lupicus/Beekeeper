package com.lupicus.bk;

import org.jetbrains.annotations.NotNull;

import com.lupicus.bk.block.ModBlocks;
import com.lupicus.bk.entity.ModProfessions;
import com.lupicus.bk.item.ModItems;
import com.lupicus.bk.sound.ModSounds;
import com.lupicus.bk.tileentity.ModTileEntities;
import com.lupicus.bk.village.ModPOI;
import com.lupicus.bk.world.ModVillage;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "bk";

    public Main(FMLJavaModLoadingContext context)
    {
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents
    {
	    @SubscribeEvent
	    public static void onRegister(final RegisterEvent event)
	    {
	    	@NotNull
			ResourceKey<? extends Registry<?>> key = event.getRegistryKey();
	    	if (key.equals(ForgeRegistries.Keys.BLOCKS))
	    		ModBlocks.register(event.getForgeRegistry());
	    	else if (key.equals(ForgeRegistries.Keys.ITEMS))
	    		ModItems.register(event.getForgeRegistry());
	    	else if (key.equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES))
	    		ModTileEntities.register(event.getForgeRegistry());
	    	else if (key.equals(ForgeRegistries.Keys.VILLAGER_PROFESSIONS))
	    		ModProfessions.register(event.getForgeRegistry());
	    	else if (key.equals(ForgeRegistries.Keys.POI_TYPES))
	    		ModPOI.register(event.getForgeRegistry());
	    	else if (key.equals(ForgeRegistries.Keys.SOUND_EVENTS))
	    		ModSounds.register(event.getForgeRegistry());
	    }

	    @SubscribeEvent
	    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event)
	    {
	    	ModItems.setupTabs(event);
	    }
    }

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeEvents
	{
		@SubscribeEvent
		public static void onStart(ServerAboutToStartEvent event)
		{
			ModVillage.updatePools(event.getServer());
		}
	}
}
