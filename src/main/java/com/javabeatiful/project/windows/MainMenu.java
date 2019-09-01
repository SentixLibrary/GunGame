package com.javabeatiful.project.windows;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import com.javabeatiful.project.windows.MatchList;
import com.javabeatiful.project.windows.Window;
import com.javabeatiful.project.windows.CreateMatch;

public class MainMenu extends FormWindowSimple implements Window{

	public MainMenu(Player player) {
		super("GunGame - Main", "GunGame Options");

		addButton(new ElementButton("Join"));
		addButton(new ElementButton("Stats"));
		
		if(player.hasPermission("gungame.create") || player.isOp()){
		addButton(new ElementButton("Create a Match"));
		}
		
	}

	public void onRespone(PlayerFormRespondedEvent event) {
		Player player = event.getPlayer();
		int hoverButton = getResponse().getClickedButtonId();

		if (!((FormWindowSimple)event.getWindow()).getTitle().equals("GunGame - Main")) return;

		if (hoverButton == 0) {
			player.showFormWindow(new MatchList());
		} else if(hoverButton == 2){
			if(player.hasPermission("gungame.create") || player.isOp()){
			player.showFormWindow(new CreateMatch());            }
		} else if(hoverButton == 1){
			player.showFormWindow(new StatsMenu(player));
		}
	}

}