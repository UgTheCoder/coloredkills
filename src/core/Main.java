package core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import events.KillEvent;
import events.OpenGUI;
import events.UseGUI;

public class Main extends JavaPlugin {

	public static Plugin pl;

	public void onEnable() {

		pl = this;

		Bukkit.getServer().getPluginManager().registerEvents(new KillEvent(), pl);

		Bukkit.getServer().getPluginManager().registerEvents(new OpenGUI(), pl);

		Bukkit.getServer().getPluginManager().registerEvents(new UseGUI(), pl);

	}

}
