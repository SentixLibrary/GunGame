package com.javabeatiful.project.player;

import com.javabeatiful.project.Main;

import cn.nukkit.Player;
import cn.nukkit.Server;

public class PlayerAPI {


	public static boolean isOperator(Player player) {
		if (Server.getInstance().isOp(player.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public static void addOperator(Player player) {
		if (Server.getInstance().isOp(player.getName())) {
			Main.getPrintAPI().debug(player.getName() + " failed to add as Operator! Error: Already");
		} else {
			Server.getInstance().addOp(player.getName());
			Main.getPrintAPI().debug(player.getName() + " added as Operator");
		}
	}
}