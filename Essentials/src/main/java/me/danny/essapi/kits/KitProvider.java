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
        final KitDelay nextUse = getNextUse(user);

        if (nextUse == KitDelay.ONE_USE_KIT) {
            user.sendMessage(tl("kitOnce"));
            throw new NoChargeException();
        } else if(nextUse == KitDelay.NO_DELAY) {
            return;
        }

        //Once we get switch expressions with instanceof pattern matching, this
        //will become less obtuse
        if (nextUse instanceof KitDelay.Delay delay) {
            user.sendMessage(tl("kitTimed", DateUtil.formatDateDiff(delay.delay())));
            throw new NoChargeException();
        }
    }

    default void checkAffordable(User user) throws Exception {
        getCharge().isAffordableFor(user);
    }

    default void chargeUser(User user) throws Exception {
        getCharge().charge(user);
    }

    default KitDelay getNextUse(User user) throws Exception {
        if (user.isAuthorized("essentials.kit.exemptdelay")) {
            return KitDelay.NO_DELAY;
        }

        final Calendar time = new GregorianCalendar();

        double delay = getDelay();

        // When was the last kit used?
        final long lastTime = user.getKitTimestamp(getName());

        // When can we use the kit again?
        final Calendar delayTime = new GregorianCalendar();
        delayTime.setTimeInMillis(lastTime);
        delayTime.add(Calendar.SECOND, (int) delay);
        delayTime.add(Calendar.MILLISECOND, (int) ((delay * 1000.0) % 1000.0));

        if (lastTime == 0L || lastTime > time.getTimeInMillis()) {
            // If we have no record of kit use, or its corrupted, give them benefit of the doubt.
            return KitDelay.NO_DELAY;
        } else if (delay < 0d) {
            // If the kit has a negative kit time, it can only be used once.
            return KitDelay.ONE_USE_KIT;
        } else if (delayTime.before(time)) {
            // If the kit was used in the past, but outside the delay time, it can be used.
            return KitDelay.NO_DELAY;
        } else {
            // If the kit has been used recently, return the next time it can be used.
            return new KitDelay.Delay(delayTime.getTimeInMillis());
        }
    }

    double getDelay() throws Exception;

    Trade getCharge();

    String getName();

    List<Item> getItems() throws Exception;

    boolean expandItems(User user) throws Exception;

    boolean expandItems(User user, List<Item> items) throws Exception;
}
