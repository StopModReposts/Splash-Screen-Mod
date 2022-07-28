package org.stopmodreposts.stopmodreposts;

import java.io.File;

import org.stopmodreposts.stopmodreposts.guis.InformationGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(StopModReposts.modid)
public class StopModReposts {

	public static final String modid = "stopmodreposts";

	public static File STOP_REPOST_FILE = new File("StopModReposts.info");

	public StopModReposts() {
		this.STOP_REPOST_FILE = new File("StopModReposts.info");
		MinecraftForge.EVENT_BUS.register(this.getClass());
	}

	@SubscribeEvent
	public static void showScreen(ScreenEvent.Init event) {
		if (event.getScreen() instanceof TitleScreen && !StopModReposts.STOP_REPOST_FILE.exists()) {
			Minecraft.getInstance().setScreen(new InformationGui(event.getScreen()));
		}
	}
}
