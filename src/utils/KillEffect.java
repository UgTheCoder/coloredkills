package utils;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import core.Main;
import events.UseGUI;
import utils.particles.ParticleEffect;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class KillEffect {

//	private Player killed;

	private Location loc;
	
	private Player killer;

	private ArrayList<ChatColor> effectColors;

	private ParticleEffect particle;

//	public KillEffect(Player killed, Player killer) {
//
//		this.killed = killed;
//
//		this.killer = killer;
//
//		this.effectColors = UseGUI.activeColor.get(killer);
//
//		this.particles = colorToEffect();
//
//	}
	
	public KillEffect(Location loc, Player killer) {

		this.loc = loc;
		
//		this.killed = killed;

		this.killer = killer;

		this.effectColors = UseGUI.activeColor.get(killer);

		this.particle = colorToEffect();

		this.spawnEffect();
		
	}

	private void spawnEffect() {

//		Location location = killed.getLocation();

		Location location = this.loc;
		
		Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);

		FireworkMeta fireworkMeta = firework.getFireworkMeta();

		fireworkMeta.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(Type.BURST)
				.withColor(chatColorsToColor()).build());

		fireworkMeta.setPower(1);

		firework.setFireworkMeta(fireworkMeta);

		spawnHelix(location);

	}

	public void spawnHelix(Location loc) {
		int radius = 1;

		for (double y = 0; y <= 13; y += 0.05) {

			final double yf = y;

			double x = radius * Math.cos(y);
			double z = radius * Math.sin(y);
			
			double xB = radius *-1* Math.cos(y);
			double zB = radius *-1* Math.sin(y);

			final ParticleEffect p = this.particle;

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.pl, new Runnable() {

				@Override
				public void run() {

						p.display(0, 0, 0, 0, 1,
								new Location(loc.getWorld(), loc.getX() + x, loc.getY() + yf, loc.getZ() + z), 200);
						p.display(0, 0, 0, 0, 1,
								new Location(loc.getWorld(), loc.getX() + xB, loc.getY() + yf, loc.getZ() + zB), 200);

				}

			}, (long) yf*2);

		}
		
	}

	private org.bukkit.Color chatColorsToColor() {

		Random random = new Random();
		
		int rc = random.nextInt(100 - -100 + 1) + -100;

		
		Color color = new Color(0, 0, 0);

		if(!this.effectColors.isEmpty()){
		
		if (this.effectColors.contains(ChatColor.RED))
			color = new Color(255, color.getGreen(), color.getBlue());

		if (this.effectColors.contains(ChatColor.BLUE))
			color = new Color(color.getRed(), color.getGreen(), 255);

		if (this.effectColors.contains(ChatColor.GREEN))
			color = new Color(color.getRed(), 255, color.getBlue());

		int r = color.getRed() + rc;
		int g = color.getGreen() + rc;
		int b = color.getBlue() + rc;
		
		if(r < 0)r=0;
		if(r>255)r=255;
		if(g < 0)g=0;
		if(g>255)g=255;
		if(b < 0)b=0;
		if(b>255)b=255;
		
		color = new Color(r,g,b);
		
		}else{
			color = new Color(0, 0, 0);
		}
		
		org.bukkit.Color c = org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue());

		return c;
	}

	/*
	 * Particle effects
	 */

	private boolean isAround(int number, int around){
		if((around + 105) >= number && (around - 105) <= number){
			return true;
		}else{
			return false;
		}
	}
	
	private ParticleEffect colorToEffect() {

		//Black
		
		ParticleEffect effect = ParticleEffect.SUSPENDED_DEPTH;

		org.bukkit.Color color = chatColorsToColor();
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		//Red
		
		if (isAround(r, 255) && isAround(g, 0) && isAround(b, 0))
			effect = ParticleEffect.FLAME;

		//Blue
		
		if (isAround(b, 255) && isAround(g, 0) && isAround(r, 0))
			effect = ParticleEffect.CRIT_MAGIC;

		//Green
		
		if (isAround(g, 255) && isAround(r, 0) && isAround(b, 0))
			effect = ParticleEffect.VILLAGER_HAPPY;
		
		//Yellow
		
		if (isAround(r, 255) && isAround(g, 255) && isAround(b, 0))
			effect = ParticleEffect.CRIT;
		
		//Pink
		
		if (isAround(r, 255) && isAround(b, 255) && isAround(g, 0))
			effect = ParticleEffect.REDSTONE;
		
		//Aqua
		
		if (isAround(g, 255) && isAround(b, 255) && isAround(r, 0))
			effect = ParticleEffect.WATER_SPLASH;
		
		//White
		
		if (isAround(g, 255) && isAround(b, 255) && isAround(r, 255))
			effect = ParticleEffect.ENCHANTMENT_TABLE;
		
		
		
		return effect;

	}

	/*
	 * Getters
	 */

//	public Player killed() {
//		return this.killed;
//	}

	public Player killer() {
		return this.killer;
	}

	public ArrayList<ChatColor> effectColor() {
		return this.effectColors;
	}

}
