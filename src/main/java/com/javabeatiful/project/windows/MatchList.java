package com.javabeatiful.project.windows;

import java.io.File;
import java.io.FilenameFilter;

import com.javabeatiful.project.Main;

import com.javabeatiful.project.windows.Window;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.utils.Config;

public class MatchList extends FormWindowSimple implements Window {

	public MatchList() {
		super("GunGame - Arenas", "Arena List");

		File arenaFolder = new File(Main.getInstance().getDataFolder() + "/arena");
		File[] files = arenaFolder.listFiles((FilenameFilter) new FilenameFilter() {

			@Override
			public boolean accept(File directory, String name) {
				if (name.toLowerCase().endsWith(".json")) {
					return true;
				} else {
					return false;
				}
			}
		});

		for (File f : files) {
			Config resultF = new Config(Main.getInstance().getDataFolder() + "/arena/" + f.getName());
			addButton(new ElementButton(resultF.getString("Name")));
			Main.maps.add(resultF.getString("World"));
		}
	}

	public void onRespone(PlayerFormRespondedEvent event) {
		Player player = event.getPlayer();
		String hoverButton = getResponse().getClickedButton().getText();
		
		Config cfile = new Config(Main.getInstance().getDataFolder() + "/arena/" + hoverButton + ".json");
		String worldString = cfile.getString("World");
		// <- to ->
		Level world = Server.getInstance().getLevelByName(worldString);
		if (!Server.getInstance().isLevelLoaded(worldString)) {
			Server.getInstance().loadLevel(worldString);
		}
		
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

		
		Main.uiOpen.put(player.getUniqueId(), false);
	}

}



