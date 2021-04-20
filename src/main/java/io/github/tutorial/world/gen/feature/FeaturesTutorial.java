package io.github.tutorial.world.gen.feature;

import com.google.common.collect.ImmutableList;

import io.github.tutorial.world.gen.placement.PlacementTutorial;
import io.github.tutorial.world.gen.treedecorator.ToadstoolTreeDecorator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;

public class FeaturesTutorial {
	public static final ConfiguredFeature<?, ?> THICKET = FeatureTutorial.THICKET.get().configured(IFeatureConfig.NONE).decorated(Placements.RING_PLACEMENT).squared();
	// twilight petal
	// toadstool on ground
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> BIRCH_TOADSTOOLS = Feature.TREE.configured(Features.BIRCH.config().withDecorators(ImmutableList.of(Placements.TOADSTOOLS_PLACEMENT)));
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> OAK_TOADSTOOLS = Feature.TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Placements.TOADSTOOLS_PLACEMENT)));
	
	public static final class Configs {
	}
	public static final class Placements {
		public static final ConfiguredPlacement<NoPlacementConfig> RING_PLACEMENT = PlacementTutorial.RING.get().configured(IPlacementConfig.NONE);
	
		public static final ToadstoolTreeDecorator TOADSTOOLS_PLACEMENT = new ToadstoolTreeDecorator(0.5F);
	}
	public static final class States {
	}
	public static final class RuleTests {
	}
}