package io.github.fallOut015.tutorial.world.level.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TwilightPetalBlock extends FlowerBlock {
	public static final IntegerProperty BLOOM = IntegerProperty.create("bloom", 1, 3);
	
	public TwilightPetalBlock(Properties properties) {
		super(MobEffects.NIGHT_VISION, 25, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(BLOOM, Integer.valueOf(3)));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(BLOOM, 1);
	}
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BLOOM);
	}
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if(worldIn.isNight()) {
			if(worldIn.getBlockState(pos).getValue(BLOOM) < 3) {
				worldIn.setBlockAndUpdate(pos, worldIn.getBlockState(pos).setValue(BLOOM, worldIn.getBlockState(pos).getValue(BLOOM) + 1));
			}
		} else {
			if(worldIn.getBlockState(pos).getValue(BLOOM) > 1) {
				worldIn.setBlockAndUpdate(pos, worldIn.getBlockState(pos).setValue(BLOOM, worldIn.getBlockState(pos).getValue(BLOOM) - 1));
			}
		}
	}
}