package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.ItemArmor;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;

public class ItemRedstoneArmor extends ItemArmor
{
    public ItemRedstoneArmor(int armorType)
    {
        super(ItemLoader.REDSTONE_ARMOR, ItemLoader.REDSTONE_ARMOR.ordinal(), armorType);
    }

    public static class Helmet extends ItemRedstoneArmor
    {
        public Helmet()
        {
            super(0);
            this.setUnlocalizedName("redstone_helmet");
            this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
        }
    }

    public static class Chestplate extends ItemRedstoneArmor
    {
        public Chestplate()
        {
            super(1);
            this.setUnlocalizedName("redstone_chestplate");
            this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
        }
    }

    public static class Leggings extends ItemRedstoneArmor
    {
        public Leggings()
        {
            super(2);
            this.setUnlocalizedName("redstone_leggings");
            this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
        }
    }

    public static class Boots extends ItemRedstoneArmor
    {
        public Boots()
        {
            super(3);
            this.setUnlocalizedName("redstone_boots");
            this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
        }
    }
}
