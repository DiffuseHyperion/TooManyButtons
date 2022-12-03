package tk.diffusehyperion.toomanybuttons.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import static tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler.*;

public class ClothConfig {
    public static Screen getConfigScreen(Screen parentScreen) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parentScreen)
                .setTitle(Text.translatable("title.toomanybuttons.config"));

        builder.setSavingRunnable(ClothConfigHandler::write);

        ConfigCategory titleScreenConfig = builder.getOrCreateCategory(Text.translatable("config.toomanybuttons.titlescreen"));
        ConfigCategory gameMenuConfig = builder.getOrCreateCategory(Text.translatable("config.toomanybuttons.gamemenu"));
        ConfigCategory optionsMenuConfig = builder.getOrCreateCategory(Text.translatable("config.toomanybuttons.optionsmenu"));
        ConfigCategory controlsMenuConfig = builder.getOrCreateCategory(Text.translatable("config.toomanybuttons.controlsmenu"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.singleplayer"), HIDE_SINGLEPLAYER)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_SINGLEPLAYER = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.singleplayer.tooltip"))
                .build());
        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.multiplayer"), HIDE_MULTIPLAYER)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_MULTIPLAYER = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.multiplayer.tooltip"))
                .build());
        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.realms"), HIDE_REALMS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_REALMS = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.realms.tooltip"))
                .build());
        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.modmenu"), HIDE_MODMENU_TITLESCREEN)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_MODMENU_TITLESCREEN = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.modmenu.tooltip"))
                .build());
        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.quitgame"), HIDE_QUITGAME)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_QUITGAME = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.quitgame.tooltip"))
                .build());
        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.language"), HIDE_LANGUAGE_TITLESCREEN)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_LANGUAGE_TITLESCREEN = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.language.tooltip"))
                .build());
        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.accessibility"), HIDE_ACCESSIBILITY_TITLESCREEN)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_ACCESSIBILITY_TITLESCREEN = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.accessibility.tooltip"))
                .build());
        titleScreenConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.titlescreen.copyright"), HIDE_COPYRIGHT)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_COPYRIGHT = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.titlescreen.copyright.tooltip"))
                .build());



        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.advancement"), HIDE_ADVANCEMENT)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_ADVANCEMENT = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.advancement.tooltip"))
                .build());
        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.statistics"), HIDE_STATISTICS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_STATISTICS = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.statistics.tooltip"))
                .build());
        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.feedback"), HIDE_FEEDBACK)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_FEEDBACK = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.feedback.tooltip"))
                .build());
        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.report"), HIDE_REPORT)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_REPORT = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.report.tooltip"))
                .build());
        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.modmenu"), HIDE_MODMENU_GAMEMENU)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_MODMENU_GAMEMENU = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.modmenu.tooltip"))
                .build());
        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.options"), HIDE_OPTIONS_GAMEMENU)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_OPTIONS_GAMEMENU = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.options.tooltip"))
                .build());
        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.lan"), HIDE_LAN)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_LAN = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.lan.tooltip"))
                .build());
        gameMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.gamemenu.reporting"), HIDE_REPORTING)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_REPORTING = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.gamemenu.reporting.tooltip"))
                .build());



        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.fov"), HIDE_FOV)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_FOV = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.fov.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.difficulty"), HIDE_DIFFICULTY)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_DIFFICULTY = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.difficulty.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.online"), HIDE_ONLINE)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_ONLINE = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.online.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.skin"), HIDE_SKINS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_SKINS = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.skin.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.music"), HIDE_MUSIC)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_MUSIC = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.music.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.video"), HIDE_VIDEO)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_VIDEO = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.video.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.controls"), HIDE_CONTROLS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_CONTROLS = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.controls.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.language"), HIDE_LANGUAGE_OPTIONSMENU)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_LANGUAGE_OPTIONSMENU = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.language.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.chat"), HIDE_CHAT)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_CHAT = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.chat.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.resource"), HIDE_RESOURCE)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_RESOURCE = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.resource.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.accessibility"), HIDE_ACCESSIBILITY_OPTIONSMENU)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_ACCESSIBILITY_OPTIONSMENU = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.accessibility.tooltip"))
                .build());
        optionsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.optionsmenu.parity"), TITLE_PARITY)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> TITLE_PARITY = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.optionsmenu.parity.tooltip1"), Text.translatable("config.toomanybuttons.optionsmenu.parity.tooltip2"), Text.translatable("config.toomanybuttons.optionsmenu.parity.tooltip3"))
                .build());



        controlsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.controlsmenu.simplify"), SIMPLIFY_CONTROLS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> SIMPLIFY_CONTROLS = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.controlsmenu.simplify.tooltip1"), Text.translatable("config.toomanybuttons.controlsmenu.simplify.tooltip2"))
                .build());
        controlsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.controlsmenu.mouse"), HIDE_MOUSE)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_MOUSE = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.controlsmenu.mouse.tooltip"))
                .build());
        controlsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.controlsmenu.keybinds"), HIDE_KEYBINDS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_KEYBINDS = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.controlsmenu.keybinds.tooltip"))
                .build());
        controlsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.controlsmenu.sneak"), HIDE_SNEAK)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_SNEAK = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.controlsmenu.sneak.tooltip"))
                .build());
        controlsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.controlsmenu.sprint"), HIDE_SPRINT)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_SPRINT = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.controlsmenu.sprint.tooltip"))
                .build());
        controlsMenuConfig.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.toomanybuttons.controlsmenu.autojump"), HIDE_AUTOJUMP)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HIDE_AUTOJUMP = newValue)
                .setTooltip(Text.translatable("config.toomanybuttons.controlsmenu.autojump.tooltip"))
                .build());
        return builder.build();
    }
}
