package tk.diffusehyperion.toomanybuttons.gui;

import net.minecraft.client.gui.screen.Screen;
import org.javatuples.Pair;
import org.javatuples.Tuple;
import org.javatuples.Unit;

import java.util.ArrayList;
import java.util.List;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class EditTitleScreen extends ScreenWidgetEditor{
    public EditTitleScreen(Screen screen) {
        super(screen);
    }

    public void main() {
        List<Tuple> widgetList = new ArrayList<>();
        widgetList.add(new Unit<>(new Pair<>(HIDE_SINGLEPLAYER, getWidget("menu.singleplayer"))));
        widgetList.add(new Unit<>(new Pair<>(HIDE_MULTIPLAYER, getWidget("menu.multiplayer"))));
        widgetList.add(new Unit<>(new Pair<>(HIDE_REALMS, getWidget("menu.online"))));
        widgetList.add(new Unit<>(new Pair<>(HIDE_MODMENU_TITLESCREEN, getWidget("modmenu.title"))));
        widgetList.add(new Pair<>(new Pair<>(HIDE_OPTIONS_TITLESCREEN, getWidget("menu.options")),
                new Pair<>(HIDE_QUITGAME, getWidget("menu.quit"))));

        int yOffset = editWidgetScreen(widgetList, 200, 102);
        yOffset = editWidget(HIDE_LANGUAGE_TITLESCREEN, "narrator.button.language", yOffset);
        editWidget(HIDE_ACCESSIBILITY_TITLESCREEN, "narrator.button.accessibility", yOffset);
    }
}
