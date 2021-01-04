package me.NotEnoughSkill.Giveaway;

import com.mysql.jdbc.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Commands implements CommandExecutor {
    Main plugin = Main.getPlugin(Main.class);

    List<Player> players = new ArrayList<>();
    List<ItemStack> giveItem = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (!(sender instanceof Player)) {
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.color("&4You can only run this command through in game!"));
        }

        if (!player.hasPermission("giveaway.join")) {
            player.sendMessage(Utils.color("&4You do not have enough permissions to do this!"));
        }

        if (!player.hasPermission("giveaway.create")) {
            player.sendMessage(Utils.color("&4You do not have enough permissions to do this!"));
        }

        if (args.length == 0) {
            player.sendMessage(Utils.color("&4Invalid Arguments!"));
        }



        if (args.length == 1 && args[0].equalsIgnoreCase("create")) {
            if (player.hasPermission("giveaway.create")) {
                if (!this.plugin.isGiveawayOn()) {
                    if (!(player.getItemInHand().getType() == Material.AIR)) {
                        this.plugin.setGiveawayOn(true);
                        giveItem.add(player.getItemInHand());
                        Bukkit.broadcastMessage(Utils.color("&c&l[!] A Giveaway for " + player.getItemInHand().getItemMeta().getDisplayName() + "!"));
                        Bukkit.broadcastMessage(Utils.color("&c&l[!] Do / giveaway join to enter!"));
                        player.setItemInHand(new ItemStack(Material.AIR));

                        this.players.add(player);
                    } else {
                        player.sendMessage(Utils.color("&4&lYou must have an item in your hand!"));
                    }
                } else {
                    player.sendMessage(Utils.color("&4A Giveaway is already started!"));
                }
            }
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("join")) {
            if (!this.players.contains(player)) {
                if (this.plugin.isGiveawayOn()) {
                    this.players.add(player);
                    player.sendMessage(Utils.color("&c&l[!] You have entered the giveaway!"));
                } else {
                    player.sendMessage(Utils.color("&c&l[!] There is no active giveaway!"));
                }
            } else {
                player.sendMessage(Utils.color("&c&l[!] You have already entered the giveaway!"));
            }
        }

        Random r  = new Random();
        if (!this.players.isEmpty()) {
            int randomPlayer = r.nextInt(this.players.size());
            Player winner = this.players.get(randomPlayer);
            Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 60 seconds!"));
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 30 seconds!")), 600L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 10 seconds!")), 800L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 5 seconds!")), 900L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 4 seconds!")), 920L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 3 seconds!")), 940L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 2 seconds!")), 960L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] Giveaway ending in 1 second!")), 980L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> this.plugin.setGiveawayOn(false), 980L);
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> Bukkit.broadcastMessage(Utils.color("&c&l[!] " + winner.getName() + " has one the giveaway!")), 1000L);
            winner.getInventory().addItem((ItemStack) giveItem);
            this.players.clear();
            this.giveItem.clear();
        }
        return true;
    }
}
