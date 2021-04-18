package me.danny.essapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;

/**
 * Fuck off checkstyle
 */
public final class EssItemSellEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled = false;
    private final Player seller;
    private final ItemStack soldItem;
    private BigDecimal value;
    private final SellSource source;

    public EssItemSellEvent(Player seller, ItemStack soldItem, BigDecimal value, SellSource source) {
        this.seller = seller;
        this.soldItem = soldItem;
        this.value = value;
        this.source = source;
    }

    public Player getSeller() {
        return seller;
    }

    public ItemStack getSoldItem() {
        return soldItem;
    }

    public BigDecimal getValue() {
        return value;
    }

    public SellSource getSource() {
        return source;
    }

    public void setValue(BigDecimal newValue) {
        value = newValue;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Fuck off checkstyle
     */
    public enum SellSource {
        COMMAND,
        SIGN
    }
}
