
package net.mcreator.anicrafttwo.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.anicrafttwo.AnicrafttwoModElements;

@AnicrafttwoModElements.ModElement.Tag
public class GomuGomuNoMiItem extends AnicrafttwoModElements.ModElement {
	@ObjectHolder("anicrafttwo:gomu_gomu_no_mi")
	public static final Item block = null;
	public GomuGomuNoMiItem(AnicrafttwoModElements instance) {
		super(instance, 22);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).rarity(Rarity.EPIC)
					.food((new Food.Builder()).hunger(4).saturation(0.3f).build()));
			setRegistryName("gomu_gomu_no_mi");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}
