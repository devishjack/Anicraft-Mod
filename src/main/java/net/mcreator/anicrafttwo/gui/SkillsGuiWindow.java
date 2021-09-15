
package net.mcreator.anicrafttwo.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.anicrafttwo.AnicrafttwoMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class SkillsGuiWindow extends ContainerScreen<SkillsGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = SkillsGui.guistate;
	TextFieldWidget Skill1;
	TextFieldWidget Skill2;
	TextFieldWidget Skill3;
	TextFieldWidget Skill4;
	public SkillsGuiWindow(SkillsGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}
	private static final ResourceLocation texture = new ResourceLocation("anicrafttwo:textures/skills.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		Skill1.render(ms, mouseX, mouseY, partialTicks);
		Skill2.render(ms, mouseX, mouseY, partialTicks);
		Skill3.render(ms, mouseX, mouseY, partialTicks);
		Skill4.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (Skill1.isFocused())
			return Skill1.keyPressed(key, b, c);
		if (Skill2.isFocused())
			return Skill2.keyPressed(key, b, c);
		if (Skill3.isFocused())
			return Skill3.keyPressed(key, b, c);
		if (Skill4.isFocused())
			return Skill4.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		Skill1.tick();
		Skill2.tick();
		Skill3.tick();
		Skill4.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Skills", 60, 7, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		Skill1 = new TextFieldWidget(this.font, this.guiLeft + 6, this.guiTop + 25, 120, 20, new StringTextComponent("Type first skill here")) {
			{
				setSuggestion("Type first skill here");
			}
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Type first skill here");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Type first skill here");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:Skill1", Skill1);
		Skill1.setMaxStringLength(32767);
		this.children.add(this.Skill1);
		Skill2 = new TextFieldWidget(this.font, this.guiLeft + 6, this.guiTop + 52, 120, 20, new StringTextComponent("Type skill 2 here")) {
			{
				setSuggestion("Type skill 2 here");
			}
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Type skill 2 here");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Type skill 2 here");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:Skill2", Skill2);
		Skill2.setMaxStringLength(32767);
		this.children.add(this.Skill2);
		Skill3 = new TextFieldWidget(this.font, this.guiLeft + 6, this.guiTop + 79, 120, 20, new StringTextComponent("Type skill 3 here")) {
			{
				setSuggestion("Type skill 3 here");
			}
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Type skill 3 here");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Type skill 3 here");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:Skill3", Skill3);
		Skill3.setMaxStringLength(32767);
		this.children.add(this.Skill3);
		Skill4 = new TextFieldWidget(this.font, this.guiLeft + 6, this.guiTop + 106, 120, 20, new StringTextComponent("Type skill 4 here")) {
			{
				setSuggestion("Type skill 4 here");
			}
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Type skill 4 here");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Type skill 4 here");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:Skill4", Skill4);
		Skill4.setMaxStringLength(32767);
		this.children.add(this.Skill4);
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 142, 75, 20, new StringTextComponent("Set Skills"), e -> {
			if (true) {
				AnicrafttwoMod.PACKET_HANDLER.sendToServer(new SkillsGui.ButtonPressedMessage(0, x, y, z));
				SkillsGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 87, this.guiTop + 142, 80, 20, new StringTextComponent("List Skills"), e -> {
			if (true) {
				AnicrafttwoMod.PACKET_HANDLER.sendToServer(new SkillsGui.ButtonPressedMessage(1, x, y, z));
				SkillsGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
