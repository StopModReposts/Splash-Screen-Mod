package org.stopmodreposts.stopmodreposts.events;

import org.stopmodreposts.stopmodreposts.StopModReposts;
import org.stopmodreposts.stopmodreposts.guis.InformationGui;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = StopModReposts.MODID)
public class GuiOpened {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void guiInit(GuiOpenEvent event) {
		if(event.getGui() instanceof GuiMainMenu && StopModReposts.shouldShowUp()) {
			event.setGui(new InformationGui(event.getGui()));
		}
	}
}
