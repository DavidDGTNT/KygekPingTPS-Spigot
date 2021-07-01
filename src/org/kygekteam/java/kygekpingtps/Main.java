package org.kygekteam.java.kygekpingtps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
        this.saveDefaultConfig();
        this.checkConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.reloadConfig();
        switch (command.getName().toLowerCase()) {
            case "tps":
                new TPS(sender, this).execute();
                break;
            case "ping":
                new Ping(sender, args, this).execute();
                break;
        }
        return true;
    }

    private void checkConfig() {
        if (!this.getConfig().getString("config-version").equals("1.0")) {
            getServer().getConsoleSender().sendMessage("Your configuration file is outdated, updating the config.yml...");
            getServer().getConsoleSender().sendMessage("The old configuration file can be found at config-old.yml");
            this.renameConfig();
            this.saveDefaultConfig();
            this.reloadConfig();
        }
    }

    private void renameConfig() {
        File oldConfig = new File(this.getDataFolder() + "/config.yml");
        File newConfig = new File(this.getDataFolder() + "/config-old.yml");

        if (newConfig.exists()) newConfig.delete();

        oldConfig.renameTo(newConfig);
    }

    public static final String PREFIX = ChatColor.YELLOW + "[KygekPingTPS] ";

    public static String replace(String string) {
        return string.replace("{prefix}", PREFIX).replace("&", "§");
    }
}
