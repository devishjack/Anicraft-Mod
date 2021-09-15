package net.mcreator.anicrafttwo.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.mcreator.anicrafttwo.AnicrafttwoModVariables;
import net.mcreator.anicrafttwo.AnicrafttwoMod;

import java.util.Map;
import java.util.HashMap;

public class SkillPointIncreaseProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerXPLevelChange(PlayerXpEvent.LevelChange event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				int amount = event.getLevels();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("amount", amount);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency entity for procedure SkillPointIncrease!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) (((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new AnicrafttwoModVariables.PlayerVariables())).SXP) + 1);
			entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.SXP = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AnicrafttwoModVariables.PlayerVariables())).SXP) == ((entity
						.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new AnicrafttwoModVariables.PlayerVariables())).SXP_NextLevel))) {
			{
				double _setval = (double) (((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new AnicrafttwoModVariables.PlayerVariables())).Stat_Points) + 1);
				entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Stat_Points = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new AnicrafttwoModVariables.PlayerVariables())).SXP_NextLevel)
						+ Math.pow(2, ((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new AnicrafttwoModVariables.PlayerVariables())).Stat_Points)));
				entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SXP_NextLevel = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) 0;
				entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SXP = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
