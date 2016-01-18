package events;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import utils.ItemFactory;

public class OpenGUI implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {

		Player player = e.getPlayer();

		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if (player.getItemInHand().getType().equals(Material.SLIME_BALL)) {

				Inventory gui = Bukkit.getServer().createInventory(player, 18, ChatColor.UNDERLINE + "Kill Effect");

				boolean red = false;
				boolean blue = false;
				boolean green = false;
				
				if(UseGUI.activeColor.containsKey(player)){
					red = UseGUI.activeColor.get(player).contains(ChatColor.RED);
					blue = UseGUI.activeColor.get(player).contains(ChatColor.BLUE);
					green = UseGUI.activeColor.get(player).contains(ChatColor.GREEN);
				}
				
				gui.addItem(ItemFactory.createItem(Material.INK_SACK, ChatColor.RED + "Red", 1, (byte) 1,
						red));

				gui.addItem(ItemFactory.createItem(Material.INK_SACK, ChatColor.BLUE + "Blue", 1, (byte) 12,
						blue));

				gui.addItem(ItemFactory.createItem(Material.INK_SACK, ChatColor.GREEN + "Green", 1, (byte) 10,
						green));

				player.openInventory(gui);

				player.playSound(player.getLocation(), Sound.SHEEP_SHEAR, 1, 1);

			}
		}
	}

}
