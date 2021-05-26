package io.github.tutorial.block;

import io.github.tutorial.TutorialMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksTutorial {
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);
	
	
	
	public static final RegistryObject<Block> SCALE_BLOCK = BLOCKS.register("scale_block", () -> new Block(Block.Properties.of(Material.METAL, MaterialColor.SAND).strength(1000)));
	
	public static final RegistryObject<Block> THICKET = BLOCKS.register("thicket", () -> new ThicketTopBlock(Block.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).strength(1.0f, 0.5f).noOcclusion().noCollission())); // hard
	public static final RegistryObject<Block> THICKET_PLANT = BLOCKS.register("thicket_plant", () -> new ThicketBlock(Block.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).strength(1.0f, 0.5f).noOcclusion().noCollission())); // hard
	public static final RegistryObject<Block> TOADSTOOL = BLOCKS.register("toadstool", () -> new ToadstoolBlock(Block.Properties.of(Material.GRASS, MaterialColor.TERRACOTTA_WHITE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS))); // easy
	public static final RegistryObject<Block> TWILIGHT_PETAL = BLOCKS.register("twilight_petal", () -> new TwilightPetalBlock(AbstractBlock.Properties.of(Material.GRASS).noCollission().instabreak().sound(SoundType.GRASS).randomTicks().lightLevel(state -> (state.getValue(TwilightPetalBlock.BLOOM) - 1) * 3))); // medium

	public static final RegistryObject<Block> SCORIA = BLOCKS.register("scoria", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(2.0f, 1.0f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops())); // easy
	public static final RegistryObject<Block> RUBY = BLOCKS.register("ruby", () -> new RubyBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(3.0f, 2.0f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().lightLevel(state -> 1).noOcclusion().noCollission())); // hard
	public static final RegistryObject<Block> SPELEOTHEM = BLOCKS.register("speleothem", () -> new SpeleothemBlock(Block.Properties.of(Material.STONE).strength(1.0f, 0.5f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().noOcclusion())); // medium
	
	
	
	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
	}
}
