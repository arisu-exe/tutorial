package io.github.fallOut015.tutorial.world.level.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class ToadstoolBlock extends MushroomBlock {
	public static final IntegerProperty TOADSTOOLS = IntegerProperty.create("toadstools", 1, 2);
	private static final VoxelShape ONE_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
	private static final VoxelShape TWO_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D);

	public ToadstoolBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(TOADSTOOLS, Integer.valueOf(1)));
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		if (blockstate.is(this)) {
			return blockstate.setValue(TOADSTOOLS, Integer.valueOf(Math.min(2, blockstate.getValue(TOADSTOOLS) + 1)));
		} else {
			return super.getStateForPlacement(context);
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && state.getValue(TOADSTOOLS) < 2 ? true : super.canBeReplaced(state, useContext);
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.getValue(TOADSTOOLS)) {
			case 1:
			default:
				return ONE_SHAPE;
			case 2:
				return TWO_SHAPE;
		}
	}
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(TOADSTOOLS);
	}
	@Override
	public boolean growMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
		if(state.getValue(TOADSTOOLS).intValue() < 2) {
			world.setBlockAndUpdate(pos, state.setValue(TOADSTOOLS, state.getValue(TOADSTOOLS).intValue() + 1));
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || super.canSurvive(state, worldIn, pos);
	}

	// TODO bonemeal from 1 to 2 and then 2 to spread 1
}