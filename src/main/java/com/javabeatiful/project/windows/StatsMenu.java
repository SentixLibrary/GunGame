package com.javabeatiful.project.windows;

import com.javabeatiful.project.Main;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class StatsMenu extends FormWindowCustom implements Window {

	public StatsMenu(Player player) {
		super("MyStats");

		Config playerConfig = new Config(Main.getInstance().getDataFolder() + "/users/" + player.getName() + ".json");

		addElement(new ElementLabel(TextFormat.GREEN + "Kills: " + playerConfig.getInt("Kills")));
		
		addElement(new ElementLabel(TextFormat.RED + "Deaths: " + playerConfig.getInt("Deaths")));
		
		double kd = playerConfig.getInt("Kills") / playerConfig.getInt("Deaths");
		
		addElement(new ElementLabel(TextFormat.LIGHT_PURPLE + "K/D | " + TextFormat.GOLD + kd));
		
		

	}


	public void onRespone(PlayerFormRespondedEvent event) {

	}

}