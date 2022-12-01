package tk.diffusehyperion.toomanybuttons;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.diffusehyperion.toomanybuttons.config.ClothConfigHandler;

public class TooManyButtons implements ClientModInitializer {

    public static final Logger logger = LoggerFactory.getLogger("TooManyButtons");
    @Override
    public void onInitializeClient() {
        ClothConfigHandler.read();
        logger.info("Mod loaded successfully!");
    }
}
