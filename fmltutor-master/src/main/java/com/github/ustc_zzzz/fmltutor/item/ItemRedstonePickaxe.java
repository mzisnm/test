package com.github.ustc_zzzz.fmltutor.item;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemPickaxe;

public class ItemRedstonePickaxe extends ItemPickaxe
{
    public ItemRedstonePickaxe()
    {
        super(ItemLoader.REDSTONE);
        this.setUnlocalizedName("redstone_pickaxe");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }
}
