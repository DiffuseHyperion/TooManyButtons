package tk.diffusehyperion.toomanybuttons.mixin;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.config.ModMenuConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.terraformersmc.modmenu.config.ModMenuConfig.MODS_BUTTON_STYLE;

@Mixin(ModMenuConfigManager.class)
public abstract class ModMenuConfigManagerMixin {

    @Inject(method = "load", at = @At("TAIL"), remap = false)
    private static void injectLoadMethod(CallbackInfo info) {
        MODS_BUTTON_STYLE.setValue(ModMenuConfig.ModsButtonStyle.CLASSIC);
        // at the moment, only classic setting works :shrug:
    }

    @Inject(method = "save", at = @At("TAIL"), remap = false)
    private static void injectSaveMethod(CallbackInfo info) {
        MODS_BUTTON_STYLE.setValue(ModMenuConfig.ModsButtonStyle.CLASSIC);
        // at the moment, only classic setting works :shrug:
    }
}
