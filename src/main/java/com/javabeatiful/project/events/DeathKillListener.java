package com.javabeatiful.project.events;

import com.javabeatiful.project.Main;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class DeathKillListener implements Listener {

	private String lastMap = null;

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onKill(PlayerDeathEvent listener) {
		Player player = listener.getEntity();
		if (Main.maps.contains(player.getLevel().getName())) {
			Config playerConfig = new Config(Main.getInstance().getDataFolder() + "/users/" + player.getName() + ".json");
			int dnew = playerConfig.getInt("Deaths") + 1;
			playerConfig.set("Deaths", dnew);
			playerConfig.save();

			if(Main.firstdead.contains(player.getUniqueId())){
				
			} else {
				Main.firstdead.add(player.getUniqueId());
			}

			listener.setDrops(null);

			lastMap = player.getLevel().getName();

			// set the custom death message

			listener.setDeathMessage(TextFormat.GOLD + "GunGame | " + player.getName() + " has died");

			EntityDamageEvent causer = player.getLastDamageCause();

			if (causer instanceof EntityDamageByEntityEvent) {
				Entity causeev = ((EntityDamageByEntityEvent) causer).getDamager();
				Player causerPlayer = (Player) causeev;
				Config playerC = new Config(Main.getInstance().getDataFolder() + "/users/" + causerPlayer.getName() + ".json");
				int knew = playerConfig.getInt("Kills") + 1;
				playerC.set("Kills", knew);
				playerC.save();
				causerPlayer.sendMessage(TextFormat.GOLD + "GunGame | " + TextFormat.GREEN + "You killed " + player.getName());



			}
		}

	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		Main.getInstance().getServer().getScheduler().scheduleDelayedTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				if (Main.firstdead.contains(player.getUniqueId())){
					player.setGamemode(2);
					Item item = new Item(345, 0, 1);
					item.setCustomName("Respawn");
					player.getInventory().addItem(item);

				}
			}


		}, 3 * 10);

	}

	@EventHandler
	public void onInterfact(PlayerInteractEvent listener) {
		Item item = listener.getItem();
		Player player = listener.getPlayer();

		if(item.getId() == 345 && item.getCustomName() == "Respawn"){
			        Server.getInstance().broadcastMessage(TextFormat.GOLD + "GunGame" + TextFormat.GRAY + " | " + TextFormat.AQUA + player.getName() + "is back!");

					Level world = Server.getInstance().getLevelByName(lastMap);

					player.setGamemode(0);
					player.getInventory().clearAll();

					player.teleport(world.getSafeSpawn());

					player.getInventory().clearAll();

					player.getInventory().setBoots(Item.get(313, 0, 1));
					player.getInventory().setChestplate(Item.get(311, 0, 1));
					player.getInventory().setLeggings(Item.get(308, 0, 1));
					player.getInventory().setHelmet(Item.get(306, 0, 1));
					player.getInventory().setItem(0, Item.get(276, 0, 1));
					player.getInventory().setItem(1, Item.get(320, 0, 12));
					player.getInventory().setItem(2, Item.get(261, 0, 1));
					player.getInventory().setItem(9, Item.get(262, 0, 12));
					player.getInventory().setItem(3, Item.get(346, 0, 1));
					player.setHealth(20);
				}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		Main.firstdead.remove(player.getUniqueId());
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		player.getInventory().clearAll();

		player.setGamemode(0);
	}
}