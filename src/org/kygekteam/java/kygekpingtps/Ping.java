package org.kygekteam.java.kygekpingtps;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping {
    private final CommandSender sender;
    private final String[] args;
    private final Main main;

    public Ping(CommandSender sender, String[] args, Main main) {
        this.sender = sender;
        this.args = args;
        this.main = main;
    }

    public void execute() {
        if (!this.sender.hasPermission("kygekpingtps.ping")) {
            String noperm = Main.replace(this.main.getConfig().getString("no-permission", ""));
            noperm = noperm.isEmpty() ? Main.PREFIX + ChatColor.RED + "You do not have permission to use this command" : noperm;

            this.sender.sendMessage(noperm);
            return;
        }

        if (this.args.length < 1) {
            if (!(this.sender instanceof Player)) {
                this.sender.sendMessage(Main.PREFIX + ChatColor.WHITE + "Usage: /ping <player>");
            } else {
                String ping = Main.replace(this.main.getConfig().getString("player-ping", "")).replace("{player}", "Your").replace("{ping}", Integer.toString(((Player) sender).getPing()));
                ping = ping.isEmpty() ? Main.PREFIX + ChatColor.GREEN + "Your current ping: " + ChatColor.AQUA + ((Player) sender).getPing() : ping;

                this.sender.sendMessage(ping);
            }
            return;
        }

        Player player = sender.getServer().getPlayer(this.args[0]);
        if (player == null) {
            String notfound = Main.replace(this.main.getConfig().getString( "player-not-found", ""));
            notfound = notfound.isEmpty() ? Main.PREFIX + ChatColor.RED + "Player was not found" : notfound;

            this.sender.sendMessage(notfound);
            return;
        }

        String ping = Main.replace(this.main.getConfig().getString("player-ping", "")).replace("{player}", player.getName()).replace("{ping}", Integer.toString(player.getPing()));
        ping = ping.isEmpty() ? Main.PREFIX + ChatColor.GREEN + player.getName() + "'s current ping: " + ChatColor.AQUA + player.getPing() : ping;

        this.sender.sendMessage(ping);
    }
}
