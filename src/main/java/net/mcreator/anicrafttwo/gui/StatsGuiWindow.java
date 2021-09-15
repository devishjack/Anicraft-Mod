
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
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.anicrafttwo.AnicrafttwoModVariables;
import net.mcreator.anicrafttwo.AnicrafttwoMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class StatsGuiWindow extends ContainerScreen<StatsGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = StatsGui.guistate;
	public StatsGuiWindow(StatsGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
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
	private static final ResourceLocation texture = new ResourceLocation("anicrafttwo:textures/stats.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
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
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Stats", 69, 7, -12829636);
		this.font.drawString(ms, "Stat Points:", 6, 16, -12829636);
		this.font.drawString(ms, "" + ((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AnicrafttwoModVariables.PlayerVariables())).Stat_Points) + "", 69, 16, -12829636);
		this.font.drawString(ms, "" + ((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AnicrafttwoModVariables.PlayerVariables())).Speed) + "", 60, 34, -12829636);
		this.font.drawString(ms, "" + ((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AnicrafttwoModVariables.PlayerVariables())).Strength) + "", 60, 70, -12829636);
		this.font.drawString(ms, "" + ((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AnicrafttwoModVariables.PlayerVariables())).Defense) + "", 60, 106, -12829636);
		this.font.drawString(ms, "" + ((entity.getCapability(AnicrafttwoModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AnicrafttwoModVariables.PlayerVariables())).Luck) + "", 60, 142, -12829636);
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
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 34, 50, 20, new StringTextComponent("Speed"), e -> {
			if (true) {
				AnicrafttwoMod.PACKET_HANDLER.sendToServer(new StatsGui.ButtonPressedMessage(0, x, y, z));
				StatsGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 70, 50, 20, new StringTextComponent("Strength"), e -> {
			if (true) {
				AnicrafttwoMod.PACKET_HANDLER.sendToServer(new StatsGui.ButtonPressedMessage(1, x, y, z));
				StatsGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 106, 50, 20, new StringTextComponent("Defense"), e -> {
			if (true) {
				AnicrafttwoMod.PACKET_HANDLER.sendToServer(new StatsGui.ButtonPressedMessage(2, x, y, z));
				StatsGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 142, 50, 20, new StringTextComponent("Luck"), e -> {
			if (true) {
				AnicrafttwoMod.PACKET_HANDLER.sendToServer(new StatsGui.ButtonPressedMessage(3, x, y, z));
				StatsGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 114, this.guiTop + 142, 55, 20, new StringTextComponent("Skills"), e -> {
			if (true) {
				AnicrafttwoMod.PACKET_HANDLER.sendToServer(new StatsGui.ButtonPressedMessage(4, x, y, z));
				StatsGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
	}
}
