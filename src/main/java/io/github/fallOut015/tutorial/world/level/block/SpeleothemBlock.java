package io.github.fallOut015.tutorial.world.level.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.Tags;

public class SpeleothemBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(1.0D, 3.0D, 1.0D, 14.0D, 16.0D, 14.0D);

	public SpeleothemBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return super.canSurvive(state, worldIn, pos) && worldIn.getBlockState(pos.above()).is(Tags.Blocks.STONE);
	}
}