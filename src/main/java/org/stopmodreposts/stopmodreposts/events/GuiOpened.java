package org.stopmodreposts.stopmodreposts.events;

import org.stopmodreposts.stopmodreposts.References;
import org.stopmodreposts.stopmodreposts.guis.InformationGui;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = References.MODID)
public class GuiOpened {

	@SubscribeEvent
	public static void guiInit(GuiOpenEvent event) {
		if(event.getGui() instanceof GuiMainMenu && References.shouldShowUp()) {
			event.setGui(new InformationGui(event.getGui()));
		}
	}
}
