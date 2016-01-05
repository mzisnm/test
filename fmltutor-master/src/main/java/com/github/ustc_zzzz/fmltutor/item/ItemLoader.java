package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.ustc_zzzz.fmltutor.FMLTutor;

public class ItemLoader
{
    public static final Item.ToolMaterial REDSTONE = EnumHelper.addToolMaterial("REDSTONE", 3, 16, 16.0F, 0.0F, 10);
    public static final ItemArmor.ArmorMaterial REDSTONE_ARMOR = EnumHelper.addArmorMaterial("REDSTONE",
            "fmltutor:redstone", 10, new int[]
    {
            2, 6, 4, 2
    }, 10);

    public static Item goldenEgg = new ItemGoldenEgg();
    public static ItemPickaxe redstonePickaxe = new ItemRedstonePickaxe();
    public static ItemFood redstoneApple = new ItemRedstoneApple();

    public static ItemArmor redstoneHelmet = new ItemRedstoneArmor.Helmet();
    public static ItemArmor redstoneChestplate = new ItemRedstoneArmor.Chestplate();
    public static ItemArmor redstoneLeggings = new ItemRedstoneArmor.Leggings();
    public static ItemArmor redstoneBoots = new ItemRedstoneArmor.Boots();

    public ItemLoader(FMLPreInitializationEvent event)
    {
        register(goldenEgg);
        register(redstonePickaxe);
        register(redstoneApple);

        register(redstoneHelmet);
        register(redstoneChestplate);
        register(redstoneLeggings);
        register(redstoneBoots);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(goldenEgg);
        registerRender(redstonePickaxe);
        registerRender(redstoneApple);

        registerRender(redstoneHelmet);
        registerRender(redstoneChestplate);
        registerRender(redstoneLeggings);
        registerRender(redstoneBoots);
    }

    private static void register(Item item)
    {
        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(FMLTutor.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
