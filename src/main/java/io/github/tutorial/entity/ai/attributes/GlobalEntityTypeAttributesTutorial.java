package io.github.tutorial.entity.ai.attributes;

import io.github.tutorial.entity.EntityTypeTutorial;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class GlobalEntityTypeAttributesTutorial {
	public static void setup(final FMLCommonSetupEvent event) {
		GlobalEntityTypeAttributes.put(EntityTypeTutorial.UNICORN.get(), AbstractHorseEntity.createBaseHorseAttributes().build());
    }
}