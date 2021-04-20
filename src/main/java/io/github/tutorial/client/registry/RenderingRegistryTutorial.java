package io.github.tutorial.client.registry;

import io.github.tutorial.client.renderer.entity.UnicornRenderer;
import io.github.tutorial.entity.EntityTypeTutorial;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RenderingRegistryTutorial {
    public static void doClientStuff(final FMLClientSetupEvent event) {
    	RenderingRegistry.registerEntityRenderingHandler(EntityTypeTutorial.UNICORN.get(), UnicornRenderer::new);
    }
}