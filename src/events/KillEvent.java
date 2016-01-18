package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import utils.KillEffect;

public class KillEvent implements Listener {

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
//		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() != null && e.getEntity().getKiller() instanceof Player) {

				Player killer = (Player) e.getEntity().getKiller();

				KillEffect effect = new KillEffect(e.getEntity().getLocation(), killer);

			}
		}
//	}

}
