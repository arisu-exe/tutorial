package io.github.tutorial.client.renderer.entity;

import io.github.tutorial.client.renderer.entity.model.UnicornModel;
import io.github.tutorial.entity.UnicornEntity;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class UnicornRenderer extends AbstractHorseRenderer<UnicornEntity, UnicornModel<UnicornEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("tutorial", "textures/entity/unicorn.png");
	
	public UnicornRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new UnicornModel<UnicornEntity>(0f), 1.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(UnicornEntity entity) {
		return TEXTURE;
	}
}