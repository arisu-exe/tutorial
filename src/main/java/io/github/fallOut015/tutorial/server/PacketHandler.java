package io.github.tutorial.server;

import io.github.tutorial.TutorialMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public abstract class PacketHandler {
	private static int ids;
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
	    new ResourceLocation(TutorialMod.MODID, "main"),
	    () -> PROTOCOL_VERSION,
	    PROTOCOL_VERSION::equals,
	    PROTOCOL_VERSION::equals
	);
	
	final int id;
	
	protected PacketHandler(int id) {
		this.id = id;
	}
	
	public static int getNewID() {
		return ids++;
	}
    public static void setup(final FMLCommonSetupEvent event) {
		INSTANCE.registerMessage(CrawlPacketHandler.CRAWL_ID, CrawlPacketHandler.class, CrawlPacketHandler::encoder, CrawlPacketHandler::decoder, CrawlPacketHandler::handle);
    }
}