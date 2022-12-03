package tk.diffusehyperion.toomanybuttons.mixin;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

@Mixin(value = Screen.class, priority = 1100)
public abstract class ScreenMixin {

    @Shadow @Nullable protected MinecraftClient client;

    @Mutable
    @Shadow @Final protected Text title;

    @Inject(method = "init", at = @At("TAIL"))
    public void injectInitMethod(MinecraftClient client, int width, int height, CallbackInfo info) {
        if ((Screen)(Object)this instanceof TitleScreen) {
            titleScreenInit();
        } else if ((Screen)(Object)this instanceof GameMenuScreen) {
            gameMenuScreenInit();
        } else if ((Screen)(Object)this instanceof OptionsScreen) {
            optionsScreenInit();
        } else if (SIMPLIFY_CONTROLS && (Screen)(Object)this instanceof ControlsOptionsScreen) {
            controlsScreenInit();
        }
        if (TITLE_PARITY && (Screen)(Object)this instanceof GameOptionsScreen) {
            if (this.title.getString().contains("...")) {
                this.title = Text.literal(this.title.getString().replace("...", ""));
            }
        }
    }

    public void titleScreenInit() {
        int yOffset = 0;
        for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
            if (widgetMatchesKey(widget, "menu.singleplayer")) {
                yOffset = changeWidget(widget, HIDE_SINGLEPLAYER, yOffset);
            }
            if (widgetMatchesKey(widget, "menu.multiplayer")) {
                yOffset = changeWidget(widget, HIDE_MULTIPLAYER, yOffset);
            }
            if (widgetMatchesKey(widget, "menu.online")) {
                yOffset = changeWidget(widget, HIDE_REALMS, yOffset);
            }
            if (widgetMatchesKey(widget, "modmenu.title")) {
                yOffset = changeWidget(widget, HIDE_MODMENU_TITLESCREEN, yOffset);
            }
            if (widgetMatchesKey(widget, "menu.options")) {
                yOffset = changeWidget(widget, HIDE_OPTIONS_TITLESCREEN, yOffset);
            }
            if (widgetMatchesKey(widget, "menu.quit")) {
                yOffset = changeWidget(widget, HIDE_QUITGAME, yOffset);
            }
            if (widgetMatchesKey(widget, "narrator.button.language")) {
                if (HIDE_LANGUAGE_TITLESCREEN) {
                    widget.visible = false;
                } else {
                    widget.y -= yOffset;
                }
            }
            if (widgetMatchesKey(widget, "narrator.button.accessibility")) {
                if (HIDE_ACCESSIBILITY_TITLESCREEN) {
                    widget.visible = false;
                } else {
                    widget.y -= yOffset;
                }
            }
        }
    }

    public void gameMenuScreenInit() {
        for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
            if (widgetMatchesKey(widget, "modmenu.title")) {
                widget.y += 48;
            }
            if (widgetMatchesKey(widget, "menu.options")) {
                widget.y -= 24;
            }
            if (widgetMatchesKey(widget, "menu.shareToLan")) {
                widget.y -= 24;
            }
            if (widgetMatchesKey(widget, "menu.playerReporting")) {
                widget.y -= 24;
            }
            if (widgetMatchesKey(widget, "menu.returnToMenu")) {
                widget.y -= 24;
            }
            if (widgetMatchesKey(widget, "menu.disconnect")) {
                widget.y -= 24;
            }
        }
        // for some reason, mod menu button is smack in the middle of the menu between feedback and options,
        // so i move it to below quit to title and move everything below original mod menu upwards

        int yOffsetLeft = 0;
        // includes advancements, give feedback, options
        int yOffsetRight = 0;
        // includes statistics, report bugs, lan/report players
        for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
            if (widgetMatchesKey(widget, "gui.advancements")) {
                yOffsetLeft = changeWidget(widget, HIDE_ADVANCEMENT, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "menu.sendFeedback")) {
                yOffsetLeft = changeWidget(widget, HIDE_FEEDBACK, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "menu.options")) {
                yOffsetLeft = changeWidget(widget, HIDE_OPTIONS_GAMEMENU, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "gui.stats")) {
                yOffsetRight = changeWidget(widget, HIDE_STATISTICS, yOffsetRight);
            }
            if (widgetMatchesKey(widget, "menu.reportBugs")) {
                yOffsetRight = changeWidget(widget, HIDE_REPORT, yOffsetRight);
            }
            if (widgetMatchesKey(widget, "menu.shareToLan")) {
                yOffsetRight = changeWidget(widget, HIDE_LAN, yOffsetRight);
            }
            if (widgetMatchesKey(widget, "menu.playerReporting")) {
                yOffsetRight = changeWidget(widget, HIDE_REPORTING, yOffsetRight);
            }
            if (widgetMatchesKey(widget, "modmenu.title")) {
                if (HIDE_MODMENU_GAMEMENU) {
                    widget.visible = false;
                }
            }
        }
    }

    public void optionsScreenInit() {
        assert this.client != null;
        boolean inSinglePlayer = this.client.isInSingleplayer();
        if (inSinglePlayer) {
            if (HIDE_FOV && HIDE_DIFFICULTY) {
                for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
                    widget.y -= 24;
                    // its fine to move up every widget since difficulty and fov would be hidden anyways
                }
            } else if (HIDE_FOV) {
                for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
                    if (widget instanceof CyclingButtonWidget && widget.getMessage().contains(Text.translatable("options.difficulty"))) {
                        widget.x -= 160;
                        widget.setWidth(290);
                    }
                }
            } else if (HIDE_DIFFICULTY) {
                for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
                    if (widget.getMessage().contains(Text.translatable("options.fov"))) {
                        widget.setWidth(310);
                    }
                }
            }
        } else {
            if (HIDE_FOV && HIDE_ONLINE) {
                for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
                    widget.y -= 24;
                    // its fine to move up every widget since difficulty and fov would be hidden anyways
                }
            } else if (HIDE_FOV) {
                for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
                    if (widgetMatchesKey(widget, "options.online")) {
                        widget.x -= 160;
                        widget.setWidth(310);
                    }
                }
            } else if (HIDE_ONLINE) {
                for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
                    if (widget.getMessage().contains(Text.translatable("options.fov"))) {
                        widget.setWidth(310);
                    }
                }
            }
        }
        // if fov and difficulty is disabled, shift buttons up by 24 (minus)
        // otherwise, if one is disabled extend the other to cover the whole row

        int yOffsetLeft = 0;
        // includes skin, video, lang, resource
        int yOffsetRight = 0;
        // includes music, controls, chat, accessibility

        for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
            if (widget instanceof CyclingButtonWidget && widget.getMessage().contains(Text.translatable("options.difficulty"))) {
                if (HIDE_DIFFICULTY) {
                    widget.visible = false;
                }
            }
            if (widget instanceof LockButtonWidget) {
                if (HIDE_DIFFICULTY) {
                    widget.visible = false;
                }
            }
            if (widget.getMessage().contains(Text.translatable("options.fov"))) {
                if (HIDE_FOV) {
                    widget.visible = false;
                }
            }
            if (widgetMatchesKey(widget, "options.online")) {
                if (HIDE_ONLINE) {
                    widget.visible = false;
                }
            }
            // skip past checking singleplayer cuz lazy lmfao
            // i rather make an impossible check within a loop then making 2 loops

            // those 3 widgets are in a "separate" row, and will not affect offset values
            if (widgetMatchesKey(widget, "options.skinCustomisation")) {
                yOffsetLeft = changeWidget(widget, HIDE_SKINS, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "options.video")) {
                yOffsetLeft = changeWidget(widget, HIDE_VIDEO, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "options.language")) {
                yOffsetLeft = changeWidget(widget, HIDE_LANGUAGE_OPTIONSMENU, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "options.resourcepack")) {
                yOffsetLeft = changeWidget(widget, HIDE_RESOURCE, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "options.sounds")) {
                yOffsetRight = changeWidget(widget, HIDE_MUSIC, yOffsetRight);
            }
            if (widgetMatchesKey(widget, "options.controls")) {
                if (!SIMPLIFY_CONTROLS) {
                    // simplify controls will take care of setting it invisible
                    yOffsetRight = changeWidget(widget, HIDE_CONTROLS, yOffsetRight);
                }
            }
            if (widgetMatchesKey(widget, "options.chat.title")) {
                // for some reason chat controls only has .title as a key????
                yOffsetRight = changeWidget(widget, HIDE_CHAT, yOffsetRight);
            }
            if (widgetMatchesKey(widget, "options.accessibility.title")) {
                // same too
                yOffsetRight = changeWidget(widget, HIDE_ACCESSIBILITY_OPTIONSMENU, yOffsetRight);
            }
        }
    }

    public void controlsScreenInit() {
        int yOffsetLeft = 0;
        // includes mouse, sneak, auto
        int yOffsetRight = 0;
        // includes keybinds, sprint
        for (ClickableWidget widget : Screens.getButtons((Screen)(Object) this)) {
            if (widgetMatchesKey(widget, "options.mouse_settings")) {
                yOffsetLeft = changeWidget(widget, HIDE_MOUSE, yOffsetLeft);
            }
            if (widget.getMessage().contains(Text.translatable("key.sneak"))) {
                yOffsetLeft = changeWidget(widget, HIDE_OPTIONS_GAMEMENU, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "options.autoJump")) {
                yOffsetLeft = changeWidget(widget, HIDE_AUTOJUMP, yOffsetLeft);
            }
            if (widgetMatchesKey(widget, "controls.keybinds")) {
                yOffsetRight = changeWidget(widget, HIDE_KEYBINDS, yOffsetRight);
            }
            if (widgetMatchesKey(widget, "menu.key.sprint")) {
                yOffsetRight = changeWidget(widget, HIDE_SPRINT, yOffsetRight);
            }
        }
    }

    private boolean widgetMatchesKey(ClickableWidget widget, String key) {
        return Objects.equals(widget.getMessage(), Text.translatable(key));
    }

    private int changeWidget(ClickableWidget widget, Boolean change, int yOffset) {
        if (change) {
            widget.visible = false;
            yOffset += 24;
        } else {
            widget.y -= yOffset;
        }
        return yOffset;
    }
}
