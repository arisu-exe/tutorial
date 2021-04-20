package io.github.tutorial.block;

import java.util.Random;

import io.github.tutorial.util.DamageSourceTutorial;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ThicketTopBlock extends AbstractTopPlantBlock {
	private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

	public ThicketTopBlock(Properties properties) {
		super(properties, Direction.UP, SHAPE, false, 0.1d);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && ((LivingEntity) entityIn).getMobType() != CreatureAttribute.ARTHROPOD) {
			entityIn.makeStuckInBlock(state, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
			if (!worldIn.isClientSide && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
	            double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
	            	entityIn.hurt(DamageSourceTutorial.THICKET, 1.0F);
	            }
			}
		}
	}
	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if(player.getUseItem().isEmpty()) {
			player.hurt(DamageSourceTutorial.THICKET, 1.0F);
		}

		super.playerWillDestroy(world, pos, state, player);
	}

	@Override
	protected int getBlocksToGrowWhenBonemealed(Random rand) {
		return PlantBlockHelper.getBlocksToGrowWhenBonemealed(rand);
	}
	@Override
	protected boolean canGrowInto(BlockState state) {
		return PlantBlockHelper.isValidGrowthState(state);
	}
	@Override
	protected Block getBodyBlock() {
		return BlocksTutorial.THICKET_PLANT.get();
	}
}