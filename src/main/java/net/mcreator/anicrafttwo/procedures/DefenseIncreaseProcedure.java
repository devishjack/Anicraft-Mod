package net.mcreator.anicrafttwo.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.anicrafttwo.AnicrafttwoModVariables;
import net.mcreator.anicrafttwo.AnicrafttwoMod;

import java.util.Map;

public class DefenseIncreaseProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency entity for procedure DefenseIncrease!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AnicrafttwoModVariables.PlayerVariables())).Stat_Points) > 0)) {
			{
				double _setval = (double) (((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new AnicrafttwoModVariables.PlayerVariables())).Defense) + 0.1);
				entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Defense = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new AnicrafttwoModVariables.PlayerVariables())).Stat_Points) - 1);
				entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Stat_Points = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
