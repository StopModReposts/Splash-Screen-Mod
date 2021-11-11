package org.stopmodreposts.stopmodreposts;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stopmodreposts.stopmodreposts.events.GuiOpened;

import java.io.File;

@Mod(modid = StopModReposts.MODID)
public class StopModReposts {
	public static final String MODID = "stopmodreposts";

	private static boolean hasShown = false;
	public static File STOP_REPOST_FILE;

	public static Logger LOGGER = null;

	public static boolean shouldShowUp() {
		if(!hasShown) {
			hasShown = true;
			return true;
		}else {
			//Uncomment bottom one, to get this displayed in dev environment.
			return false;
			//return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		}

	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if(!Loader.isModLoaded("Stop Mod Reposts"))
			MinecraftForge.EVENT_BUS.register(new GuiOpened());

		LOGGER = LogManager.getLogger(MODID);

		STOP_REPOST_FILE = new File("./StopModReposts.info");
		hasShown = STOP_REPOST_FILE.exists();
	}
}
