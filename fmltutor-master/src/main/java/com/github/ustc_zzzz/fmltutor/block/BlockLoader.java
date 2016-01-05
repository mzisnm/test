package com.github.ustc_zzzz.fmltutor.block;

import com.github.ustc_zzzz.fmltutor.FMLTutor;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLoader
{
    public static Block grassBlock = new BlockGrassBlock();

    public BlockLoader(FMLPreInitializationEvent event)
    {
        register(grassBlock);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(grassBlock);
    }

    private static void register(Block block)
    {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation(FMLTutor.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }
}
