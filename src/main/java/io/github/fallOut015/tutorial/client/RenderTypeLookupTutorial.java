package io.github.tutorial.client;

import io.github.fallOut015.tutorial.world.level.block.BlocksTutorial;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RenderTypeLookupTutorial {
    public static void doClientStuff(final FMLClientSetupEvent event) {
	    RenderTypeLookup.setRenderLayer(BlocksTutorial.THICKET.get(), RenderType.cutout());
	    RenderTypeLookup.setRenderLayer(BlocksTutorial.THICKET_PLANT.get(), RenderType.cutout());
	    RenderTypeLookup.setRenderLayer(BlocksTutorial.TOADSTOOL.get(), RenderType.cutout());
	    RenderTypeLookup.setRenderLayer(BlocksTutorial.TWILIGHT_PETAL.get(), RenderType.cutout());
	    RenderTypeLookup.setRenderLayer(BlocksTutorial.RUBY.get(), RenderType.cutout());
	    RenderTypeLookup.setRenderLayer(BlocksTutorial.SPELEOTHEM.get(), RenderType.cutout());
    }
}