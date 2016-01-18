package events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UseGUI implements Listener {

	public static HashMap<Player, ArrayList<ChatColor>> activeColor = new HashMap<Player, ArrayList<ChatColor>>();

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player player = (Player) e.getWhoClicked();

			if (e.getInventory().getTitle().equals(ChatColor.UNDERLINE + "Kill Effect")) {

				boolean close = false;

				if (e.getCurrentItem().equals(null))
					return;
				if (e.getCurrentItem().getType().equals(Material.AIR))
					return;

				if (e.getCurrentItem().getType().equals(Material.INK_SACK)) {

					ArrayList<ChatColor> newColor = new ArrayList<ChatColor>();
					
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Red")) {
						if (activeColor.containsKey(player)) {
							if (!activeColor.get(player).contains(ChatColor.RED)) {
								activeColor.get(player).add(ChatColor.RED);
							} else {
								activeColor.get(player).remove(ChatColor.RED);
							}
						} else {
							newColor.add(ChatColor.RED);
							activeColor.put(player, newColor);
						}
						close = true;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Blue")) {
						if (activeColor.containsKey(player)) {

							if (!activeColor.get(player).contains(ChatColor.BLUE)) {
								activeColor.get(player).add(ChatColor.BLUE);
							} else {
								activeColor.get(player).remove(ChatColor.BLUE);
							}
						} else {
							newColor.add(ChatColor.BLUE);
							activeColor.put(player, newColor);
						}
						close = true;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Green")) {
						if (activeColor.containsKey(player)) {

							if (!activeColor.get(player).contains(ChatColor.GREEN)) {
								activeColor.get(player).add(ChatColor.GREEN);
							} else {
								activeColor.get(player).remove(ChatColor.GREEN);
							}
						} else {
							newColor.add(ChatColor.GREEN);
							activeColor.put(player, newColor);
						}
						close = true;
					}

				}

				if (close)
					player.closeInventory();
				if (close)
					player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1, 1);

				e.setCancelled(true);

			}

		}
	}

}
