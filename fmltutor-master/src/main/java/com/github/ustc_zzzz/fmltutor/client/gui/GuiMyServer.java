package com.github.ustc_zzzz.fmltutor.client.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;

import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

@SideOnly(Side.CLIENT)
public class GuiMyServer extends GuiButton {
	protected static Logger log = LogManager.getLogger(GuiNewMainMenu.class);
	private static final ResourceLocation[] severBgTextures = { new ResourceLocation("fmltutor", "textures/gui/severBg_disable.png"), new ResourceLocation("fmltutor", "textures/gui/severBg_normal.png"), new ResourceLocation("fmltutor", "textures/gui/severBg_hover.png"), new ResourceLocation("fmltutor", "textures/gui/severBg_selected.png") };
	private static final ResourceLocation recommendTexture = new ResourceLocation("fmltutor", "textures/gui/recommend_server.png");
	private static final ResourceLocation selectedTexture = new ResourceLocation("fmltutor", "textures/gui/select_flag.png");
	private static final ThreadPoolExecutor field_148302_b = new ScheduledThreadPoolExecutor(5, (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build());
	private static final ResourceLocation unknownTexture = new ResourceLocation("textures/misc/unknown_server.png");
	private ServerData serverData;
	private ResourceLocation iconTexture;
	private DynamicTexture dynamicTexture;
	private String iconData;
	private GuiNewMainMenu guiNewMainMenu;
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
	protected boolean selected;
	public int packedFGColour; // FML

	public GuiMyServer(int buttonId, int x, int y, ServerData serverData, GuiNewMainMenu guiNewMainMenu) {
		super(buttonId, x, y, 50, 72, "");
		this.width = 50;
		this.height = 72;
		this.enabled = true;
		this.visible = true;
		this.id = buttonId;
		this.xPosition = x;
		this.yPosition = y;
		this.serverData = serverData;
		this.guiNewMainMenu = guiNewMainMenu;
		this.iconTexture = new ResourceLocation("servers/" + serverData.serverIP + "/icon");
		this.dynamicTexture = (DynamicTexture)Minecraft.getMinecraft().getTextureManager().getTexture(this.iconTexture);
	}

	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	protected int getHoverState(boolean mouseOver) {
		byte b0 = 1;// mormal

		if (!this.enabled) {
			b0 = 0;// disable
		} else if (mouseOver) {
			b0 = 2;// hover
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
			if (this.selected) {
				k = 3;
			}
			mc.getTextureManager().bindTexture(severBgTextures[k]);
			/*
			 * GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			 * GlStateManager.enableBlend();
			 * GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			 * GlStateManager.blendFunc(770, 771);
			 */
			// 参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.
			drawScaledCustomSizeModalRect(xPosition, yPosition, 0, 0, 101, 144, width, height, 101, 144);
			this.mouseDragged(mc, mouseX, mouseY);
			if (!this.serverData.field_78841_f) {
				this.serverData.field_78841_f = true;
				this.serverData.pingToServer = -2L;
				this.serverData.serverMOTD = "";
				this.serverData.populationInfo = "";
				field_148302_b.submit(new Runnable() {
					private static final String __OBFID = "CL_00000818";

					public void run() {
						try {
							GuiMyServer.this.guiNewMainMenu.getOldServerPinger().ping(GuiMyServer.this.serverData);
						} catch (UnknownHostException unknownhostexception) {
							GuiMyServer.this.serverData.pingToServer = -1L;
							GuiMyServer.this.serverData.serverMOTD = EnumChatFormatting.DARK_RED + "Can\'t resolve hostname";
						} catch (Exception exception) {
							GuiMyServer.this.serverData.pingToServer = -1L;
							GuiMyServer.this.serverData.serverMOTD = EnumChatFormatting.DARK_RED + "Can\'t connect to server.";
							log.error("ping error",exception);
						}
					}
				});
			}

			boolean flag1 = this.serverData.version > 47;
			boolean flag2 = this.serverData.version < 47;
			boolean flag3 = flag1 || flag2;
			mc.fontRendererObj.drawString(this.serverData.serverName, xPosition + 1, yPosition + 50, 16777215);

			String s2 = flag3 ? EnumChatFormatting.DARK_RED + this.serverData.gameVersion : this.serverData.populationInfo;
	        int i2 = mc.fontRendererObj.getStringWidth(s2);
	        mc.fontRendererObj.drawString(s2, xPosition+width-12 - i2 - 2, yPosition+height-10, 0);
	        
			byte b0 = 0;
			String s = null;
			int j2;
			String s1;

			if (flag3) {
				j2 = 5;
				s1 = flag1 ? "Client out of date!" : "Server out of date!";
				s = this.serverData.playerList;
			} else if (this.serverData.field_78841_f && this.serverData.pingToServer != -2L) {
				if (this.serverData.pingToServer < 0L) {
					j2 = 5;
				} else if (this.serverData.pingToServer < 150L) {
					j2 = 0;
				} else if (this.serverData.pingToServer < 300L) {
					j2 = 1;
				} else if (this.serverData.pingToServer < 600L) {
					j2 = 2;
				} else if (this.serverData.pingToServer < 1000L) {
					j2 = 3;
				} else {
					j2 = 4;
				}

				if (this.serverData.pingToServer < 0L) {
					s1 = "(no connection)";
				} else {
					s1 = this.serverData.pingToServer + "ms";
					s = this.serverData.playerList;
				}
			} else {
				b0 = 1;
				j2 = (int) (Minecraft.getSystemTime() / 100L + (long) (1 * 2) & 7L);//1*2不知道什么意思,随便改的1

				if (j2 > 4) {
					j2 = 8 - j2;
				}

				s1 = "Pinging...";
			}

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(Gui.icons);
			Gui.drawModalRectWithCustomSizedTexture(xPosition+width-12, yPosition+height-10, (float) (b0 * 10), (float) (176 + j2 * 8), 10, 8, 256.0F, 256.0F);

			if (this.serverData.getBase64EncodedIconData() != null && !this.serverData.getBase64EncodedIconData().equals(this.iconData)) {
				this.iconData = this.serverData.getBase64EncodedIconData();
				this.prepareServerIcon();
			}

			if (this.dynamicTexture != null) {
				this.drawIcon(xPosition, yPosition, this.iconTexture);
			} else {
				this.drawIcon(xPosition, yPosition, unknownTexture);
			}

			mc.getTextureManager().bindTexture(recommendTexture);
			drawScaledCustomSizeModalRect(xPosition, yPosition, 0, 0, 32, 32, 16, 16, 32, 32);
			
			if(selected){
				mc.getTextureManager().bindTexture(selectedTexture);
				drawScaledCustomSizeModalRect(xPosition+width-10, yPosition+40, 0, 0, 15, 16, 8, 8, 15, 16);
			}
			
			int k2 = mouseX - xPosition;
			int l2 = mouseY - yPosition;

			if (k2 >= width - 15 && k2 <= width && l2 >= height-10 && l2 <= height) {
				this.guiNewMainMenu.setHoveringText(s1);
			} else if (k2 >= width-12 - i2 - 2 && k2 <= width-12 - 2 && l2 >= height-8 && l2 <= height) {
				this.guiNewMainMenu.setHoveringText(s);
			}

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
	}

	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	private void prepareServerIcon() {
		Minecraft mc = Minecraft.getMinecraft();
		if (this.serverData.getBase64EncodedIconData() == null) {
			mc.getTextureManager().deleteTexture(this.iconTexture);
			this.dynamicTexture = null;
		} else {
			ByteBuf bytebuf = Unpooled.copiedBuffer(this.serverData.getBase64EncodedIconData(), Charsets.UTF_8);
			ByteBuf bytebuf1 = Base64.decode(bytebuf);
			BufferedImage bufferedimage;
			label74: {
				try {
					bufferedimage = TextureUtil.readBufferedImage(new ByteBufInputStream(bytebuf1));
					Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
					Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
					break label74;
				} catch (Exception exception) {
					log.error("Invalid icon for server " + this.serverData.serverName + " (" + this.serverData.serverIP + ")", exception);
					this.serverData.setBase64EncodedIconData((String) null);
				} finally {
					bytebuf.release();
					bytebuf1.release();
				}

				return;
			}

			if (this.dynamicTexture == null) {
				this.dynamicTexture = new DynamicTexture(bufferedimage.getWidth(), bufferedimage.getHeight());
				mc.getTextureManager().loadTexture(this.iconTexture, this.dynamicTexture);
			}

			bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), this.dynamicTexture.getTextureData(), 0, bufferedimage.getWidth());
			this.dynamicTexture.updateDynamicTexture();
		}
	}

	protected void drawIcon(int xPosition, int yPosition, ResourceLocation p_178012_3_) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(p_178012_3_);
		GlStateManager.enableBlend();
		Gui.drawModalRectWithCustomSizedTexture(xPosition+1, yPosition+1, 0.0F, 0.0F, 48, 48, 48.0F, 48.0F);
		GlStateManager.disableBlend();
	}

	public ServerData getServerData() {
		return serverData;
	}
}