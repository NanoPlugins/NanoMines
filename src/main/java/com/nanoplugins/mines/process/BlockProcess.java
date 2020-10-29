package com.nanoplugins.mines.process;

import com.nanoplugins.mines.dao.BlockDao;
import com.nanoplugins.mines.model.BlockModel;
import com.nanoplugins.mines.model.BlockType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class BlockProcess {

    public BlockProcess(BlockDao dao, FileConfiguration config) {
        for (String key : config.getConfigurationSection("blocks").getKeys(false)) {
            dao.add(new BlockModel(new BlockType(Material.getMaterial(config.getInt("blocks." + key + ".type")),
                    (byte) config.getInt("blocks." + key + ".data")),
                    config.getString("blocks." + key + ".name").replace("&", "§"),
                    config.getDouble("blocks." + key + ".money")));
        }
    }

}
