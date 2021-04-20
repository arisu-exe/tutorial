package io.github.tutorial;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import io.github.tutorial.block.BlocksTutorial;
import io.github.tutorial.client.RenderTypeLookupTutorial;
import io.github.tutorial.client.registry.RenderingRegistryTutorial;
import io.github.tutorial.entity.EntityTypeTutorial;
import io.github.tutorial.entity.ai.attributes.GlobalEntityTypeAttributesTutorial;
import io.github.tutorial.item.ItemsTutorial;
import io.github.tutorial.server.CrawlPacketHandler;
import io.github.tutorial.server.PacketHandler;
import io.github.tutorial.util.SoundEventsTutorial;
import io.github.tutorial.world.gen.feature.FeatureTutorial;
import io.github.tutorial.world.gen.feature.FeaturesTutorial;
import io.github.tutorial.world.gen.placement.PlacementTutorial;
import io.github.tutorial.world.gen.treedecorator.TreeDecoratorTypeTutorial;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TutorialMod.MODID)
public class TutorialMod {
	public static final String MODID = "tutorial";
    public static final Logger LOGGER = LogManager.getLogger();

    public TutorialMod() {
    	BlocksTutorial.register(FMLJavaModLoadingContext.get().getModEventBus());
    	ItemsTutorial.register(FMLJavaModLoadingContext.get().getModEventBus());
    	EntityTypeTutorial.register(FMLJavaModLoadingContext.get().getModEventBus());
    	FeatureTutorial.register(FMLJavaModLoadingContext.get().getModEventBus());
    	PlacementTutorial.register(FMLJavaModLoadingContext.get().getModEventBus());
    	SoundEventsTutorial.register(FMLJavaModLoadingContext.get().getModEventBus());
    	TreeDecoratorTypeTutorial.register(FMLJavaModLoadingContext.get().getModEventBus());
    	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	PacketHandler.setup(event);
    	GlobalEntityTypeAttributesTutorial.setup(event);
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
    	RenderTypeLookupTutorial.doClientStuff(event);
    	RenderingRegistryTutorial.doClientStuff(event);
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
    }
    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent // TODO make other methods subscribed
    public void onServerStarting(FMLServerStartingEvent event) {
    }
    
    @Mod.EventBusSubscriber
    public static class Events {
    	@SubscribeEvent
    	@OnlyIn(Dist.CLIENT)
    	public static void onKeyInput(final InputEvent.KeyInputEvent inputEvent$KeyInputEvent) {
    		if(inputEvent$KeyInputEvent.getKey() == GLFW.GLFW_KEY_LEFT_ALT && inputEvent$KeyInputEvent.getAction() == GLFW.GLFW_PRESS) {
    			TutorialMod.LOGGER.debug("Sending crawl packet to server (press)");
    			PacketHandler.INSTANCE.sendToServer(new CrawlPacketHandler(true));
    		}
    		if(inputEvent$KeyInputEvent.getKey() == GLFW.GLFW_KEY_LEFT_ALT && inputEvent$KeyInputEvent.getAction() == GLFW.GLFW_RELEASE) {
    			TutorialMod.LOGGER.debug("Sending crawl packet to server (release)");
    			PacketHandler.INSTANCE.sendToServer(new CrawlPacketHandler(false));
    		}
    	}
    	
    	@SubscribeEvent
    	public static void onBiomeLoad(final BiomeLoadingEvent biomeLoadingEvent) {
    		if(biomeLoadingEvent.getCategory() == Category.FOREST) {
    			biomeLoadingEvent.getGeneration().getFeatures(Decoration.VEGETAL_DECORATION).add(() -> FeaturesTutorial.THICKET.chance(1));
    			biomeLoadingEvent.getGeneration().getFeatures(Decoration.VEGETAL_DECORATION).add(() -> FeaturesTutorial.BIRCH_TOADSTOOLS);
    			biomeLoadingEvent.getGeneration().getFeatures(Decoration.VEGETAL_DECORATION).add(() -> FeaturesTutorial.OAK_TOADSTOOLS);
    		}
    		if(biomeLoadingEvent.getName() == Biomes.BIRCH_FOREST.getRegistryName()) {
    			biomeLoadingEvent.getGeneration().getFeatures(Decoration.VEGETAL_DECORATION).removeIf(supplier -> supplier.get() == Features.TREES_BIRCH);
    		}
    	}
    }
}