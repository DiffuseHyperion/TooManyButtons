package tk.diffusehyperion.toomanybuttons.gui;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class EditOptionsScreen extends ScreenWidgetEditor{

    private final MinecraftClient client;

    public EditOptionsScreen(Screen screen, MinecraftClient client) {
        super(screen);
        this.client = client;
    }

    public void main() {
        if (client.isInSingleplayer()) {
            if (HIDE_FOV && HIDE_DIFFICULTY) {
                for (ClickableWidget widget : Screens.getButtons(screen)) {
                    widget.y -= 24;
                    // it's fine to move up every widget since difficulty and fov would be hidden anyway
                }
            } else if (HIDE_FOV) {
                ClickableWidget widget = getWidgetContainingKey("options.difficulty");
                widget.x -= 160;
                widget.setWidth(290);
            } else if (HIDE_DIFFICULTY) {
                ClickableWidget widget = getWidgetContainingKey("options.fov");
                widget.setWidth(310);
            }
        } else {
            if (HIDE_FOV && HIDE_ONLINE) {
                for (ClickableWidget widget : Screens.getButtons(screen)) {
                    widget.y -= 24;
                    // it's fine to move up every widget since difficulty and fov would be hidden anyway
                }
            } else if (HIDE_FOV) {
                moveLeftOfKeyedWidget("options.online", 160);
                setWidthOfKeyedWidget("options.online", 310);
            } else if (HIDE_ONLINE) {
                setWidthOfKeyedWidget("options.fov", 310);
            }
        }

        if (client.isInSingleplayer()) {
            if (HIDE_DIFFICULTY) {
                getWidgetContainingKey("options.difficulty").visible = false;
                getLockableWidget().visible = false;
            }
            if (HIDE_FOV) {
                getWidgetContainingKey("options.fov").visible = false;
            }
        } else {
            if (HIDE_ONLINE) {
                getWidget("options.online").visible = false;
            }
        }

        int yOffsetLeft = 0;
        // includes skin, video, lang, resource
        int yOffsetRight = 0;
        // includes music, controls, chat, accessibility
        yOffsetLeft = editWidget(HIDE_SKINS, "options.skinCustomisation", yOffsetLeft);
        yOffsetLeft = editWidget(HIDE_VIDEO, "options.video", yOffsetLeft);
        yOffsetLeft = editWidget(HIDE_LANGUAGE_OPTIONSMENU, "options.language", yOffsetLeft);
        editWidget(HIDE_RESOURCE, "options.resourcepack", yOffsetLeft);

        yOffsetRight = editWidget(HIDE_MUSIC, "options.sounds", yOffsetRight);
        yOffsetRight = editWidget(HIDE_CHAT, "options.chat.title", yOffsetRight);
        yOffsetRight = editWidget(HIDE_ACCESSIBILITY_OPTIONSMENU, "options.accessibility.title", yOffsetRight);
        if (!SIMPLIFY_CONTROLS) {
            editWidget(HIDE_CONTROLS, "options.controls", yOffsetRight);
        }
    }
}
