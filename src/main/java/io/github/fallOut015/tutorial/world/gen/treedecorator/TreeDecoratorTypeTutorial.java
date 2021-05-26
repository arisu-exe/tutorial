package io.github.tutorial.world.gen.treedecorator;

import io.github.tutorial.TutorialMod;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeDecoratorTypeTutorial {
	private static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, TutorialMod.MODID);
	
	
	
	public static final RegistryObject<TreeDecoratorType<ToadstoolTreeDecorator>> TOADSTOOL = TREE_DECORATOR_TYPES.register("toadstool", () -> new TreeDecoratorType<ToadstoolTreeDecorator>(ToadstoolTreeDecorator.codec));

	
	
	public static void register(IEventBus bus) {
		TREE_DECORATOR_TYPES.register(bus);
	}
}