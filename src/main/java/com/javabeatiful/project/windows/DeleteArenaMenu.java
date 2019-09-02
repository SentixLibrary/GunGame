package com.javabeatiful.project.windows;

import java.io.File;
import java.io.FilenameFilter;

import com.javabeatiful.project.Main;
import com.javabeatiful.project.windows.failed.RemoveArenaUIFailed;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;

public class DeleteArenaMenu extends FormWindowCustom implements Window {

	public DeleteArenaMenu() {
		super("Delete a Arena");

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
			addElement(new ElementLabel(TextFormat.LIGHT_PURPLE + f.getName()));
		}
		addElement(new ElementInput(TextFormat.RED + "Type Arena name without .json"));

	}

	public void onRespone(PlayerFormRespondedEvent event) {
		String arenaString = getResponse().getInputResponse(0);

		Player player = event.getPlayer();

		if (!new File(Main.getInstance().getDataFolder() + "/arena/" + arenaString + ".json").exists()) {
			player.showFormWindow(new RemoveArenaUIFailed());
		} else {
			File folderFile = new File(Main.getInstance().getDataFolder() + "/arena/" + arenaString + ".json");
			if (folderFile.isDirectory()) {
				player.showFormWindow(new RemoveArenaUIFailed());
			} else {
				folderFile.delete();
				player.sendMessage(Main.prefix + TextFormat.GREEN + "Arena successful deleted");
			}
		}
	}

}