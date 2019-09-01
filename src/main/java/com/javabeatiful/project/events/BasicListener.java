package com.javabeatiful.project.events;

import java.io.File;
import java.util.LinkedHashMap;

import com.javabeatiful.project.Main;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.Config;

public class BasicListener implements Listener {

	@EventHandler
	public void onDrop(PlayerDropItemEvent listener) {
		Player player = listener.getPlayer();

		if (Main.maps.contains(player.getLevel().getName())) {
			listener.setCancelled();
		}
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (!new File(Main.getInstance().getDataFolder() + "/users/" + player.getName() + ".json").exists()) {
			Config playerJson = new Config(
				new File(Main.getInstance().getDataFolder() + "/users/" + player.getName() + ".json"),
				Config.JSON,
			new LinkedHashMap<String, Object>() {

				{

					put("Kills", 1);
					put("Deaths", 1);



				}

			});
			playerJson.save();
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		
		if(Main.maps.contains(player.getLevel().getName())){
			event.setCancelled();
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		
		if(Main.maps.contains(player.getLevel().getName())){
			event.setCancelled();
		}
	}

}