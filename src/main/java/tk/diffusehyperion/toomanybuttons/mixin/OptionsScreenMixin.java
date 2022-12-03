package tk.diffusehyperion.toomanybuttons.mixin;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.SIMPLIFY_CONTROLS;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    public void initInjectMethod(CallbackInfo info) {
        if (SIMPLIFY_CONTROLS) {
            for (ClickableWidget widget : Screens.getButtons(this)) {
                if (Objects.equals(widget.getMessage(), Text.translatable("options.controls"))) {
                    widget.visible = false;
                    break;
                }
            }
            this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, Text.translatable("options.controls"), (button) -> {
                assert this.client != null;
                this.client.setScreen(new KeybindsScreen(this, this.client.options));
            }));
        }
    }
}
