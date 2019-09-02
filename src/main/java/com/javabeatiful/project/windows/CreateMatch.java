package com.javabeatiful.project.windows;

import java.io.File;
import java.util.LinkedHashMap;

import com.javabeatiful.project.Main;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import com.javabeatiful.project.windows.Window;

public class CreateMatch extends FormWindowCustom implements Window{
	public CreateMatch() {
		super("GunGame - Add Match");

		addElement(new ElementInput("Arena Name"));
		addElement(new ElementInput("World Name"));
	}
	public void onRespone(PlayerFormRespondedEvent event) {
		Player player = event.getPlayer();
		String arenaName = getResponse().getInputResponse(0);
		String worldName = getResponse().getInputResponse(1);

		if (arenaName.equals("") || worldName.equals("")) {
			return;
		} else {
			if (new File(Main.getInstance().getDataFolder() + "/arena/" + arenaName + ".json").exists()) {
				player.sendMessage(Main.prefix + TextFormat.GREEN + "Arena successful created");
				return;
			} else {
				Config arenaJson = new Config(
					new File(Main.getInstance().getDataFolder() + "/arena/" + arenaName + ".json"),
					Config.JSON,
				new LinkedHashMap<String, Object>() {

					{

						put("Name", arenaName);
						put("World", worldName);



					}

				});
				arenaJson.save();
                player.sendMessage(Main.prefix +TextFormat.GREEN + "Arena " + arenaName + "created at Level: " + worldName);
                Main.uiOpen.put(player.getUniqueId(), false);
			}
		}
	}
}