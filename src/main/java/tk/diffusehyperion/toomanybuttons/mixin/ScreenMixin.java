package tk.diffusehyperion.toomanybuttons.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.diffusehyperion.toomanybuttons.gui.EditControlsScreen;
import tk.diffusehyperion.toomanybuttons.gui.EditGameScreen;
import tk.diffusehyperion.toomanybuttons.gui.EditOptionsScreen;
import tk.diffusehyperion.toomanybuttons.gui.EditTitleScreen;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.SIMPLIFY_CONTROLS;
import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.TITLE_PARITY;

@Mixin(value = Screen.class, priority = 1100)
public abstract class ScreenMixin {

    @Shadow @Nullable protected MinecraftClient client;

    @Mutable
    @Shadow @Final protected Text title;

    @Inject(method = "init", at = @At("TAIL"))
    public void injectInitMethod(MinecraftClient client, int width, int height, CallbackInfo info) {
        Screen screen = (Screen) (Object) this;
        if (screen instanceof TitleScreen) {
            new EditTitleScreen(screen).main();
        } else if (screen instanceof GameMenuScreen) {
            new EditGameScreen(screen, client).main();
        } else if (screen instanceof OptionsScreen) {
            new EditOptionsScreen(screen, client).main();
        } else if (!SIMPLIFY_CONTROLS && screen instanceof ControlsOptionsScreen) {
            new EditControlsScreen(screen).main();
        }
        if (TITLE_PARITY && screen instanceof GameOptionsScreen) {
            if (this.title.getString().contains("...")) {
                this.title = Text.literal(this.title.getString().replace("...", ""));
            }
        }
    }
}
