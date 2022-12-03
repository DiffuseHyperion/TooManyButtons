package tk.diffusehyperion.toomanybuttons.gui;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.text.Text;

import java.util.HashMap;

import static tk.diffusehyperion.toomanybuttons.TooManyButtons.logger;

public class ScreenWidgetEditor {

    public final HashMap<String, ClickableWidget> messageToWidget = new HashMap<>();
    public final Screen screen;
    public ScreenWidgetEditor(Screen screen) {
        for (ClickableWidget widget : Screens.getButtons(screen)) {
            messageToWidget.put(widget.getMessage().getString(), widget);
        }
        this.screen = screen;
    }

    public void hideKeyedWidget(String key) {
        messageToWidget.get(Text.translatable(key).getString()).visible = false;
    }
    public void moveLeftOfKeyedWidget(String key, int x) {
        messageToWidget.get(Text.translatable(key).getString()).x -= x;
    }
    public void moveRightOfKeyedWidget(String key, int x) {
        messageToWidget.get(Text.translatable(key).getString()).x += x;
    }
    public void moveUpKeyedWidget(String key, int y) {
        messageToWidget.get(Text.translatable(key).getString()).y -= y ;
    }
    public void moveDownKeyedWidget(String key, int y) {
        messageToWidget.get(Text.translatable(key).getString()).y += y ;
    }
    public void setWidthOfKeyedWidget(String key, int width) {
        messageToWidget.get(Text.translatable(key).getString()).setWidth(width);
    }
    public ClickableWidget getWidget(String key) {
        return messageToWidget.get(Text.translatable(key).getString());
    }
    public int editWidget(Boolean condition, String key, int offset) {
        if (condition) {
            hideKeyedWidget(key);
            offset += 24;
        } else {
            moveDownKeyedWidget(key, offset);
        }
        return offset;
    }

    /**
     * @apiNote Method presumes there is and only 1 widget that contains the key.
     */
    public ClickableWidget getWidgetContainingKey(String key) {
        for (ClickableWidget widget : Screens.getButtons(screen)) {
            logger.info("checking widget msg: " + widget.getMessage().getString());
            if (widget.getMessage().contains(Text.translatable(key))) {
                logger.info("hit");
                return widget;
            } else {
                logger.info("no hit");
            }
        }
        throw new RuntimeException("TooManyButtons: Something went wrong trying to hide buttons! Error: Could not find a widget containing the key: " + key);
    }

    /**
     * @apiNote Method presumes there is and only 1 widget that contains the lock button.
     */
    public ClickableWidget getLockableWidget() {
        for (ClickableWidget widget : Screens.getButtons(screen)) {
            if (widget instanceof LockButtonWidget) {
                return widget;
            }
        }
        throw new RuntimeException("TooManyButtons: Something went wrong trying to hide buttons! Error: Could not find lock button widget.");
    }
}
