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
