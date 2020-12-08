package org.stopmodreposts.stopmodreposts;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.NoticeScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class SMRMessageScreen extends Screen {
    public static final String URL;
    public static final Identifier ICON;
    public static final Text TITLE;
    private static final Text MESSAGE;
    private static final Text DISMISS_TOAST_TEXT;
    private static final Text DISMISS_BUTTON_TEXT;
    private static final Text LEARN_MORE_TEXT;
    private static final Text CLOSE_TEXT;
    private static final Text CLOSE_NOTICE_TEXT;

    static {
        URL = "http://stopmodreposts.org/";
        ICON = new Identifier("stopmodreposts:textures/gui/icon.png");
        TITLE = new TranslatableText("stopmodreposts.stopmodreposts");
        MESSAGE = new TranslatableText("stopmodreposts.screen");
        DISMISS_TOAST_TEXT = new TranslatableText("stopmodreposts.dismiss_toast");
        DISMISS_BUTTON_TEXT = new TranslatableText("stopmodreposts.dismiss_button");
        LEARN_MORE_TEXT = new TranslatableText("stopmodreposts.learn_more");
        CLOSE_TEXT = new TranslatableText("stopmodreposts.close");
        CLOSE_NOTICE_TEXT = new TranslatableText("stopmodreposts.close_notice");
    }

    private final Screen parent;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int logoX;
    private int titleX;
    private CheckboxWidget dismissToastCheckbox;
    private CheckboxWidget dismissButtonCheckbox;

    public SMRMessageScreen(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        this.left = 0;
        this.right = this.width;
        this.top = 32;
        this.bottom = this.height - 64;
        // That +6 is for the gap
        // We multiply by 2 because later we scale it by 2
        this.logoX = this.width / 2 - (this.textRenderer.getWidth(TITLE) * 2 + 24 + 6) / 2;
        // That +6 is for the gap
        // We divide by 2 because we scaled by 2
        this.titleX = (logoX + 24 + 6) / 2;

        this.dismissToastCheckbox = new CheckboxWidget(this.width / 2 - 185, this.height - (40 + 12), 180, 20, DISMISS_TOAST_TEXT, !SMR.settings.showToast);
        this.addButton(this.dismissToastCheckbox);

        this.dismissButtonCheckbox = new CheckboxWidget(this.width / 2 + 5, this.height - (40 + 12), 180, 20, DISMISS_BUTTON_TEXT, !SMR.settings.showButton);
        this.addButton(this.dismissButtonCheckbox);

        this.addButton(new ButtonWidget(this.width / 2 - 185, this.height - (20 + 6), 180, 20, LEARN_MORE_TEXT, button -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(URL);
                }

                this.client.openScreen(this);
            }, URL, true));
        }));

        this.addButton(new ButtonWidget(this.width / 2 + 5, this.height - (20 + 6), 180, 20, CLOSE_TEXT, button -> {
            this.saveSettings();
            if (SMR.settings.showButton) {
                this.client.openScreen(this.parent);
            } else {
                this.client.openScreen(new NoticeScreen(
                    () -> this.client.openScreen(this.parent),
                    NarratorManager.EMPTY,
                    CLOSE_NOTICE_TEXT,
                    ScreenTexts.PROCEED
                ));
            }
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        // Message darker background
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        this.client.getTextureManager().bindTexture(DrawableHelper.OPTIONS_BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        // Setting corner coordinates and tex offset aka 𝖛𝖊𝖗𝖙𝖎𝖈𝖊𝖘 𝖒𝖆𝖌𝖎𝖈
        bufferBuilder.begin(7, VertexFormats.POSITION_COLOR_TEXTURE);
        bufferBuilder
            .vertex(this.left, this.bottom, 0.0D)
            .color(32, 32, 32, 255)
            .texture(this.left / 32.0F, this.bottom / 32.0F)
            .next();
        bufferBuilder
            .vertex(this.right, this.bottom, 0.0D)
            .color(32, 32, 32, 255)
            .texture(this.right / 32.0F, this.bottom / 32.0F)
            .next();
        bufferBuilder
            .vertex(this.right, this.top, 0.0D)
            .color(32, 32, 32, 255)
            .texture(this.right / 32.0F, this.top / 32.0F)
            .next();
        bufferBuilder
            .vertex(this.left, this.top, 0.0D)
            .color(32, 32, 32, 255)
            .texture(this.left / 32.0F, this.top / 32.0F)
            .next();
        tessellator.draw();

        // Logo
        this.client.getTextureManager().bindTexture(ICON);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        drawTexture(matrices, this.logoX, 4, 24, 24, 0, 0,150, 150, 150, 150);

        // Title
        matrices.push();
        matrices.scale(2, 2, 2);
        drawTextWithShadow(matrices, this.textRenderer, TITLE, this.titleX, 5, 0xFFFFFFFF);
        matrices.pop();

        // Message
        this.textRenderer.drawTrimmed(MESSAGE, this.left + 20, this.top + 6, this.width - 40, 0xFFFFFFFF);

        super.render(matrices, mouseX, mouseY, delta);
    }

    public void saveSettings() {
        SMR.Settings settings = new SMR.Settings();
        settings.showToast = !(this.dismissToastCheckbox.isChecked() || this.dismissButtonCheckbox.isChecked());
        settings.showButton = !this.dismissButtonCheckbox.isChecked();
        SMR.saveSettings(settings);
    }
}
