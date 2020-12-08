package org.stopmodreposts.stopmodreposts.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.stopmodreposts.stopmodreposts.SMR;
import org.stopmodreposts.stopmodreposts.SMRMessageScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    public TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgetsNormal(II)V")
    public void drawMenuButton(CallbackInfo info) {
        if (SMR.settings.showButton) {
            this.addButton(new ButtonWidget(
                2,
                SMR.isModMenuLoaded ? 14 : 2,
                100,
                20,
                SMRMessageScreen.TITLE,
                button -> {
                    this.client.openScreen(new SMRMessageScreen(this));
                    this.client.getToastManager().clear();
                }
            ));
        }
    }
}
