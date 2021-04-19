package me.danny.essapi.kits;

import com.earth2me.essentials.Trade;
import com.earth2me.essentials.User;
import com.earth2me.essentials.commands.NoChargeException;
import com.earth2me.essentials.utils.DateUtil;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static com.earth2me.essentials.I18n.tl;

public interface KitProvider<Item> {

    default void checkPerms(User user) throws Exception {
        if (!user.isAuthorized("essentials.kits." + getName())) {
            throw new Exception(tl("noKitPermission", "essentials.kits." + getName()));
        }
    }

    default void setTime(User user) {
        final Calendar time = new GregorianCalendar();
        user.setKitTimestamp(getName(), time.getTimeInMillis());
    }

    default void resetTime(User user) {
        user.setKitTimestamp(getName(), 0);
    }

    default void checkDelay(User user) throws Exception {
        final long nextUse = getNextUse(user);

        if (nextUse == 0L) {
        } else if (nextUse < 0L) {
            user.sendMessage(tl("kitOnce"));
            throw new NoChargeException();
        } else {
            user.sendMessage(tl("kitTimed", DateUtil.formatDateDiff(nextUse)));
            throw new NoChargeException();
        }
    }

    default void checkAffordable(User user) throws Exception {
        getCharge().isAffordableFor(user);
    }

    default void chargeUser(User user) throws Exception {
        getCharge().charge(user);
    }

    Trade getCharge();

    String getName();

    long getNextUse(User user) throws Exception;

    List<Item> getItems() throws Exception;

    boolean expandItems(User user) throws Exception;

    boolean expandItems(User user, List<Item> items) throws Exception;
}
