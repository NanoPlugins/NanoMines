package com.nanoplugins.mines.hook;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHook {

    private final Economy economy;
    private final Permission perms;

    public VaultHook() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        economy = rsp.getProvider();
        RegisteredServiceProvider<Permission> rspPerm = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rspPerm.getProvider();
    }

    public Economy getEconomy() {
        return economy;
    }

    public Permission getPerms() {
        return perms;
    }
}
