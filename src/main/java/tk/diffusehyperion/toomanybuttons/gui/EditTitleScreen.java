package tk.diffusehyperion.toomanybuttons.gui;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import net.minecraft.client.gui.screen.Screen;
import org.javatuples.Pair;
import org.javatuples.Tuple;
import org.javatuples.Unit;

import java.util.ArrayList;
import java.util.List;

import static com.terraformersmc.modmenu.config.ModMenuConfig.MODS_BUTTON_STYLE;
import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class EditTitleScreen extends ScreenWidgetEditor{
    public EditTitleScreen(Screen screen) {
        super(screen);
    }

    public void main() {
        List<Tuple> widgetList = new ArrayList<>();
        widgetList.add(new Unit<>(new Pair<>(HIDE_SINGLEPLAYER, getWidget("menu.singleplayer"))));
        widgetList.add(new Unit<>(new Pair<>(HIDE_MULTIPLAYER, getWidget("menu.multiplayer"))));
        // mod menu button styles:
        // if classic -> mod button below realms
        // if shrink -> mod button next to realms
        // if replace_realms -> mod button replaces realms
        // if icon -> mod button in separate column as icon
        if (MODS_BUTTON_STYLE.getValue().equals(ModMenuConfig.ModsButtonStyle.SHRINK)) {
            widgetList.add(new Pair<>(new Pair<>(HIDE_REALMS, getWidget("menu.online")), new Pair<>(HIDE_MODMENU_TITLESCREEN, getWidget("modmenu.title"))));
        } else if (!MODS_BUTTON_STYLE.getValue().equals(ModMenuConfig.ModsButtonStyle.REPLACE_REALMS)) {
            widgetList.add(new Unit<>(new Pair<>(HIDE_REALMS, getWidget("menu.online"))));
        }
        // for realms, if mod button style is shrink, add it as new pair with mod menu,
        // otherwise, if its not replace realms add it as unit
        if (!MODS_BUTTON_STYLE.getValue().equals(ModMenuConfig.ModsButtonStyle.ICON) || !MODS_BUTTON_STYLE.getValue().equals(ModMenuConfig.ModsButtonStyle.SHRINK)) {
            widgetList.add(new Unit<>(new Pair<>(HIDE_MODMENU_TITLESCREEN, getWidget("modmenu.title"))));
        } else if (MODS_BUTTON_STYLE.getValue().equals(ModMenuConfig.ModsButtonStyle.ICON) && HIDE_MODMENU_TITLESCREEN) {
            getWidget("modmenu.title").visible = false;
        }
        // for mod menu, if it's not icon (since it will mess up offset) or shrink (since it was already added), add it as unit
        // else if is for hiding it manually
        widgetList.add(new Pair<>(new Pair<>(HIDE_OPTIONS_TITLESCREEN, getWidget("menu.options")),
                new Pair<>(HIDE_QUITGAME, getWidget("menu.quit"))));
        int yOffset = editWidgetScreen(widgetList, 200, 102);
        yOffset = editWidget(HIDE_LANGUAGE_TITLESCREEN, "narrator.button.language", yOffset);
        editWidget(HIDE_ACCESSIBILITY_TITLESCREEN, "narrator.button.accessibility", yOffset);
    }
}
