package com.javabeatiful.project;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.javabeatiful.project.commands.GunGameCommand;
import com.javabeatiful.project.events.BasicListener;
import com.javabeatiful.project.events.DeathKillListener;
import com.javabeatiful.project.print.PrintAPI;
import com.javabeatiful.project.windows.Window;

import cn.nukkit.Player;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase implements Listener {

	public static ArrayList<String> maps = new ArrayList<>();

	private static PrintAPI print;

	public static Map<UUID, Boolean> uiOpen = new HashMap<UUID, Boolean>();

	public static ArrayList<UUID> firstdead = new ArrayList<>();

	public static Main plugin;

	public void onEnable() {

		plugin = this;

		getLogger().info("Name | GunGame");
		getLogger().info("Author | JavaBeatiful");
		getLogger().info("API | " + getServer().getApiVersion());
		getLogger().info("Plugin started successly");
		registerFolders();
		registerCommands();
		registerEvents();
	}

	/*
	*****************************
	*                       	*
	*          Register         *
	*                           *
	*****************************
	*/

	private void registerFolders() {

		if (!new File(getDataFolder() + "/users").exists()) {
			File folder1 = new File(getDataFolder() + "/users");
			folder1.mkdirs();

			getPrintAPI().debug("Creating User Folder");

		} else if (!new File(getDataFolder() + "/arena").exists()) {
			File arenasFolder = new File(getDataFolder() + "/arena");
			arenasFolder.mkdirs();
			getPrintAPI().debug("Creating Arena Folder");
		}
		getPrintAPI().debug("Folders successly loaded");
	}

	private void registerEvents() {
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new BasicListener(), this);
		getServer().getPluginManager().registerEvents(new DeathKillListener(), this);

		getPrintAPI().debug("Loading Events");
	}

	private void registerCommands() {
		final SimpleCommandMap commandMap = getServer().getCommandMap();
		commandMap.register("gungame", new GunGameCommand("gungame"));

		getPrintAPI().debug("Loading Commands");
	}

  // This Function coming later...

  /*private void initConfig() {
  	if (!new File(getDataFolder() + "settings.json").exists()) {
			Config settingsJson = new Config(
				new File(getDataFolder() + "settings.json"),
				Config.JSON,
			new LinkedHashMap<String, Object>() {

				{

					put("bannedPlayers", null);



				}


			});
			settingsJson.save();
		}
	}
	*/


	/*
	*****************************
	*                       	*
	*          Definer          *
	*                           *
	*****************************
	*/

	public static Main getInstance() {
		return plugin;
	}

	@EventHandler
	private void onRespone(PlayerFormRespondedEvent event) {

		Player player = event.getPlayer();

		if (event.getResponse() == null) {
			uiOpen.put(player.getUniqueId(), false);
		}
		if (!(event.getWindow() instanceof Window)) return;
		((Window)event.getWindow()).onRespone(event);
	}
	public static PrintAPI getPrintAPI() {
		print = new PrintAPI();
		return print;
	}

}