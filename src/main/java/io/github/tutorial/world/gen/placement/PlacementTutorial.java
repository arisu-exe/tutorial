package io.github.tutorial.world.gen.placement;

import io.github.tutorial.TutorialMod;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PlacementTutorial {
	private static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, TutorialMod.MODID);
	
	
	
	public static final RegistryObject<Placement<NoPlacementConfig>> RING = DECORATORS.register("ring", () -> new RingPlacement(NoPlacementConfig.CODEC));

	
	
	public static void register(IEventBus bus) {
		DECORATORS.register(bus);
	}
}
