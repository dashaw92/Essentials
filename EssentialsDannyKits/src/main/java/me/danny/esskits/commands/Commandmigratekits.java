package me.danny.esskits.commands;

import com.earth2me.essentials.User;
import com.earth2me.essentials.commands.EssentialsCommand;
import org.bukkit.Server;

public final class Commandmigratekits extends EssentialsCommand {

    public Commandmigratekits() {
        super("migratekits");
    }

    @Override
    public void run(Server server, User user, String commandLabel, String[] args) throws Exception {
        user.sendMessage("Not implemented, yet");
    }
}
