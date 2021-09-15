package net.mcreator.anicrafttwo.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;

import net.mcreator.anicrafttwo.item.TheBlueSeaOrbItem;
import net.mcreator.anicrafttwo.AnicrafttwoMod;

import java.util.Map;

public class EiichiroOdaEntityDiesProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency x for procedure EiichiroOdaEntityDies!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency y for procedure EiichiroOdaEntityDies!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency z for procedure EiichiroOdaEntityDies!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency world for procedure EiichiroOdaEntityDies!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.isRemote()) {
			ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(TheBlueSeaOrbItem.block));
			entityToSpawn.setPickupDelay((int) 0);
			entityToSpawn.setNoDespawn();
			world.addEntity(entityToSpawn);
		}
	}
}
