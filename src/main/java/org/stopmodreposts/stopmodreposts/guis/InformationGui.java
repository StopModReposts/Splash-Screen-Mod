package org.stopmodreposts.stopmodreposts.guis;

import java.io.IOException;
import java.util.List;

import org.stopmodreposts.stopmodreposts.StopModReposts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;

public class InformationGui extends GuiScreen {

	private static final ResourceLocation SMR_LOGO = new ResourceLocation(StopModReposts.MODID, "textures/guis/smr_logo.png");
	private static final String TRANSLATION_BASE_KEY = "stopmodreposts.gui.information.";

	private FontRenderer fontRenderer = null;
	//The Instance of the MainMenu for compatibility with mods like CustomMainMenu
	private GuiScreen parent = null;

	private GuiButton btnMoreInfo = null;
	private GuiButton btnClose = null;

	private GuiLabel lblInformation = null;

	public InformationGui(GuiScreen parent) {
		this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
		this.parent = parent;
	}

	@Override
    public void initGui(){
		this.labelList.clear();
    	this.buttonList.clear();

    	this.lblInformation = new GuiLabel(this.fontRenderer, 0, 25, 50, this.width - 50, this.height - 80, 0xFFFFFFFF);

    	//Get Translated Text
    	String text = I18n.format(TRANSLATION_BASE_KEY.concat("information"));

    	//Split text into multiple lines, the seperateor = ";"
    	String[] multilineA = text.split("\\n");

    	//Further splits the strings and adds them to the label.
    	for(String strA : multilineA) {
	    	List<String> multilineB = this.fontRenderer.listFormattedStringToWidth(strA, this.width - 50);

	    	for(String strB : multilineB)
	    		this.lblInformation.addLine(strB);
    	}

    	this.labelList.add(this.lblInformation);

    	//Buttons
    	this.btnClose = new GuiButton(0, this.width / 2 + 5, this.height - 25, this.width / 2 - 10, 20, I18n.format(TRANSLATION_BASE_KEY.concat("btnclose")));
    	this.btnMoreInfo = new GuiButton(1, 5, this.height - 25, this.width / 2 - 10, 20, I18n.format(TRANSLATION_BASE_KEY.concat("btnmoreinfo")));

    	this.buttonList.add(this.btnClose);
    	this.buttonList.add(this.btnMoreInfo);

    	this.titleOffsetX = (int) ((this.width - (this.fontRenderer.getStringWidth("StopModReposts") * this.titleMultiplier) - 35) / 2);
    }

	private float titleMultiplier = 2;
	private int titleOffsetX = 0;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();

		this.drawGradientRect(0, 40, this.width, this.height - 30, 0x80000000, 0x80000000);

		Minecraft.getMinecraft().getTextureManager().bindTexture(SMR_LOGO);

		Gui.drawModalRectWithCustomSizedTexture(this.titleOffsetX, 5, 0, 0, 30, 30, 30, 30);

		GlStateManager.pushMatrix();
		GlStateManager.translate(this.titleOffsetX + 35, 14, 0);
		GlStateManager.scale(this.titleMultiplier, this.titleMultiplier, 0);
		this.fontRenderer.drawStringWithShadow("StopModReposts", 0, 0, 0xFFFFFFFF);
		GlStateManager.popMatrix();

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
    protected void actionPerformed(GuiButton button) throws IOException {
		if(button == this.btnClose) {
			Minecraft.getMinecraft().displayGuiScreen(this.parent);
	    	try {
	    		/*
	    		 * TODO create a file containing some more information and maybe more links
	    		 * Then copy it out of the assets
	    		 */
	    		StopModReposts.STOP_REPOST_FILE.createNewFile();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(button == this.btnMoreInfo) {
			ITextComponent linkHelper = new TextComponentString("");
			linkHelper.getStyle().setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, StopModReposts.URL));

			this.handleComponentClick(linkHelper);
		}
    }
}
