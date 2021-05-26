package io.github.tutorial.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import net.minecraft.block.AbstractBlock.Properties;

public class TwilightPetalBlock extends FlowerBlock {
	public static final IntegerProperty BLOOM = IntegerProperty.create("bloom", 1, 3);
	
	public TwilightPetalBlock(Properties properties) {
		super(Effects.NIGHT_VISION, 25, properties);
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