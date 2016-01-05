package com.github.ustc_zzzz.fmltutor.client.gui;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMyButton extends GuiButton {
	protected static Logger log = LogManager.getLogger(GuiNewMainMenu.class);
	protected ResourceLocation[] buttonTextures = {new ResourceLocation("fmltutor","textures/gui/start_disable.png")
																,new ResourceLocation("fmltutor","textures/gui/start_normal.png")
																,new ResourceLocation("fmltutor","textures/gui/start_mouseover.png")
																,new ResourceLocation("fmltutor","textures/gui/start_click.png")
																};
	/** Button width in pixels */
	public int width;
	/** Button height in pixels */
	public int height;
	/** The x position of this control. */
	public int xPosition;
	/** The y position of this control. */
	public int yPosition;
	/** The string displayed on this control. */
	public String displayString;
	public int id;
	/** True if this control is enabled, false to disable. */
	public boolean enabled;
	/** Hides the button completely if false. */
	public boolean visible;
	protected boolean hovered;
	protected boolean clicked;
	public int packedFGColour; // FML

	public GuiMyButton(int buttonId, int x, int y, int widthIn, int heightIn, ResourceLocation[] buttonTextures, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.width = 200;
		this.height = 20;
		this.enabled = true;
		this.visible = true;
		this.id = buttonId;
		this.xPosition = x;
		this.yPosition = y;
		this.width = widthIn;
		this.height = heightIn;
		this.buttonTextures = buttonTextures;
		this.displayString = buttonText;
	}

	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	protected int getHoverState(boolean mouseOver) {
		byte b0 = 1;//mormal

		if (!this.enabled) {
			b0 = 0;//disable
		} else if (mouseOver) {
			b0 = 2;//hover
		}

		return b0;
	}

	/**
	 * Draws this button to the screen.
	 */
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			int k = this.getHoverState(this.hovered);
			if(this.clicked){
				k = 3;
			}
			mc.getTextureManager().bindTexture(buttonTextures[k]);
			/*GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);*/
			//参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.
			drawScaledCustomSizeModalRect(xPosition, yPosition, 0, 0, width, height, width, height, width, height); 
			this.mouseDragged(mc, mouseX, mouseY);
		}
	}

	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
	}

	/**
	 * Fired when the mouse button is released. Equivalent of
	 * MouseListener.mouseReleased(MouseEvent e).
	 */
	public void mouseReleased(int mouseX, int mouseY) {
		this.clicked = false;
	}

	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		this.clicked = this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		return this.clicked;
	}

	/**
	 * Whether the mouse cursor is currently over the button.
	 */
	public boolean isMouseOver() {
		return this.hovered;
	}

	public void drawButtonForegroundLayer(int mouseX, int mouseY) {
	}

	public void playPressSound(SoundHandler soundHandlerIn) {
		soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
	}

	public int getButtonWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}