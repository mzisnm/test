package com.github.ustc_zzzz.fmltutor.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.github.ustc_zzzz.fmltutor.block.BlockLoader;
import com.github.ustc_zzzz.fmltutor.common.ConfigLoader;
import com.github.ustc_zzzz.fmltutor.item.ItemLoader;

public class CraftingLoader
{
    public CraftingLoader()
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe()
    {
        GameRegistry.addRecipe(new ItemStack(ItemLoader.goldenEgg), new Object[]
        {
                "###", "#*#", "###", '#', Items.gold_ingot, '*', Items.egg
        });
        GameRegistry.addRecipe(new ItemStack(BlockLoader.grassBlock), new Object[]
        {
                "##", "##", '#', Blocks.vine
        });
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstonePickaxe), new Object[]
        {
                "###", " * ", " * ", '#', Items.redstone, '*', Items.stick
        });
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneApple), new Object[]
        {
                "###", "#*#", "###", '#', Items.redstone, '*', Items.apple
        });
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneHelmet), new Object[]
        {
                "###", "# #", '#', Items.redstone
        });
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneChestplate), new Object[]
        {
                "# #", "###", "###", '#', Items.redstone
        });
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneLeggings), new Object[]
        {
                "###", "# #", "# #", '#', Items.redstone
        });
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneBoots), new Object[]
        {
                "# #", "# #", '#', Items.redstone
        });
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.vine, 4), BlockLoader.grassBlock);
    }

    private static void registerSmelting()
    {
        GameRegistry.addSmelting(BlockLoader.grassBlock, new ItemStack(Items.coal), 0.5F);
    }

    private static void registerFuel()
    {
        GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
                return Items.diamond != fuel.getItem() ? 0 : Math.max(0, ConfigLoader.diamondBurnTime) * 20;
            }
        });
    }
}
