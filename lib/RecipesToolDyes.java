package clashsoft.mods.moretools.lib;

import java.util.ArrayList;

import clashsoft.mods.moretools.MoreToolsMod_Tools;
import clashsoft.mods.moretools.item.ItemDyeableHoeMoreTools;
import clashsoft.mods.moretools.item.ItemDyeableSwordMoreTools;
import clashsoft.mods.moretools.item.ItemDyeableToolMoreTools;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.world.World;

public class RecipesToolDyes implements IRecipe
{
	@Override
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		ItemStack var3 = null;
		ArrayList var4 = new ArrayList();
		
		for (int var5 = 0; var5 < par1InventoryCrafting.getSizeInventory(); ++var5)
		{
			ItemStack var6 = par1InventoryCrafting.getStackInSlot(var5);
			
			if (var6 != null)
			{
				if (var6.getItem() instanceof ItemDyeableToolMoreTools)
				{
					ItemDyeableToolMoreTools var7 = (ItemDyeableToolMoreTools) var6.getItem();
					
					if (var7.getToolMaterial() != MoreToolsMod_Tools.LEATHER || var3 != null)
					{
						return false;
					}
					
					var3 = var6;
				}
				else
				{
					if (var6.itemID != Item.dyePowder.itemID)
					{
						return false;
					}
					
					var4.add(var6);
				}
			}
		}
		
		return var3 != null && !var4.isEmpty();
	}
	
	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack var2 = null;
		int[] var3 = new int[3];
		int var4 = 0;
		int var5 = 0;
		ItemDyeableToolMoreTools var6 = null;
		int var7;
		int var9;
		float var10;
		float var11;
		int var17;
		
		for (var7 = 0; var7 < par1InventoryCrafting.getSizeInventory(); ++var7)
		{
			ItemStack var8 = par1InventoryCrafting.getStackInSlot(var7);
			
			if (var8 != null)
			{
				if (var8.getItem() instanceof ItemDyeableToolMoreTools || var8.getItem() instanceof ItemDyeableSwordMoreTools || var8.getItem() instanceof ItemDyeableHoeMoreTools)
				{
					var6 = (ItemDyeableToolMoreTools) var8.getItem();
					
					if (var6.getToolMaterial() != MoreToolsMod_Tools.LEATHER || var2 != null)
					{
						return null;
					}
					
					var2 = var8.copy();
					
					if (var6.hasColor(var8))
					{
						var9 = var6.getColor(var2);
						var10 = (var9 >> 16 & 255) / 255.0F;
						var11 = (var9 >> 8 & 255) / 255.0F;
						float var12 = (var9 & 255) / 255.0F;
						var4 = (int) (var4 + Math.max(var10, Math.max(var11, var12)) * 255.0F);
						var3[0] = (int) (var3[0] + var10 * 255.0F);
						var3[1] = (int) (var3[1] + var11 * 255.0F);
						var3[2] = (int) (var3[2] + var12 * 255.0F);
						++var5;
					}
				}
				else
				{
					if (var8.itemID != Item.dyePowder.itemID)
					{
						return null;
					}
					
					float[] var14 = EntitySheep.fleeceColorTable[BlockColored.getBlockFromDye(var8.getItemDamage())];
					int var16 = (int) (var14[0] * 255.0F);
					int var15 = (int) (var14[1] * 255.0F);
					var17 = (int) (var14[2] * 255.0F);
					var4 += Math.max(var16, Math.max(var15, var17));
					var3[0] += var16;
					var3[1] += var15;
					var3[2] += var17;
					++var5;
				}
			}
		}
		
		if (var6 == null)
		{
			return null;
		}
		else
		{
			var7 = var3[0] / var5;
			int var13 = var3[1] / var5;
			var9 = var3[2] / var5;
			var10 = (float) var4 / (float) var5;
			var11 = Math.max(var7, Math.max(var13, var9));
			var7 = (int) (var7 * var10 / var11);
			var13 = (int) (var13 * var10 / var11);
			var9 = (int) (var9 * var10 / var11);
			var17 = (var7 << 8) + var13;
			var17 = (var17 << 8) + var9;
			var6.func_82813_b(var2, var17);
			return var2;
		}
	}
	
	/**
	 * Returns the size of the recipe area
	 */
	@Override
	public int getRecipeSize()
	{
		return 10;
	}
	
	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}
}
