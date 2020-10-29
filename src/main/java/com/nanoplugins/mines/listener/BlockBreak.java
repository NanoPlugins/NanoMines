package com.nanoplugins.mines.listener;

import com.nanoplugins.mines.NanoMines;
import com.nanoplugins.mines.dao.BlockDao;
import com.nanoplugins.mines.event.NanoMinesBreakEvent;
import com.nanoplugins.mines.hook.vault.VaultHook;
import com.nanoplugins.mines.manager.BonusManager;
import com.nanoplugins.mines.model.BlockModel;
import com.nanoplugins.mines.model.BlockType;
import com.nanoplugins.mines.util.NumberFormat;
import com.nanoplugins.stockmarket.NanoStockMarketAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;
import java.util.Optional;

public class BlockBreak implements Listener {

    private final BlockDao dao;
    private final VaultHook hook;
    private final BonusManager manager;
    private final NumberFormat numberFormat;
    private final boolean useFortune, useActionBar, useMessage, useStockMarket;
    private final String actionbar, message;
    private final List<String> worlds;
    private NanoStockMarketAPI nanoStockMarketAPI;

    public BlockBreak(NanoMines plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.dao = plugin.getDao();
        this.manager = new BonusManager(plugin.getConfig());
        this.hook = new VaultHook();
        this.numberFormat = new NumberFormat();
        useStockMarket = plugin.getConfig().getBoolean("settings.nano-stock-market");
        useFortune = plugin.getConfig().getBoolean("settings.fortune");
        useActionBar = plugin.getConfig().getBoolean("settings.actionbar");
        useMessage = plugin.getConfig().getBoolean("settings.message");
        worlds = plugin.getConfig().getStringList("settings.worlds");
        actionbar = plugin.getConfig().getString("messages.actionbar").replace("&", "§");
        message = plugin.getConfig().getString("messages.message").replace("&", "§");
        if (useStockMarket) nanoStockMarketAPI = NanoStockMarketAPI.get();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (!worlds.contains(block.getWorld().getName())) return;

        BlockModel blockModel = dao.get(new BlockType(block.getType(), block.getData()));
        if (blockModel == null) return;

        event.setCancelled(true);

        Player player = event.getPlayer();

        int fortune = getFortune(player);

        double money = useFortune ? blockModel.getMoney() * fortune : blockModel.getMoney();

        Optional<Double> bonusOptional = manager.get(hook.getPerms().getPrimaryGroup(player));

        double bonus = bonusOptional.orElse(1.0);

        money *= bonus;

        int stockMarket = useStockMarket ? nanoStockMarketAPI.getStockMarket() : 1;

        money *= stockMarket;

        Bukkit.getPluginManager().callEvent(new NanoMinesBreakEvent(blockModel, money, bonus, block, useStockMarket ? nanoStockMarketAPI.getStockMarket() : 1, numberFormat));

        block.setType(Material.AIR);

        hook.getEconomy().depositPlayer(player, money);

        if (useActionBar)
            sendMessage(player, actionbar
                    .replace("%stockMarket%", Integer.toString(stockMarket))
                    .replace("%block%", blockModel.getDisplayName())
                    .replace("%fortune%", Integer.toString(fortune))
                    .replace("%bonus%", Double.toString(bonus)).replace("%money%", numberFormat.formatNumber(money)));
        if (useMessage)
            player.sendMessage(message
                    .replace("%stockMarket%", Integer.toString(stockMarket))
                    .replace("%block%", blockModel.getDisplayName())
                    .replace("%fortune%", Integer.toString(fortune))
                    .replace("%bonus%", Double.toString(bonus)).replace("%money%", numberFormat.formatNumber(money)));

    }

    private void sendMessage(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message.replace("&", "§") + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    private int getFortune(Player player) {
        if (player.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS))
            return player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
        return 1;
    }

}
