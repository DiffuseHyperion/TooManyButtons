package tk.diffusehyperion.toomanybuttons.mixin;

import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    @Mutable
    @Shadow @Final public static Text COPYRIGHT;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgetsNormal", at = @At("HEAD"))
    protected void initCustomWidgetMethod(int y, int spacingY, CallbackInfo info) {
        assert this.client != null;
        if (HIDE_COPYRIGHT) {
            COPYRIGHT = Text.of("");
            // https://github.com/TomB-134/MinimalMenu/blob/main/src/main/java/minimalmenu/mixin/TitleScreenMixin.java#L24
            // and i quote: "Lol"
        }
    }
}
