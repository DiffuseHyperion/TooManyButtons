package tk.diffusehyperion.toomanybuttons.gui;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.javatuples.Pair;
import org.javatuples.Tuple;
import org.javatuples.Unit;

import java.util.ArrayList;
import java.util.List;

import static com.terraformersmc.modmenu.config.ModMenuConfig.MODS_BUTTON_STYLE;
import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;
import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.HIDE_MODMENU_GAMEMENU;

public class EditGameScreen extends ScreenWidgetEditor{

    private final MinecraftClient client;
    public EditGameScreen(Screen screen, MinecraftClient client) {
        super(screen);
        this.client = client;
    }

    public void main() {
        if (MODS_BUTTON_STYLE.getValue().equals(ModMenuConfig.ModsButtonStyle.CLASSIC)) {
            moveDownKeyedWidget("modmenu.title", 48);
            moveUpKeyedWidget("menu.options", 24);
            if (client.isInSingleplayer()) {
                moveUpKeyedWidget("menu.shareToLan", 24);
                moveUpKeyedWidget("menu.returnToMenu", 24);
            } else {
                moveUpKeyedWidget("menu.playerReporting", 24);
                moveUpKeyedWidget("menu.disconnect", 24);
            }
        }
        // for some reason, mod menu button is smack in the middle of the menu between feedback and options,
        // so the mod moves it to below quit to title and move everything below original mod menu upwards

        List<Tuple> widgetList = new ArrayList<>();
        widgetList.add(new Unit<>(new Pair<>(HIDE_BACKTOGAME, getWidget("menu.returnToGame"))));
        widgetList.add(new Pair<>(new Pair<>(HIDE_ADVANCEMENT, getWidget("gui.advancements")), new Pair<>(HIDE_STATISTICS, getWidget("gui.stats"))));
        widgetList.add(new Pair<>(new Pair<>(HIDE_FEEDBACK, getWidget("menu.sendFeedback")), new Pair<>(HIDE_REPORT, getWidget("menu.reportBugs"))));
        if (client.isInSingleplayer()) {
            widgetList.add(new Pair<>(new Pair<>(HIDE_OPTIONS_GAMEMENU, getWidget("menu.options")), new Pair<>(HIDE_LAN, getWidget("menu.shareToLan"))));
            widgetList.add(new Unit<>(new Pair<>(HIDE_SAVEANDQUIT, getWidget("menu.returnToMenu"))));
        } else {
            widgetList.add(new Pair<>(new Pair<>(HIDE_OPTIONS_GAMEMENU, getWidget("menu.options")), new Pair<>(HIDE_REPORTING, getWidget("menu.playerReporting"))));
            widgetList.add(new Unit<>(new Pair<>(HIDE_DISCONNECT, getWidget("menu.disconnect"))));
        }
        if (MODS_BUTTON_STYLE.getValue().equals(ModMenuConfig.ModsButtonStyle.CLASSIC)) {
            widgetList.add(new Unit<>(new Pair<>(HIDE_MODMENU_GAMEMENU, getWidget("modmenu.title"))));
        }
        int yOffset = editWidgetScreen(widgetList, 205, 106);
        if (HIDE_MODMENU_GAMEMENU) {
            ClickableWidget widget = getWidget("modmenu.title");
            widget.visible = false;
            widget.y -= yOffset;
        }
    }
}
