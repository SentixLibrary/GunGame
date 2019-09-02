package com.javabeatiful.project.windows.failed;

import com.javabeatiful.project.windows.Window;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.utils.TextFormat;

public class RemoveArenaUIFailed extends FormWindowCustom implements Window {

	public RemoveArenaUIFailed() {
		super("Action failed!");

		addElement(new ElementLabel(TextFormat.RED + "Invaild Action failed."));
		addElement(new ElementLabel(TextFormat.RED + "Error Code: X16F19-RAU"));
		addElement(new ElementLabel("Please contact the Engineering Team!"));
	}

	public void onRespone(PlayerFormRespondedEvent event) {

	}

}