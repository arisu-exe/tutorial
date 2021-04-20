package io.github.tutorial.world.gen.treedecorator;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import io.github.tutorial.block.BlocksTutorial;
import io.github.tutorial.block.ToadstoolBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class ToadstoolTreeDecorator extends TreeDecorator {
	public static final Codec<ToadstoolTreeDecorator> codec = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(ToadstoolTreeDecorator::new, (instance) -> {
		return instance.probability;
	}).codec();
	private final float probability;

	public ToadstoolTreeDecorator(float probabilityIn) {
		this.probability = probabilityIn;
	}
	   
	@Override
	protected TreeDecoratorType<?> type() {
		return TreeDecoratorTypeTutorial.TOADSTOOL.get();
	}
	@Override
	public void place(ISeedReader worldIn, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> set, MutableBoundingBox mutableBB) {
		if (!(rand.nextFloat() >= this.probability)) {
			Iterator<Direction> directions = Direction.Plane.HORIZONTAL.stream().sorted((x, y) -> rand.nextBoolean() ? -1 : 1).iterator();
			
			int toadstools = Math.min(rand.nextInt(4) + 1, rand.nextInt(4) + 1); // literally dnd disadvantage
			for(int i = 0; i < toadstools;) {
				int count = rand.nextInt(2) + 1;
				if(i == toadstools - 1) {
					count = 1;
				}
				
				BlockState state = BlocksTutorial.TOADSTOOL.get().defaultBlockState().setValue(ToadstoolBlock.TOADSTOOLS, count);
				worldIn.setBlock(logs.get(0).relative(directions.next()), state, 1);
				
				i += count;
			}
		}
	}
}