package com.mauzerov.siktouchhands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static org.bukkit.Material.TALL_SEAGRASS;

public class SilkTouchHands extends JavaPlugin implements Listener {
    private final static Boolean DEBUG = false;
    public ArrayList<String> Players = new ArrayList<String>();

    public DataManager data;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.data = new DataManager(this);
    }

    @Override
    public void onDisable() {
        data.GetConfig().getBoolean("");
    }

    @EventHandler
    public void onPlayerBlockDestroy(org.bukkit.event.block.BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material item = player.getInventory().getItemInMainHand().getType();
        //this.data.SaveConfig();

        if (this.data.GetConfig().contains(player.getName())) {
            // Debug Message
            if (DEBUG == true) {
                player.sendMessage("Block Destroyed! -> " + event.getBlock().getType().getKey().toString());
                player.sendMessage("With Item -> " + item.getKey().toString());
            }
            Block block = event.getBlock();
            if (block.getType() == TALL_SEAGRASS) return;

            // Make Sure that Player Broke Block With Bare Hands
            if (item == Material.AIR) {
                event.setCancelled(true);
                // Replacing Block With Item
                block.getWorld().dropItem(block.getLocation(), new ItemStack(block.getType(), 1));
                block.getWorld().getBlockAt(block.getLocation()).setType(Material.AIR);
            }
        }
    }
}