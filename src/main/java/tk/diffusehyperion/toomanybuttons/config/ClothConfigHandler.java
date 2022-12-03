package tk.diffusehyperion.toomanybuttons.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static com.google.gson.JsonParser.parseReader;

public class ClothConfigHandler {
    // titlescreen
    public static boolean HIDE_SINGLEPLAYER;
    public static boolean HIDE_MULTIPLAYER;
    public static boolean HIDE_REALMS;
    public static boolean HIDE_MODMENU_TITLESCREEN;
    public static boolean HIDE_OPTIONS_TITLESCREEN;
    public static boolean HIDE_QUITGAME;
    public static boolean HIDE_LANGUAGE_TITLESCREEN;
    public static boolean HIDE_ACCESSIBILITY_TITLESCREEN;
    public static boolean HIDE_COPYRIGHT;

    // gamemenu
    public static boolean HIDE_ADVANCEMENT;
    public static boolean HIDE_STATISTICS;
    public static boolean HIDE_FEEDBACK;
    public static boolean HIDE_REPORT;
    public static boolean HIDE_MODMENU_GAMEMENU;
    public static boolean HIDE_OPTIONS_GAMEMENU;
    public static boolean HIDE_LAN;
    public static boolean HIDE_REPORTING;

    // options
    public static boolean HIDE_FOV;
    public static boolean HIDE_DIFFICULTY;
    public static boolean HIDE_ONLINE;
    public static boolean HIDE_SKINS;
    public static boolean HIDE_MUSIC;
    public static boolean HIDE_VIDEO;
    public static boolean HIDE_CONTROLS;
    public static boolean HIDE_LANGUAGE_OPTIONSMENU;
    public static boolean HIDE_CHAT;
    public static boolean HIDE_RESOURCE;
    public static boolean HIDE_ACCESSIBILITY_OPTIONSMENU;
    public static boolean TITLE_PARITY;

    // controls
    public static boolean SIMPLIFY_CONTROLS;
    public static boolean HIDE_MOUSE;
    public static boolean HIDE_KEYBINDS;
    public static boolean HIDE_SNEAK;
    public static boolean HIDE_SPRINT;
    public static boolean HIDE_AUTOJUMP;

    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("toomanybuttons.json");

    public static void write() {
        try (
                final FileWriter fw = new FileWriter(configPath.toFile());
                final JsonWriter jw = new JsonWriter(fw)
        ) {
            jw.setIndent("    ");
            jw.beginObject()
                    .name("HIDE_SINGLEPLAYER").value(HIDE_SINGLEPLAYER)
                    .name("HIDE_MULTIPLAYER").value(HIDE_MULTIPLAYER)
                    .name("HIDE_REALMS").value(HIDE_REALMS)
                    .name("HIDE_MODMENU_TITLESCREEN").value(HIDE_MODMENU_TITLESCREEN)
                    .name("HIDE_OPTIONS_TITLESCREEN").value(HIDE_OPTIONS_TITLESCREEN)
                    .name("HIDE_QUITGAME").value(HIDE_QUITGAME)
                    .name("HIDE_LANGUAGE_TITLESCREEN").value(HIDE_LANGUAGE_TITLESCREEN)
                    .name("HIDE_ACCESSIBILITY_TITLESCREEN").value(HIDE_ACCESSIBILITY_TITLESCREEN)
                    .name("HIDE_COPYRIGHT").value(HIDE_COPYRIGHT)

                    .name("HIDE_ADVANCEMENT").value(HIDE_ADVANCEMENT)
                    .name("HIDE_STATISTICS").value(HIDE_STATISTICS)
                    .name("HIDE_FEEDBACK").value(HIDE_FEEDBACK)
                    .name("HIDE_REPORT").value(HIDE_REPORT)
                    .name("HIDE_MODMENU_GAMEMENU").value(HIDE_MODMENU_GAMEMENU)
                    .name("HIDE_OPTIONS_GAMEMENU").value(HIDE_OPTIONS_GAMEMENU)
                    .name("HIDE_LAN").value(HIDE_LAN)
                    .name("HIDE_REPORTING").value(HIDE_REPORTING)

                    .name("HIDE_FOV").value(HIDE_FOV)
                    .name("HIDE_DIFFICULTY").value(HIDE_DIFFICULTY)
                    .name("HIDE_ONLINE").value(HIDE_ONLINE)
                    .name("HIDE_SKINS").value(HIDE_SKINS)
                    .name("HIDE_MUSIC").value(HIDE_MUSIC)
                    .name("HIDE_VIDEO").value(HIDE_VIDEO)
                    .name("HIDE_CONTROLS").value(HIDE_CONTROLS)
                    .name("HIDE_LANGUAGE_OPTIONSMENU").value(HIDE_LANGUAGE_OPTIONSMENU)
                    .name("HIDE_CHAT").value(HIDE_CHAT)
                    .name("HIDE_RESOURCE").value(HIDE_RESOURCE)
                    .name("HIDE_ACCESSIBILITY_OPTIONSMENU").value(HIDE_ACCESSIBILITY_OPTIONSMENU)
                    .name("TITLE_PARITY").value(TITLE_PARITY)

                    .name("SIMPLIFY_CONTROLS").value(SIMPLIFY_CONTROLS)
                    .name("HIDE_MOUSE").value(HIDE_MOUSE)
                    .name("HIDE_KEYBINDS").value(HIDE_KEYBINDS)
                    .name("HIDE_SNEAK").value(HIDE_SNEAK)
                    .name("HIDE_SPRINT").value(HIDE_SPRINT)
                    .name("HIDE_AUTOJUMP").value(HIDE_AUTOJUMP)
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // above and below code, plus readBoolean literally copy pasted from https://github.com/TomB-134/MinimalMenu/blob/main/src/main/java/minimalmenu/config/ConfigHandler.java, thanks :)

    public static void read() { //Runs on init to get data from json file.
        if (configPath.toFile().exists()) {
            try (final FileReader fr = new FileReader(configPath.toFile())) {
                final JsonElement je = parseReader(fr);
                if (!je.isJsonObject()) {
                    setDefaults();
                }

                final JsonObject object = je.getAsJsonObject();
                HIDE_SINGLEPLAYER = readBoolean(object, "HIDE_SINGLEPLAYER", false);
                HIDE_MULTIPLAYER = readBoolean(object, "HIDE_MULTIPLAYER", false);
                HIDE_REALMS = readBoolean(object, "HIDE_REALMS", false);
                HIDE_MODMENU_TITLESCREEN = readBoolean(object, "HIDE_MODMENU_TITLESCREEN", false);
                HIDE_OPTIONS_TITLESCREEN = readBoolean(object, "HIDE_OPTIONS_TITLESCREEN", false);
                HIDE_QUITGAME = readBoolean(object, "HIDE_QUITGAME", false);
                HIDE_LANGUAGE_TITLESCREEN = readBoolean(object, "HIDE_LANGUAGE_TITLESCREEN", false);
                HIDE_ACCESSIBILITY_TITLESCREEN = readBoolean(object, "HIDE_ACCESSIBILITY_TITLESCREEN", false);
                HIDE_COPYRIGHT = readBoolean(object, "HIDE_COPYRIGHT", false);

                HIDE_ADVANCEMENT = readBoolean(object, "HIDE_ADVANCEMENT", false);
                HIDE_STATISTICS = readBoolean(object, "HIDE_STATISTICS", false);
                HIDE_FEEDBACK = readBoolean(object, "HIDE_FEEDBACK", false);
                HIDE_REPORT = readBoolean(object, "HIDE_REPORT", false);
                HIDE_MODMENU_GAMEMENU = readBoolean(object, "HIDE_MODMENU_GAMEMENU", false);
                HIDE_OPTIONS_GAMEMENU = readBoolean(object, "HIDE_OPTIONS_GAMEMENU", false);
                HIDE_LAN = readBoolean(object, "HIDE_LAN", false);
                HIDE_REPORTING = readBoolean(object, "HIDE_REPORTING", false);

                HIDE_FOV = readBoolean(object, "HIDE_FOV", false);
                HIDE_DIFFICULTY = readBoolean(object, "HIDE_DIFFICULTY", false);
                HIDE_ONLINE = readBoolean(object, "HIDE_ONLINE", false);
                HIDE_SKINS = readBoolean(object, "HIDE_SKINS", false);
                HIDE_MUSIC = readBoolean(object, "HIDE_MUSIC", false);
                HIDE_VIDEO = readBoolean(object, "HIDE_VIDEO", false);
                HIDE_CONTROLS = readBoolean(object, "HIDE_CONTROLS", false);
                HIDE_LANGUAGE_OPTIONSMENU = readBoolean(object, "HIDE_LANGUAGE_OPTIONSMENU", false);
                HIDE_CHAT = readBoolean(object, "HIDE_CHAT", false);
                HIDE_RESOURCE = readBoolean(object, "HIDE_RESOURCE", false);
                HIDE_ACCESSIBILITY_OPTIONSMENU = readBoolean(object, "HIDE_ACCESSIBILITY_OPTIONSMENU", false);
                TITLE_PARITY = readBoolean(object, "TITLE_PARITY", false);

                SIMPLIFY_CONTROLS = readBoolean(object, "SIMPLIFY_CONTROLS", false);
                HIDE_MOUSE = readBoolean(object, "HIDE_MOUSE", false);
                HIDE_KEYBINDS = readBoolean(object, "HIDE_KEYBINDS", false);
                HIDE_SNEAK = readBoolean(object, "HIDE_SNEAK", false);
                HIDE_SPRINT = readBoolean(object, "HIDE_SPRINT", false);
                HIDE_AUTOJUMP = readBoolean(object, "HIDE_AUTOJUMP", false);

            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            setDefaults();
        }
    }

    private static void setDefaults() {
        HIDE_SINGLEPLAYER = false;
        HIDE_MULTIPLAYER = false;
        HIDE_REALMS = false;
        HIDE_MODMENU_TITLESCREEN = false;
        HIDE_OPTIONS_TITLESCREEN = false;
        HIDE_QUITGAME = false;
        HIDE_LANGUAGE_TITLESCREEN = false;
        HIDE_ACCESSIBILITY_TITLESCREEN = false;
        HIDE_COPYRIGHT = false;

        HIDE_ADVANCEMENT = false;
        HIDE_STATISTICS = false;
        HIDE_FEEDBACK = false;
        HIDE_REPORT = false;
        HIDE_MODMENU_GAMEMENU = false;
        HIDE_OPTIONS_GAMEMENU = false;
        HIDE_LAN = false;
        HIDE_REPORTING = false;

        HIDE_FOV = false;
        HIDE_DIFFICULTY = false;
        HIDE_ONLINE = false;
        HIDE_SKINS = false;
        HIDE_MUSIC = false;
        HIDE_VIDEO = false;
        HIDE_CONTROLS = false;
        HIDE_LANGUAGE_OPTIONSMENU = false;
        HIDE_CHAT = false;
        HIDE_RESOURCE = false;
        HIDE_ACCESSIBILITY_OPTIONSMENU = false;
        TITLE_PARITY = false;

        SIMPLIFY_CONTROLS = false;
        HIDE_MOUSE = false;
        HIDE_KEYBINDS = false;
        HIDE_SNEAK = false;
        HIDE_SPRINT = false;
        HIDE_AUTOJUMP = false;
    }

    private static boolean readBoolean(JsonObject json, String key, boolean fallback) {
        final JsonElement el = json.get(key);
        if (el == null || !el.isJsonPrimitive()) return fallback;
        try {
            return el.getAsBoolean();
        } catch (ClassCastException e) {
            return fallback;
        }
    }
}
