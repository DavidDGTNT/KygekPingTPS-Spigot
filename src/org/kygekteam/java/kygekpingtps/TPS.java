/*
 * See server TPS and a player's ping
 * Copyright (C) 2021 KygekTeam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package org.kygekteam.java.kygekpingtps;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TPS {
    private final CommandSender sender;
    private final Main main;

    public TPS(CommandSender sender, Main main) {
        this.sender = sender;
        this.main = main;
    }

    public void execute() {
        double tps = Lag.getTPS();

        if (sender.hasPermission("kygekpingtps.tps")) {
            String servertps = Main.replace(this.main.getConfig().getString("server-tps", "")).replace("{tps}", Double.toString(tps));
            servertps = servertps.isEmpty() ? Main.PREFIX + ChatColor.GREEN + "Current server TPS: " + ChatColor.AQUA + tps : servertps;

            this.sender.sendMessage(servertps);
        } else {
            String noperm = Main.replace(this.main.getConfig().getString("no-permission", ""));
            noperm = noperm.isEmpty() ? Main.PREFIX + ChatColor.RED + "You do not have permission to use this command" : noperm;

            this.sender.sendMessage(noperm);
        }
    }
}
