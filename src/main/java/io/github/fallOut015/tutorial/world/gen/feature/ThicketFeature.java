package io.github.tutorial.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import io.github.fallOut015.tutorial.world.level.block.BlocksTutorial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ThicketFeature extends Feature<NoFeatureConfig> {
	public ThicketFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator chunkGeneratorIn, Random rand, BlockPos pos, NoFeatureConfig config) {
		if(worldIn.isEmptyBlock(pos)) {
			int height = Math.max(rand.nextInt(7), rand.nextInt(7)); // literally just a dnd advantage roll
			for(int i = 1; i < height; ++ i) {
				worldIn.setBlock(pos, BlocksTutorial.THICKET_PLANT.get().defaultBlockState(), 1);

				pos = pos.above();
				
				if(!worldIn.isEmptyBlock(pos) || i == height - 1) {
					worldIn.setBlock(pos.below(), BlocksTutorial.THICKET.get().defaultBlockState(), 1);

					break;
				}
			}
			
			return true;
		}
		
		return false;
	}
}