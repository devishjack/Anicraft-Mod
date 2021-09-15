package net.mcreator.anicrafttwo.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.anicrafttwo.AnicrafttwoModVariables;
import net.mcreator.anicrafttwo.AnicrafttwoMod;

import java.util.Map;
import java.util.HashMap;

public class SetSkillSaveProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency entity for procedure SetSkillSave!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				AnicrafttwoMod.LOGGER.warn("Failed to load dependency guistate for procedure SetSkillSave!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		{
			String _setval = (String) (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Skill1");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText());
			entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.SkillOneSave = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = (String) (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Skill2");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText());
			entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.SkillTwoSave = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = (String) (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Skill3");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText());
			entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.SkillThreeSave = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = (String) (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Skill4");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText());
			entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.SkillFourSave = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
