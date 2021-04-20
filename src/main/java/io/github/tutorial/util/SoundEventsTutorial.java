package io.github.tutorial.util;

import io.github.tutorial.TutorialMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEventsTutorial {
	private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MODID);
	
	
	
	public static final RegistryObject<SoundEvent> ITEM_ARMOR_EQUIP_DRACONIC = SOUND_EVENTS.register("item.armor.equip_draconic", () -> new SoundEvent(new ResourceLocation("item.armor.equip_draconic")));

	
	
	public static void register(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}