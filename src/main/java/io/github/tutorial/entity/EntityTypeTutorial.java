package io.github.tutorial.entity;

import io.github.tutorial.TutorialMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeTutorial {
	private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TutorialMod.MODID);
	
	
	
	public static final RegistryObject<EntityType<UnicornEntity>> UNICORN = ENTITIES.register("unicorn", () -> {
		return EntityType.Builder.of(
			UnicornEntity::new, EntityClassification.CREATURE
		).sized(1.3964844F, 1.6F).setTrackingRange(10).build("unicorn");
	});
	
	
	
	public static void register(IEventBus bus) {
		ENTITIES.register(bus);
	}
}