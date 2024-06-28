package net.pm.specifics;

import eu.midnightdust.lib.config.MidnightConfig;

public class SpecificsConfig extends MidnightConfig {
    //Specific Configs
    @Comment(category = "general") public static Comment category;
    @Entry(category = "general", min = 0) public static boolean endPortal = false;

}
