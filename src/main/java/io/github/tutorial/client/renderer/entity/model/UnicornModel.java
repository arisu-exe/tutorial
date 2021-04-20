package io.github.tutorial.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UnicornModel<T extends AbstractHorseEntity> extends HorseModel<T> {
	public UnicornModel(float scale) {
		super(scale);
		
		this.headParts.addBox(-1, -17, 1, 2, 6, 2).texOffs(0, 0);
	}
}