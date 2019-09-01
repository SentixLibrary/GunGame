package com.javabeatiful.project.commands;

import com.javabeatiful.project.Main;
import com.javabeatiful.project.windows.MainMenu;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class GunGameCommand extends Command{

    public GunGameCommand(String name){
    	super(name);
    	setDescription(TextFormat.GOLD + "GunGame Command");
    }

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
	   if(sender instanceof Player){
	     Player player = (Player)sender;
	     if(!Main.uiOpen.containsKey(player.getUniqueId())){
	     	Main.uiOpen.put(player.getUniqueId(), true);
	     	player.showFormWindow(new MainMenu(player));
	     } else {
	     
	   	player.showFormWindow(new MainMenu(player));
	   	Main.uiOpen.put(player.getUniqueId(), true);
	     } 
	       
	     
	   } else
	   {
	   	
	   }
		return true;
	}
	
}