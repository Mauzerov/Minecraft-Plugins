package com.mauzerov.rushpearl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.*;

public class PearlClass implements Listener {
    public static NavigableSet<UUID> players;
    public final static String PEARL_NAME = "..RushPearl..";
    public final static String PEARL_ITEM__NAME = "§9§oRush Pearl";

    public static ItemStack CreateRushPearl(int amount) {
        ItemStack item = new ItemStack(Material.ENDER_PEARL, Math.min(amount, 64));
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(PEARL_ITEM__NAME);
        List<String> lore = new ArrayList<>();
        lore.add("§7Rush Pearl");
        lore.add("");
        lore.add("§7Ride A Pearl!");

        meta.setLore(lore);

        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public static void PlayerUsePearl(ProjectileLaunchEvent event){
        if (event.getEntity().getType() != EntityType.ENDER_PEARL) return;
        if (!(event.getEntity().getShooter() instanceof Player)) return;

        if (Objects.requireNonNull(((Player) event.getEntity().getShooter())
                .getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(PEARL_ITEM__NAME)) {

            Entity entity = event.getEntity();

            entity.setCustomName(PEARL_NAME);
            entity.addPassenger((Player) Objects.requireNonNull(event.getEntity().getShooter()));
        }
    }
    @EventHandler
    public static void PlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager().getCustomName() != null)
        if (event.getDamager().getCustomName().equals(PEARL_NAME)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void PlayerDismount(EntityDismountEvent event) {
        if (event.getDismounted().getCustomName() != null)
        if (event.getDismounted().getName().equals(PEARL_NAME)) {
            event.getDismounted().remove();
        }
    }
}
