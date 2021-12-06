package io.github.fallOut015.tutorial.world.item;

import java.util.function.Supplier;

import io.github.tutorial.util.SoundEventsTutorial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ArmorMaterialTutorial implements IArmorMaterial {
	DRACONIC("draconic", 47, new int[]{4, 7, 9, 4}, 25, SoundEventsTutorial.ITEM_ARMOR_EQUIP_DRACONIC, 4.0F, 0.2F, () -> {
		return Ingredient.of(ItemsTutorial.DRACONIC_SCALE.get());
	});

	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final LazyValue<SoundEvent> soundEvent;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairMaterial;

	private ArmorMaterialTutorial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, Supplier<SoundEvent> soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
		this.name = name;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = new LazyValue<>(soundEvent);
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairMaterial = new LazyValue<>(repairMaterial);
	}
	   
	@Override
	public int getDurabilityForSlot(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}
	@Override
	public int getDefenseForSlot(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}
	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}
	@Override
	public SoundEvent getEquipSound() {
		return this.soundEvent.get();
	}
	@Override
	public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}
	@Override
	public float getToughness() {
		return this.toughness;
	}
	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}