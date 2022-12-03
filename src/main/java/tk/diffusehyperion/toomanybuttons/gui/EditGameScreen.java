package tk.diffusehyperion.toomanybuttons.gui;

import net.minecraft.client.gui.screen.Screen;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;
import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.HIDE_MODMENU_GAMEMENU;

public class EditGameScreen extends ScreenWidgetEditor{
    public EditGameScreen(Screen screen) {
        super(screen);
    }

    public void main() {
        moveDownKeyedWidget("modmenu.title", 48);
        moveUpKeyedWidget("menu.options", 24);
        moveUpKeyedWidget("menu.shareToLan", 24);
        moveUpKeyedWidget("menu.playerReporting", 24);
        moveUpKeyedWidget("menu.returnToMenu", 24);
        moveUpKeyedWidget("menu.disconnect", 24);
        // for some reason, mod menu button is smack in the middle of the menu between feedback and options,
        // so the mod moves it to below quit to title and move everything below original mod menu upwards

        // back to game
        // advancements - statistics
        // feedback - report bugs
        // options - lan/report players
        // disconnect
        // mods

        // when 1 row has only 1 button, extend other button to take up the entire row
        // if the entire row is empty, move all buttons below upwards by 1 row
        int yOffsetLeft = 0;
        // includes advancements, give feedback, options
        int yOffsetRight = 0;
        // includes statistics, report bugs, lan/report players
        yOffsetLeft = editWidget(HIDE_ADVANCEMENT, "gui.advancements", yOffsetLeft);
        yOffsetLeft = editWidget(HIDE_FEEDBACK, "menu.sendFeedback", yOffsetLeft);
        editWidget(HIDE_OPTIONS_GAMEMENU, "menu.options", yOffsetLeft);

        yOffsetRight = editWidget(HIDE_STATISTICS, "gui.stats", yOffsetRight);
        yOffsetRight = editWidget(HIDE_REPORT, "menu.reportBugs", yOffsetRight);
        yOffsetRight = editWidget(HIDE_LAN, "menu.shareToLan", yOffsetRight);
        editWidget(HIDE_REPORTING, "menu.playerReporting", yOffsetRight);

        if (HIDE_MODMENU_GAMEMENU) {
            hideKeyedWidget("modmenu.title");
        }
    }
}
