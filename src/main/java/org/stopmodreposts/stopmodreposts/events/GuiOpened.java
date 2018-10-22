package org.stopmodreposts.stopmodreposts.events;

import org.stopmodreposts.stopmodreposts.StopModReposts;
import org.stopmodreposts.stopmodreposts.guis.InformationGui;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiOpened {

	@SubscribeEvent/*(priority = EventPriority.LOWEST) TODO talk with CMM creator to fix black screen with this */
	public void guiInit(GuiOpenEvent event) {
		if(event.getGui() instanceof GuiMainMenu && StopModReposts.shouldShowUp()) {
			event.setGui(new InformationGui(event.getGui()));
		}
	}
}
