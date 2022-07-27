package org.stopmodreposts.stopmodreposts.guis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.stopmodreposts.stopmodreposts.StopModReposts;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ComponentRenderUtils;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;

public class InformationGui extends Screen {
	private Screen lastScreen;

	public InformationGui(Screen lastScreen) {
		super(Component.literal("StopModReposts"));
		this.lastScreen = lastScreen;
	}

	@Override
	protected void init() {
		this.addRenderableWidget(
				new Button(this.width / 2 + 10, this.height - 29, 150, 20, CommonComponents.GUI_DONE, button -> {
					try {
						StopModReposts.STOP_REPOST_FILE.createNewFile();
						FileWriter writer = new FileWriter(StopModReposts.STOP_REPOST_FILE);
						writer.write(Component.translatable("stopmodreposts.gui.information.information2").getString());
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					this.onClose();
				}));
		this.addRenderableWidget(new Button(this.width / 2 - 160, this.height - 29, 150, 20,
				Component.translatable("stopmodreposts.gui.information.btnmoreinfo"), button -> {
					this.minecraft.setScreen(new ConfirmLinkScreen(p_169337_ -> {
						if (p_169337_)
							Util.getPlatform().openUri("https://stopmodreposts.org/");
						this.minecraft.setScreen(this);
					}, "https://stopmodreposts.org/", true));
				}));
		super.init();
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(poseStack);
		this.fillGradient(poseStack, 0, 50, this.width, this.height - 40, -1072689136, -804253680);

		GuiComponent.drawCenteredString(poseStack, this.font, this.title.getVisualOrderText(), this.width / 2, 20,
				0xFFFFFF);

		ResourceLocation menuResource = new ResourceLocation("stopmodreposts", "textures/guis/icon.png");
		int imageWidthAndHeight = 150;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, menuResource);
		GuiComponent.blit(poseStack, this.width / 2 - imageWidthAndHeight / 4 - 50, 5, 0, 0, 40, 40, 40, 40);

		String text = Component.translatable("stopmodreposts.gui.information.information").getString();
		String[] multilineA = text.split("\\n");
		int linePos = 70;
		for (String strA : multilineA) {

			int i = Mth.floor(this.width - 30);
			List<FormattedCharSequence> multilineB = ComponentRenderUtils.wrapComponents(Component.literal(strA), i,
					this.minecraft.font);

			for (FormattedCharSequence strB : multilineB) {
				GuiComponent.drawString(poseStack, this.font, strB, 15, linePos, 0xFFFFFF);
				linePos += 10;
			}
		}

		super.render(poseStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void onClose() {
		this.minecraft.setScreen(this.lastScreen);
	}
}
