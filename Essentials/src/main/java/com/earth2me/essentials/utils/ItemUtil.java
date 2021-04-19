package com.earth2me.essentials.utils;

import org.bukkit.inventory.ItemStack;

public final class ItemUtil {

    public static boolean isEmptyStack(ItemStack stack) {
        return stack == null || stack.getType().isAir();
    }

}
