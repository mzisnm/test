package com.github.ustc_zzzz.fmltutor.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import noppes.npcs.api.event.DialogEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.ustc_zzzz.fmltutor.client.gui.GuiNewMainMenu;
import com.github.ustc_zzzz.fmltutor.common.CommonProxy;

public class ClientProxy extends CommonProxy {
	protected static Logger log = LogManager.getLogger(ClientProxy.class);
	private GuiButton btnShowNewGui = new GuiButton(223, 0, 0, "To open the door, use the code: 0012");

	@SubscribeEvent
	public void onInteract(DialogEvent.OpenEvent event) {
		log.info("npc对话" + event.dialog.getQuest());
		log.info("npc对话" + event.dialog.getOptions());
	}

	@SubscribeEvent
	public void guiScreenShow(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			log.info("handle主界面开始");
			event.gui = new GuiNewMainMenu();
			log.info("handle主界面结束");
		}
	}

	@SubscribeEvent
	public void playerJoinGame(ClientConnectedToServerEvent event) {
		log.info("handle玩家加入游戏开始");
		// Minecraft.getMinecraft().displayGuiScreen(new GuiNewMainMenu());
		log.info("handle玩家加入游戏结束");
	}

	@SubscribeEvent
	public void renderWorldHandle(RenderWorldLastEvent event) {
		log.info("RenderWorldLastEvent开始");
		// Minecraft.getMinecraft().displayGuiScreen(new GuiNewMainMenu());
		log.info("RenderWorldLastEvent结束");
	}

	@SubscribeEvent
	public void guiClickButton(ActionPerformedEvent.Post event) {
		if (event.button == btnShowNewGui) {
			
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(this);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
