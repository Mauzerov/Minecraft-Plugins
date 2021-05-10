package com.mauzerov.siktouchhands;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {
    private SilkTouchHands plugin;
    private FileConfiguration fileConfiguration = null;
    private File file = null;

    public DataManager(SilkTouchHands plugin) {
        this.plugin = plugin;
        SaveDefaultConfig();
    }

    public void ReloadConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), "players.yml");
        }

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        InputStream defaultStream = this.plugin.getResource("players.yml");

        if (defaultStream != null) {
            YamlConfiguration defaultConfig =
                    YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.fileConfiguration.setDefaults(defaultConfig);

        }
    }

    public FileConfiguration GetConfig() {
        if (this.fileConfiguration == null)
            this.ReloadConfig();
        return this.fileConfiguration;
    }

    public void SaveConfig() {
        if (this.fileConfiguration == null || this.file == null)
            return;

        try {
            this.GetConfig().save(this.file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unable To Save Config");
        }
    }

    public void SaveDefaultConfig() {
        if (this.file == null)
            this.file = new File(this.plugin.getDataFolder(), "players.yml");

        if (!this.file.exists()) {
            this.plugin.saveResource("players.yml", false);
        }
    }
}
