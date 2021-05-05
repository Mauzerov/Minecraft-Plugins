package com.mauzerov.rushpearl;

import com.sun.jmx.mbeanserver.NamedObject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static com.mauzerov.rushpearl.PearlClass.*;

public class RushPearl extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new PearlClass(), this);

        getCommand("getpearl").setExecutor(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("getpearl")) {
            int amount = 1;
            if (args.length > 0) {
                try {
                    amount = Integer.parseInt(args[0]);
                } catch (Exception ignored) {};
            }
            ((Player) sender).getInventory().addItem(PearlClass.CreateRushPearl(amount));
        }

        return true;
    }
}
