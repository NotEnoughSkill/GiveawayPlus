package me.NotEnoughSkill.Giveaway;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public boolean giveawayOn;

    @Override
    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c*******************"));
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c*****Giveaway+*****"));
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c******Enabled******"));
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c*******************"));
        getCommand("giveaway").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c*******************"));
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c*****Giveaway+*****"));
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c*****Disabled******"));
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&c*******************"));
    }

    public void setGiveawayOn(boolean giveawayOn) {
        this.giveawayOn = giveawayOn;
    }

    public boolean isGiveawayOn() {
        return this.giveawayOn;
    }
}
