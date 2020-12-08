package org.stopmodreposts.stopmodreposts;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class SMRToast implements Toast {
    private static final Text MESSAGE = new TranslatableText("stopmodreposts.toast");
    private static final Identifier TEXTURE = new Identifier("stopmodreposts:textures/gui/toast_bg.png");
    private long startTime = -1;

    @Override
    public int getWidth() {
        return 180;
    }

    @Override
    public int getHeight() {
        return 40;
    }

    @Override
    public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
        if (this.startTime == -1) {
            this.startTime = startTime;
        }

        // Background
        manager.getGame().getTextureManager().bindTexture(TEXTURE);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        manager.drawTexture(matrices, 0, 0, 0, 0, this.getWidth(), this.getHeight());

        // Icon
        manager.getGame().getTextureManager().bindTexture(SMRMessageScreen.ICON);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        ToastManager.drawTexture(matrices, 6, 6, 22, 22, 0, 0,150, 150, 150, 150);

        // Text
        manager.getGame().textRenderer.drawTrimmed(MESSAGE, 30, 8, getWidth() - 35,0xFFFFFFFF);

        return startTime - this.startTime >= 7_500L ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
    }
}
