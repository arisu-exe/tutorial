package io.github.tutorial.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.world.World;

public class UnicornEntity extends AbstractHorseEntity {
//	private EatToadstoolsGoal eatToadstoolsGoal;

	public UnicornEntity(EntityType<? extends UnicornEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		// attack dragon goal
		// locate twilight petal goal
		// eat toadstools goal
//		this.eatToadstoolsGoal = new EatToadstoolsGoal(this);
		
		super.registerGoals();
	}
}