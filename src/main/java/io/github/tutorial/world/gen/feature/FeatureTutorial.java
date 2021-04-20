package io.github.tutorial.world.gen.feature;

import io.github.tutorial.TutorialMod;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class FeatureTutorial {
	private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TutorialMod.MODID);
	
	
	
	public static final RegistryObject<Feature<NoFeatureConfig>> THICKET = FEATURES.register("thicket", () -> new ThicketFeature(NoFeatureConfig.CODEC));

	
	
	public static void register(IEventBus bus) {
		FEATURES.register(bus);
	}
}