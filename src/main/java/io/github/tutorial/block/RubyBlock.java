package io.github.tutorial.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class RubyBlock extends Block {
	private static final VoxelShape SHAPE_UP = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	private static final VoxelShape SHAPE_DOWN = Block.box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	private static final VoxelShape SHAPE_NORTH = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 12.0D);
	private static final VoxelShape SHAPE_EAST = Block.box(4.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);
	private static final VoxelShape SHAPE_SOUTH = Block.box(2.0D, 2.0D, 4.0D, 14.0D, 14.0D, 16.0D);
	private static final VoxelShape SHAPE_WEST = Block.box(0.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D);
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public RubyBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.getValue(FACING)) {
			case UP:
			default:
				return SHAPE_UP;
			case DOWN:
				return SHAPE_DOWN;
			case NORTH:
				return SHAPE_NORTH;
			case EAST:
				return SHAPE_EAST;
			case SOUTH:
				return SHAPE_SOUTH;
			case WEST:
				return SHAPE_WEST;
		}
	}
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());

		if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
			return blockstate;
		}

		return null;
	}
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}
	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}