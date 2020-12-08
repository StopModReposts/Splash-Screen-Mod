package org.stopmodreposts.stopmodreposts.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.stopmodreposts.stopmodreposts.SMR;
import org.stopmodreposts.stopmodreposts.SMRToast;

@Mixin(SplashScreen.class)
public class SplashScreenMixin {
    private boolean shown = false;
    @Shadow @Final private MinecraftClient client;

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/MinecraftClient;setOverlay(Lnet/minecraft/client/gui/screen/Overlay;)V",
            shift = At.Shift.AFTER
        )
    )
    private void onStart(CallbackInfo ci) {
        if (SMR.settings.showToast) {
            if (shown) {
                return;
            }

            client.getToastManager().add(new SMRToast());
            shown = true;
        }
    }
}
