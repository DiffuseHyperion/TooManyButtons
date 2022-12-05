package tk.diffusehyperion.toomanybuttons.gui;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.javatuples.Pair;
import org.javatuples.Tuple;

import java.util.ArrayList;
import java.util.List;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class EditOptionsScreen extends ScreenWidgetEditor{

    private final MinecraftClient client;

    public EditOptionsScreen(Screen screen, MinecraftClient client) {
        super(screen);
        this.client = client;
    }

    public void main() {
        // options.difficulty, options.fov, options.online


        // deal with top row (fov and difficulty) manually since difficulty and lock button are separate widgets
        if (client.isInSingleplayer()) {
            if (HIDE_FOV && HIDE_DIFFICULTY) {
                for (ClickableWidget widget : Screens.getButtons(screen)) {
                    widget.y -= 24;
                    // it's fine to move up every widget since difficulty and fov would be hidden anyway
                }
            } else if (HIDE_FOV) {
                getWidgetContainingKey("options.fov").visible = false;

                ClickableWidget widget = getWidgetContainingKey("options.difficulty");
                widget.x -= 160;
                widget.setWidth(290);
            } else if (HIDE_DIFFICULTY) {
                getWidgetContainingKey("options.difficulty").visible = false;
                getLockableWidget().visible = false;
                ClickableWidget widget = getWidgetContainingKey("options.fov");
                widget.setWidth(310);
            }
        } else {
            editWidgetRow(new Pair<>(new Pair<>(HIDE_FOV, getWidgetContainingKey("options.fov")), new Pair<>(HIDE_ONLINE, getWidget("options.online"))), 310, 160);
        }

        List<Tuple> widgetList = new ArrayList<>();
        widgetList.add(new Pair<>(new Pair<>(HIDE_SKINS, getWidget("options.skinCustomisation")), new Pair<>(HIDE_MUSIC, getWidget("options.sounds"))));
        widgetList.add(new Pair<>(new Pair<>(HIDE_VIDEO, getWidget("options.video")), new Pair<>(HIDE_CONTROLS, getWidget("options.controls"))));
        widgetList.add(new Pair<>(new Pair<>(HIDE_LANGUAGE_OPTIONSMENU, getWidget("options.language")), new Pair<>(HIDE_CHAT, getWidget("options.chat.title"))));
        widgetList.add(new Pair<>(new Pair<>(HIDE_RESOURCE, getWidget("options.resourcepack")), new Pair<>(HIDE_ACCESSIBILITY_OPTIONSMENU, getWidget("options.accessibility.title"))));
        editWidgetScreen(widgetList, 310, 160);
    }
}
