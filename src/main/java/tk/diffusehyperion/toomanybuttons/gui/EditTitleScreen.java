package tk.diffusehyperion.toomanybuttons.gui;

import net.minecraft.client.gui.screen.Screen;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class EditTitleScreen extends ScreenWidgetEditor{
    public EditTitleScreen(Screen screen) {
        super(screen);
    }

    public void main() {
        int yOffset = 0;
        yOffset = editWidget(HIDE_SINGLEPLAYER, "menu.singleplayer", yOffset);
        yOffset = editWidget(HIDE_MULTIPLAYER, "menu.multiplayer", yOffset);
        yOffset = editWidget(HIDE_REALMS, "menu.online", yOffset);
        yOffset = editWidget(HIDE_MODMENU_TITLESCREEN, "modmenu.title", yOffset);
        yOffset = editWidget(HIDE_OPTIONS_TITLESCREEN, "menu.options", yOffset);
        yOffset = editWidget(HIDE_QUITGAME, "menu.quit", yOffset);
        yOffset = editWidget(HIDE_LANGUAGE_TITLESCREEN, "narrator.button.language", yOffset);
        editWidget(HIDE_ACCESSIBILITY_TITLESCREEN, "narrator.button.accessibility", yOffset);
    }
}
