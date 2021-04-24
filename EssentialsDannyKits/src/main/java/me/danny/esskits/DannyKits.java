package me.danny.esskits;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.IEssentialsModule;
import net.ess3.api.IEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static com.earth2me.essentials.I18n.tl;

public final class DannyKits extends JavaPlugin implements IEssentialsModule {

    private transient IEssentials ess;

    @Override
    public void onEnable() {
        //copied from EssentialsSpawn (generic Essentials sub-plugin boilerplate)
        final PluginManager pluginManager = getServer().getPluginManager();
        ess = (IEssentials) pluginManager.getPlugin("Essentials");
        if (!this.getDescription().getVersion().equals(ess.getDescription().getVersion())) {
            getLogger().log(Level.WARNING, tl("versionMismatchAll"));
        }
        if (!ess.isEnabled()) {
            this.setEnabled(false);
            return;
        }
        //</copy>
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args) {
        return ess.onCommandEssentials(sender, command, commandLabel, args, DannyKits.class.getClassLoader(), "me.danny.esskits.commands.Command", "essentials.", this);
    }
}
