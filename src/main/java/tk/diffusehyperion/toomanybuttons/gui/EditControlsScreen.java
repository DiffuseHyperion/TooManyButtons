package tk.diffusehyperion.toomanybuttons.gui;

import net.minecraft.client.gui.screen.Screen;
import org.javatuples.Pair;
import org.javatuples.Tuple;
import org.javatuples.Unit;

import java.util.ArrayList;
import java.util.List;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class EditControlsScreen extends ScreenWidgetEditor {
    public EditControlsScreen(Screen screen) {
        super(screen);
    }

    public void main() {
        List<Tuple> widgetList = new ArrayList<>();
        widgetList.add(new Pair<>(new Pair<>(HIDE_MOUSE, getWidget("options.mouse_settings")), new Pair<>(HIDE_KEYBINDS, getWidget("controls.keybinds"))));
        widgetList.add(new Pair<>(new Pair<>(HIDE_SNEAK, getWidgetContainingKey("key.sneak")), new Pair<>(HIDE_SPRINT, getWidgetContainingKey("key.sprint"))));
        widgetList.add(new Unit<>(new Pair<>(HIDE_AUTOJUMP, getWidgetContainingKey("options.autoJump"))));
        editWidgetScreen(widgetList, 310, 160);
    }
}
