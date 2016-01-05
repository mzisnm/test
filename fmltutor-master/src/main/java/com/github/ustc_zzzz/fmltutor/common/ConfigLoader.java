package com.github.ustc_zzzz.fmltutor.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigLoader {
	protected static Logger log = LogManager.getLogger(ConfigLoader.class);
	private static Configuration config;

	public static int diamondBurnTime;

	public ConfigLoader(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		registerConfig();

		config.save();
	}

	private static void registerConfig() {
		diamondBurnTime = config.get(Configuration.CATEGORY_GENERAL, "diamondBurnTime", 640).getInt();
		config.getCategory("server");
	}
}
