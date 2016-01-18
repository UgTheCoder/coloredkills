package utils;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

import java.util.List;

public class ItemFactory {

	public static ItemStack createItem(Material material) {
		ItemStack item = new ItemStack(material);
		return item;
	}

	public static ItemStack createItem(Material material, int amount) {
		ItemStack item = new ItemStack(material);
		item.setAmount(amount);
		return item;
	}

	public static ItemStack createItem(Material material, String name) {
		ItemStack item = createItem(material, 1);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack createItem(Material material, String name, List<String> lore, int amount) {
		ItemStack item = createItem(material, name);
		item.setAmount(amount);

		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack createItem(Material material, String name, int amount, byte data, boolean enchant) {
		ItemStack item = new ItemStack(material, amount, data);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);

		if(enchant){
			EnchantGlow.addGlow(item);
		}

		return item;
	}
	
	public static ItemStack createItem(Material material, String name, List<String> lore) {
		ItemStack item = createItem(material, name);

		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

}
