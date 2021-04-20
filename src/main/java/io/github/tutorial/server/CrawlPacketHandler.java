package io.github.tutorial.server;

import java.util.function.Supplier;

import io.github.tutorial.TutorialMod;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class CrawlPacketHandler extends PacketHandler {
	public static final int CRAWL_ID = PacketHandler.getNewID();
	private final boolean pressed; // true is start crawl, false is release crawl
	
	public CrawlPacketHandler(boolean pressed) {
		super(CRAWL_ID);
		this.pressed = pressed;
	}
	
	public static void encoder(CrawlPacketHandler msg, PacketBuffer buffer) {
		TutorialMod.LOGGER.debug("Encoding {} to {}", msg, buffer);
		buffer.writeBoolean(msg.getPressed());
	}
	public static CrawlPacketHandler decoder(PacketBuffer buffer) {
		TutorialMod.LOGGER.debug("Decoding {}", buffer);
		return new CrawlPacketHandler(buffer.readBoolean());
	}
	public static void handle(CrawlPacketHandler msg, Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	    	TutorialMod.LOGGER.debug("Recieved crawl packet on server");
	    	
	        ServerPlayerEntity sender = ctx.get().getSender();

	        if(msg.getPressed()) {
	        	if(!sender.isInWater() && sender.isOnGround()) {
	        		TutorialMod.LOGGER.info("Begin crawl.");
			    		
	        		sender.setForcedPose(Pose.SWIMMING);
		        }
	    	} else {
	    		TutorialMod.LOGGER.info("End crawl.");
	    		
	    		sender.setForcedPose(null);
	    	}
	    });
	    
	    ctx.get().setPacketHandled(true);
	}
	
	public boolean getPressed() {
		return this.pressed;
	}
}