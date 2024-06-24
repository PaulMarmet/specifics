package net.pm;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Specifics implements ModInitializer {
	public static final String MOD_ID = "specifics";
    public static final Logger LOGGER = LoggerFactory.getLogger("specifics");

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, SpecificsConfig.class);
	}
}