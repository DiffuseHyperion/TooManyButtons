package tk.diffusehyperion.toomanybuttons.mixin;

import com.mojang.serialization.Codec;
import com.terraformersmc.modmenu.config.option.ConfigOptionStorage;
import com.terraformersmc.modmenu.config.option.EnumConfigOption;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Mixin(EnumConfigOption.class)
public abstract class ModMenuConfigManagerMixin<E extends Enum<E>>{

    @Shadow @Final private String key;

    @Shadow @Final private String translationKey;

    @Shadow @Final private Class<E> enumClass;

    @Inject(method = "asOption", at = @At("TAIL"), remap = false, cancellable = true)
    private void injectAsOptionMethod(CallbackInfoReturnable<SimpleOption<?>> cir) {
        if (this.translationKey.equals("option.modmenu.mods_button_style")) {
            List<E> newOptions = new LinkedList<>(Arrays.asList(enumClass.getEnumConstants()));
            // Lists from Arrays.asList cannot do any operations that would modify its size, e.g. adding, removing elements
            // LinkedList fixes that
            SimpleOption<E> option = new SimpleOption<>(
                    translationKey,
                    SimpleOption.constantTooltip(Text.translatable("config.toomanybuttons.modmenu.warning.body")),
                    (text, value) -> Text.translatable(translationKey + "." + value.name().toLowerCase(Locale.ROOT)),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(newOptions,
                            Codec.STRING.xmap(
                                    string -> Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.name().toLowerCase().equals(string)).findAny().orElse(null),
                                    newValue -> newValue.name().toLowerCase()
                            )),
                    ConfigOptionStorage.getEnum(key, enumClass),
                    value -> ConfigOptionStorage.setEnum(key, value));
            cir.setReturnValue(option);
        }
    }

    // this mixin is here to prevent any unsupported
}
