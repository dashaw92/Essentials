package me.danny.essapi.kits;

import com.earth2me.essentials.Trade;
import com.earth2me.essentials.User;
import net.ess3.api.IEssentials;

import java.util.List;
import java.util.Map;

import static com.earth2me.essentials.I18n.tl;

public final class DannyKit implements KitProvider<KitItem> {

    private final IEssentials ess;
    private final String kitName;
    private final Map<String, Object> kit;
    private final Trade charge;

    public DannyKit(final String kitName, final IEssentials ess) throws Exception {
        this.kitName = kitName;
        this.ess = ess;
        this.kit = ess.getKits().getKit(kitName);
        this.charge = new Trade("kit-" + kitName, new Trade("kit-kit", ess), ess);

        if (kit == null) {
            throw new Exception(tl("kitNotFound"));
        }
    }

    @Override
    public Trade getCharge() {
        return charge;
    }

    @Override
    public String getName() {
        return kitName;
    }

    @Override
    public double getDelay() throws Exception {
        return 0d;
    }

    @Override
    public KitDelay getNextUse(User user) throws Exception {
        return KitDelay.NO_DELAY;
    }

    @Override
    public List<KitItem> getItems() throws Exception {
        return null;
    }

    @Override
    public boolean expandItems(User user) throws Exception {
        return false;
    }

    @Override
    public boolean expandItems(User user, List<KitItem> kitItems) throws Exception {
        return false;
    }
}
