package com.github.ustc_zzzz.fmltutor.item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;

public class ItemRedstoneApple extends ItemFood {
	protected static Logger     log          = LogManager.getLogger(ItemRedstoneApple.class);
	public ItemRedstoneApple() {
		super(4, 0.6F, false);
		this.setAlwaysEdible();
		this.setUnlocalizedName("redstone_apple");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
		this.setPotionEffect(Potion.absorption.id, 10, 1, 1.0F);
	}

	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.saturation.id, 200, 1));
			player.addExperience(10);
		}
		super.onFoodEaten(stack, worldIn, player);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		log.info("右键单击了===5==");
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}

}
