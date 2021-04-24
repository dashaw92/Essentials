package me.danny.esskits.kits;

import net.ess3.api.IUser;
import org.bukkit.configuration.ConfigurationSection;

public interface KitItem {

    void parseFromConfig(ConfigurationSection config);

    void applyToUser(IUser user);

}
