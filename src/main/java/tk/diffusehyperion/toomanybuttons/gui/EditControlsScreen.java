package tk.diffusehyperion.toomanybuttons.gui;

import net.minecraft.client.gui.screen.Screen;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class EditControlsScreen extends ScreenWidgetEditor {
    public EditControlsScreen(Screen screen) {
        super(screen);
    }

    public void main() {
        int yOffsetLeft = 0;
        // includes mouse, sneak, auto
        int yOffsetRight = 0;
        // includes keybinds, sprint
        yOffsetLeft = editWidget(HIDE_MOUSE, "options.mouse_settings", yOffsetLeft);
        yOffsetLeft = editWidget(HIDE_AUTOJUMP, "options.autoJump", yOffsetLeft);
        if (HIDE_OPTIONS_GAMEMENU) {
            getWidgetContainingKey("key.sneak").visible = false;
        } else {
            getWidgetContainingKey("key.sneak").y -= yOffsetLeft;
        }

        yOffsetRight = editWidget(HIDE_KEYBINDS, "controls.keybinds", yOffsetRight);
        editWidget(HIDE_SPRINT, "menu.key.sprint", yOffsetRight);
    }
}
