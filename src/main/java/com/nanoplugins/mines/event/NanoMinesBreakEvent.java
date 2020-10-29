package com.nanoplugins.mines.event;

import com.nanoplugins.mines.model.BlockModel;
import com.nanoplugins.mines.util.NumberFormat;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NanoMinesBreakEvent extends Event {

    private final BlockModel blockModel;
    private final double money, bonus;
    private final Block block;
    private final NumberFormat numberFormat;

    public BlockModel getBlockModel() {
        return blockModel;
    }

    public double getBonus() {
        return bonus;
    }

    public Block getBlock() {
        return block;
    }

    public double getMoney() {
        return money;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public NanoMinesBreakEvent(BlockModel blockModel, double money, double bonus, Block block, NumberFormat numberFormat) {
        this.blockModel = blockModel;
        this.money = money;
        this.bonus = bonus;
        this.block = block;
        this.numberFormat = numberFormat;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
