package tk.diffusehyperion.toomanybuttons.gui;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.text.Text;
import org.javatuples.Pair;
import org.javatuples.Tuple;
import org.javatuples.Unit;

import java.util.HashMap;
import java.util.List;

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
            moveUpKeyedWidget(key, offset);
        }
        return offset;
    }

    /**
     * @apiNote Method presumes that
     * the row has only 2 widgets and
     * the first value in the pair is the left widget within the row and vice versa
     *
     * @return Returns if the row is completely invisible
     */
    public boolean editWidgetRow(Pair<Pair<Boolean, ClickableWidget>, Pair<Boolean, ClickableWidget>> booleanToWidget, int width, int xOffset) {
        boolean leftBoolean = booleanToWidget.getValue0().getValue0();
        boolean rightBoolean = booleanToWidget.getValue1().getValue0();
        ClickableWidget leftWidget = booleanToWidget.getValue0().getValue1();
        ClickableWidget rightWidget = booleanToWidget.getValue1().getValue1();
        if (leftBoolean & rightBoolean) {
            leftWidget.visible = false;
            rightWidget.visible = false;
            return true;
        }
        if (rightBoolean) {
            rightWidget.visible = false;
            leftWidget.setWidth(width);
        }
        if (leftBoolean) {
            leftWidget.visible = false;
            rightWidget.x -= xOffset; //160
            rightWidget.setWidth(width); //310
        }
        return false;
    }

    /**
     * @apiNote Unit<Boolean, Widget>, Pair<Pair<Boolean, Widget>, Pair<Boolean, Widget>>
     * @implNote Add the elements in the argument list from top to bottom.
     * @implSpec Use a different tuple for each row; Unit for a row containing 1 widget and Pairs for 2 widgets. There is no >2 widgets per row anywhere in MC (i think)
     * @return Returns button Y offset
     */
    public int editWidgetScreen(List<Tuple> widgets, int width, int xOffset) {
        int offsetY = 0;
        for (Tuple tuple : widgets) {
            switch (tuple.getSize()) {
                case 1 -> {
                    Unit<Pair<Boolean, ClickableWidget>> clickableWidgetUnit = (Unit<Pair<Boolean, ClickableWidget>>) tuple;
                    ClickableWidget widget = clickableWidgetUnit.getValue0().getValue1();
                    boolean bool = clickableWidgetUnit.getValue0().getValue0();
                    if (bool) {
                        offsetY += 24;
                        widget.visible = false;
                    } else {
                        widget.y -= offsetY;
                    }
                }
                case 2 -> {
                    Pair<Pair<Boolean, ClickableWidget>, Pair<Boolean, ClickableWidget>> clickableWidgetPair = (Pair<Pair<Boolean, ClickableWidget>, Pair<Boolean, ClickableWidget>>) tuple;
                    if (editWidgetRow(clickableWidgetPair, width, xOffset)) {
                        offsetY += 24;
                    } else {
                        clickableWidgetPair.getValue0().getValue1().y -= offsetY;
                        clickableWidgetPair.getValue1().getValue1().y -= offsetY;
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + tuple.getSize());
            }
        }
        return offsetY;
    }

    /**
     * @apiNote Method presumes there is and only 1 widget that contains the key.
     */
    public ClickableWidget getWidgetContainingKey(String key) {
        for (ClickableWidget widget : Screens.getButtons(screen)) {
            if (widget.getMessage().contains(Text.translatable(key))) {
                return widget;
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
