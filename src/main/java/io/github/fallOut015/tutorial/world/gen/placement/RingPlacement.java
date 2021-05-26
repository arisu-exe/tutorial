package io.github.tutorial.world.gen.placement;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class RingPlacement extends Placement<NoPlacementConfig> {
	public RingPlacement(Codec<NoPlacementConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, NoPlacementConfig config, BlockPos pos) {
		return IntStream.range(0, 359).mapToObj(degrees -> {
			double radians = Math.toRadians(degrees);
			double range = 12;
			double x = Math.cos(radians) * range + rand.nextInt(3) - 1;
			double z = Math.sin(radians) * range + rand.nextInt(3) - 1;
			return new BlockPos(pos.getX() + x, pos.getY() + 80, pos.getZ() + z);
		}).filter(Objects::nonNull);
	}
}