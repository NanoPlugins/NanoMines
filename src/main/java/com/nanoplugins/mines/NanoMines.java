package com.nanoplugins.mines;

import com.nanoplugins.mines.dao.BlockDao;
import com.nanoplugins.mines.listener.BlockBreak;
import com.nanoplugins.mines.process.BlockProcess;
import org.bukkit.plugin.java.JavaPlugin;

public class NanoMines extends JavaPlugin {

    private BlockDao dao;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        dao = new BlockDao();
        new BlockProcess(dao, getConfig());
        if (getConfig().getBoolean("settings.nano-stock-market") && getServer().getPluginManager().getPlugin("NanoBolsa") == null) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        new BlockBreak(this);
    }

    public BlockDao getDao() {
        return dao;
    }
}
