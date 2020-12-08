package org.stopmodreposts.stopmodreposts;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.NoticeScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class SMRSettingsScreen extends Screen {
    private static final Text SHOW_TOAST_TEXT;
    private static final Text SHOW_BUTTON_TEXT;
    private static final Text CLOSE_TEXT;
    private static final Text DISCARD_TEXT;

    static {
        SHOW_TOAST_TEXT = new TranslatableText("stopmodreposts.show_toast");
        SHOW_BUTTON_TEXT = new TranslatableText("stopmodreposts.show_button");
        CLOSE_TEXT = new TranslatableText("stopmodreposts.close");
        DISCARD_TEXT = new TranslatableText("stopmodreposts.discard");
    }

    private final Screen parent;
    private int logoX;
    private int logoY;
    private int titleX;
    private CheckboxWidget showToastCheckbox;
    private CheckboxWidget showButtonCheckbox;

    protected SMRSettingsScreen(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        // That +6 is for the gap
        this.logoX = this.width / 2 - 90;
        this.logoY = this.height / 2 - this.height / 4;
        // That +6 is for the gap
        this.titleX = logoX + 20 + 6;

        this.showToastCheckbox = new CheckboxWidget(this.width / 2 - 90, this.logoY + 25, 180, 20, SHOW_TOAST_TEXT, SMR.settings.showToast);
        this.addButton(this.showToastCheckbox);

        this.showButtonCheckbox = new CheckboxWidget(this.width / 2 - 90, this.logoY + 50, 180, 20, SHOW_BUTTON_TEXT, SMR.settings.showButton);
        this.addButton(this.showButtonCheckbox);

        this.addButton(new ButtonWidget(this.width / 2 - 90, this.logoY + 75, 180, 20, CLOSE_TEXT, button -> {
            this.saveSettings();
            this.client.openScreen(this.parent);
        }));

        this.addButton(new ButtonWidget(this.width / 2 - 90, this.logoY + 100, 180, 20, DISCARD_TEXT, button -> {
            this.client.openScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        // Logo
        this.client.getTextureManager().bindTexture(SMRMessageScreen.ICON);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        drawTexture(matrices, this.logoX, this.logoY, 20, 20, 0, 0,150, 150, 150, 150);

        // Title
        drawTextWithShadow(matrices, this.textRenderer, SMRMessageScreen.TITLE, this.titleX, this.logoY + 6, 0xFFFFAA00);

        super.render(matrices, mouseX, mouseY, delta);
    }

    public void saveSettings() {
        SMR.Settings settings = new SMR.Settings();
        settings.showToast = this.showToastCheckbox.isChecked();
        settings.showButton = this.showButtonCheckbox.isChecked();
        SMR.saveSettings(settings);
    }
}
