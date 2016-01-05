package com.github.ustc_zzzz.fmltutor.client.gui;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerData.ServerResourceMode;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * 
 * @author  马哲@zhuzher
 * @since   2015年12月24日下午6:56:58
 * @version 
 *
 */
@SideOnly(Side.CLIENT)
public class GuiNewMainMenu extends GuiScreen{
	protected static Logger log = LogManager.getLogger(GuiNewMainMenu.class);
	private final OldServerPinger oldServerPinger = new OldServerPinger();
	private static final ResourceLocation titlePanoramaPath = new ResourceLocation("fmltutor","textures/gui/title/background/background.jpg");
	private static final ResourceLocation titlePath = new ResourceLocation("fmltutor","textures/gui/server_selection.png");
	private static final ResourceLocation[] startTextures = {new ResourceLocation("fmltutor","textures/gui/start_disable.png")
						,new ResourceLocation("fmltutor","textures/gui/start_normal.png")
						,new ResourceLocation("fmltutor","textures/gui/start_mouseover.png")
						,new ResourceLocation("fmltutor","textures/gui/start_click.png")
						};
	private static final ResourceLocation[] dxTextures = {new ResourceLocation("fmltutor","textures/gui/dx_normal.png")
						,new ResourceLocation("fmltutor","textures/gui/dx_normal.png")
						,new ResourceLocation("fmltutor","textures/gui/dx_mouseover.png")
						,new ResourceLocation("fmltutor","textures/gui/dx_click.png")
						};
	private static final ResourceLocation[] forumTextures = {new ResourceLocation("fmltutor","textures/gui/forum_normal.png")
						,new ResourceLocation("fmltutor","textures/gui/forum_normal.png")
						,new ResourceLocation("fmltutor","textures/gui/forum_mouseover.png")
						,new ResourceLocation("fmltutor","textures/gui/forum_click.png")
						};
	private static final ResourceLocation[] csTextures = {new ResourceLocation("fmltutor","textures/gui/cs_normal.png")
						,new ResourceLocation("fmltutor","textures/gui/cs_normal.png")
						,new ResourceLocation("fmltutor","textures/gui/cs_mouseover.png")
						,new ResourceLocation("fmltutor","textures/gui/cs_click.png")
						};
	
	private static int selectedServer = 0;
	private static final ResourceLocation logoTexture = new ResourceLocation("fmltutor","textures/gui/logo.png");
	private String dxUrl = "";
	private String forumUrl = "";
	private String qqUrl = "http://wpa.qq.com/msgrd?v=3&uin=649291446&site=qq&menu=yes";
	private String hoveringText;
	private List<GuiMyServer> serverList = new ArrayList<GuiMyServer>();
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id){
			case 0:
				for (GuiMyServer temp: serverList) {
					if(selectedServer == temp.id){
						temp.setSelected(false);
						FMLClientHandler clientHandler = FMLClientHandler.instance();
						clientHandler.setupServerList();
						clientHandler.connectToServer(this, temp.getServerData());
					}
				}
				break;
			case 1:
				openLink(dxUrl);
				break;
			case 2:
				openLink(forumUrl);
				break;
			case 3:
				openLink(qqUrl);
				break;
		}
		for (GuiMyServer temp: serverList) {
			if(button.id == temp.id){
				selectedServer = button.id;
				temp.setSelected(true);
			}
		}
		for (GuiMyServer temp: serverList) {
			if(selectedServer != temp.id){
				temp.setSelected(false);
			}
		}
	}
	// 每当界面被打开时调用
	// 这里部署控件
	@Override
	public void initGui() {
		log.info("新主界面初始化开始");
		log.info(width+"="+height);
		serverList.clear();
		buttonList.add(new GuiMyButton(0, (int)(width*0.80), (int)(height*0.49), 81, 95, startTextures, ""));
		buttonList.add(new GuiMyButton(1, (int)(width*0.08), (int)(height*0.15), 67, 28, dxTextures, ""));
		buttonList.add(new GuiMyButton(2, (int)(width*0.08)+7, (int)(height*0.15)+24, 67, 28, forumTextures, ""));
		buttonList.add(new GuiMyButton(3, (int)(width*0.08)+7*2, (int)(height*0.15)+24*2, 67, 28, csTextures, ""));
		ServerData serverData1 = new ServerData("我的世界1", "localhost:25565");
		serverData1.field_78841_f = false;
		serverData1.setResourceMode(ServerResourceMode.ENABLED);
		GuiMyServer guiMyServer1 = new GuiMyServer(4, (int)(width*0.80)-60, (int)(height*0.49), serverData1, this);
		buttonList.add(guiMyServer1);
		serverList.add(guiMyServer1);
		System.out.println("+++");
		ServerData serverData2 = new ServerData("我的世界2", "183.61.146.166:17845");
		serverData2.field_78841_f = false;
		serverData2.setResourceMode(ServerResourceMode.ENABLED);
		GuiMyServer guiMyServer2 = new GuiMyServer(5, (int)(width*0.80)-60-55, (int)(height*0.49), serverData2, this);
		buttonList.add(guiMyServer2);
		serverList.add(guiMyServer2);
		log.info("新主界面初始化结束");
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.hoveringText = null;
		drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
        drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
		mc.renderEngine.bindTexture(titlePanoramaPath); //绑定纹理
		//参数分别为x,y,u,v,u宽度,v高度(即纹理中欲绘制区域的宽高),实际宽,实际高,纹理总宽,纹理总高.
		drawScaledCustomSizeModalRect(0, 0, 0, 0, 1277, 711, width, height, 1277, 711); 
		((GuiButton)this.buttonList.get(1)).drawButton(this.mc, par1, par2);
		((GuiButton)this.buttonList.get(2)).drawButton(this.mc, par1, par2);
		((GuiButton)this.buttonList.get(3)).drawButton(this.mc, par1, par2);
		mc.renderEngine.bindTexture(titlePath); 
		drawScaledCustomSizeModalRect((int)(width*0.03), (int)(height*0.03), 0, 0, 835, 507, (int)(width*0.9), (int)(height*0.9), 835, 507); 
		((GuiButton)this.buttonList.get(0)).drawButton(this.mc, par1, par2);
		for (int i = 0; i < serverList.size(); i++) {
			((GuiButton)this.buttonList.get(i+4)).drawButton(this.mc, par1, par2);
		}
		mc.renderEngine.bindTexture(logoTexture); 
		drawScaledCustomSizeModalRect((int)(width*0.8), (int)(height*0.08), 0, 0, 175, 141, (int)(width*0.16), (int)(height*0.15), 175, 141); 
		if (this.hoveringText != null) {
			this.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), par1, par2);
		}
		
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		this.oldServerPinger.pingPendingNetworks();
	}
	
	private void openLink(String url) {
		try {
			Class oclass = Class.forName("java.awt.Desktop");
			Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object) null, new Object[0]);
			oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI(url) });
		} catch (Throwable throwable) {
			log.error("Couldn\'t open link", throwable);
		}
	}
	public OldServerPinger getOldServerPinger() {
		return oldServerPinger;
	}
	public String getHoveringText() {
		return hoveringText;
	}
	public void setHoveringText(String hoveringText) {
		this.hoveringText = hoveringText;
	}
}
