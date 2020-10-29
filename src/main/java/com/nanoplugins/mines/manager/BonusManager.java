package com.nanoplugins.mines.manager;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Optional;

public class BonusManager {

    private final HashMap<String, Double> bonuses = new HashMap<>();

    public BonusManager(FileConfiguration config) {
        for (String key : config.getConfigurationSection("bonus").getKeys(false)) {
            add(config.getString("bonus." + key + ".group"), config.getDouble("bonus." + key + ".value"));
        }
    }

    public void add(String group, double bonus) {
        bonuses.put(group, bonus);
    }

    public Optional<Double> get(String group) {
        return Optional.ofNullable(bonuses.get(group));
    }

    public HashMap<String, Double> getBonuses() {
        return bonuses;
    }
}
