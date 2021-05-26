package io.github.tutorial.item;

import io.github.tutorial.TutorialMod;
import io.github.tutorial.block.BlocksTutorial;
import io.github.tutorial.entity.EntityTypeTutorial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsTutorial {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);
	
	
	
	public static final RegistryObject<Item> SCALE_BLOCK = ITEMS.register("scale_block", () -> new BlockItem(BlocksTutorial.SCALE_BLOCK.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

	public static final RegistryObject<Item> THICKET = ITEMS.register("thicket", () -> new BlockItem(BlocksTutorial.THICKET.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	public static final RegistryObject<Item> TOADSTOOL = ITEMS.register("toadstool", () -> new BlockItem(BlocksTutorial.TOADSTOOL.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	public static final RegistryObject<Item> TWILIGHT_PETAL = ITEMS.register("twilight_petal", () -> new BlockItem(BlocksTutorial.TWILIGHT_PETAL.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	
	public static final RegistryObject<Item> SCORIA = ITEMS.register("scoria", () -> new BlockItem(BlocksTutorial.SCORIA.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new BlockItem(BlocksTutorial.RUBY.get(), new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> SPELEOTHEM = ITEMS.register("speleothem", () -> new BlockItem(BlocksTutorial.SPELEOTHEM.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	
	public static final RegistryObject<Item> RAINBOW_HORN = ITEMS.register("rainbow_horn", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> DRACONIC_SCALE = ITEMS.register("draconic_scale", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	
	public static final RegistryObject<Item> UNICORN_SPAWN_EGG = ITEMS.register("unicorn_spawn_egg", () -> new SpawnEggItemTutorial(() -> EntityTypeTutorial.UNICORN.get(), 15922431, 10395391, new Item.Properties().tab(ItemGroup.TAB_MISC)));

	public static final RegistryObject<Item> RAINBOW_SWORD = ITEMS.register("rainbow_sword", () -> new SwordItem(ItemTierTutorial.RAINBOW, 3, -2.4f, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
	
	public static final RegistryObject<Item> DRACONIC_HELMET = ITEMS.register("draconic_helmet", () -> new ArmorItem(ArmorMaterialTutorial.DRACONIC, EquipmentSlotType.HEAD, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
	public static final RegistryObject<Item> DRACONIC_CHESTPLATE = ITEMS.register("draconic_chestplate", () -> new ArmorItem(ArmorMaterialTutorial.DRACONIC, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
	public static final RegistryObject<Item> DRACONIC_LEGGINGS = ITEMS.register("draconic_leggings", () -> new ArmorItem(ArmorMaterialTutorial.DRACONIC, EquipmentSlotType.LEGS, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
	public static final RegistryObject<Item> DRACONIC_BOOTS = ITEMS.register("draconic_boots", () -> new ArmorItem(ArmorMaterialTutorial.DRACONIC, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
	
	
	
	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	}
}