package io.github.tutorial.entity.ai.goal;

import java.util.EnumSet;
import java.util.function.Predicate;

import io.github.tutorial.block.BlocksTutorial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EatToadstoolsGoal extends Goal {
	private static final Predicate<BlockState> IS_TOADSTOOL = BlockStateMatcher.forBlock(BlocksTutorial.TOADSTOOL.get());
	private final MobEntity toadstoolEaterEntity;
	private final World entityWorld;
	private int eatingToadstoolTimer;

	public EatToadstoolsGoal(MobEntity toadstoolEaterEntityIn) {
		this.toadstoolEaterEntity = toadstoolEaterEntityIn;
		this.entityWorld = toadstoolEaterEntity.level;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
	}
	
	public boolean canUse() {
		if (this.toadstoolEaterEntity.getRandom().nextInt(this.toadstoolEaterEntity.isBaby() ? 50 : 1000) != 0) {
			return false;
		} else {
			BlockPos blockpos = this.toadstoolEaterEntity.blockPosition();
			if (IS_TOADSTOOL.test(this.entityWorld.getBlockState(blockpos))) {
				return true;
			} else {
	            return this.entityWorld.getBlockState(blockpos.below()).is(Blocks.GRASS_BLOCK);
			}
		}
	}
	
	@Override
	public void start() {
		this.eatingToadstoolTimer = 40;
		this.entityWorld.broadcastEntityEvent(this.toadstoolEaterEntity, (byte)10);
		this.toadstoolEaterEntity.getNavigation().stop();
	}

	@Override
	public void stop() {
		this.eatingToadstoolTimer = 0;
	}
	@Override
	public boolean canContinueToUse() {
		return this.eatingToadstoolTimer > 0;
	}
	public int getEatingToadstoolTimer() {
		return this.eatingToadstoolTimer;
	}
	@Override
	public void tick() {
		this.eatingToadstoolTimer = Math.max(0, this.eatingToadstoolTimer - 1);
		if (this.eatingToadstoolTimer == 4) {
			BlockPos blockpos = this.toadstoolEaterEntity.blockPosition();
			if (IS_TOADSTOOL.test(this.entityWorld.getBlockState(blockpos))) {
				if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.toadstoolEaterEntity)) {
					this.entityWorld.destroyBlock(blockpos, false);
	            }
			} else {
	            BlockPos blockpos1 = blockpos.below();
	            if (this.entityWorld.getBlockState(blockpos1).is(Blocks.GRASS_BLOCK)) {
	            	if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.toadstoolEaterEntity)) {
	            		this.entityWorld.globalLevelEvent(2001, blockpos1, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
	            		this.entityWorld.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), 2);
	            	}
	            }
			}
		}
	}
}