package tk.diffusehyperion.toomanybuttons.mixin;

import com.terraformersmc.modmenu.gui.ModMenuOptionsScreen;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static tk.diffusehyperion.toomanybuttons.TooManyButtons.logger;

@Mixin(ModMenuOptionsScreen.class)
public abstract class ModMenuOptionsScreenMixin {

    @Shadow private ButtonListWidget list;

    @Inject(method = "init", at = @At("TAIL"))
    public void injectInitMethod(CallbackInfo info) {
        for (ButtonListWidget.ButtonEntry entry : this.list.children()) {
            for (Element element : entry.children()) {
                ClickableWidget widget = (ClickableWidget) element;
                // this took 2 hours to get to this point, fuck you moyang
                if (widget.getMessage().contains(Text.translatable("option.modmenu.mods_button_style"))) {
                    widget.active = false;
                    if (widget.isHovered()) {
                    }
                }
            }
        }
    }
}
