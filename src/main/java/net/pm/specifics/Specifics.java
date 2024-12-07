package net.pm.specifics;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.pm.specifics.block.Blocks;
import net.pm.specifics.block.entity.BlockEntityTypes;
import net.pm.specifics.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Specifics implements ModInitializer {
	public static final String MOD_ID = "specifics";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, SpecificsConfig.class);
		Blocks.init();
		ItemGroups.init();
		BlockEntityTypes.init();
	}
}