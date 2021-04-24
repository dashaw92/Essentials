package me.danny.essapi;

import java.util.Locale;

/**
 * Provides a better abstraction for denoting delays on kit usage.
 * Previously, the methods returned magic numbers (-1 for only one use, 0 for no delay)
 */
public sealed interface KitDelay {

    static KitDelay parseFromString(String input) {
        if(input == null || input.isBlank() || input.equalsIgnoreCase("not configured")) return KitDelay.NOT_CONFIGURED;
        return switch(input.toLowerCase(Locale.ENGLISH)) {
            case "none" -> KitDelay.NO_DELAY;
            case "once" -> KitDelay.ONE_USE_KIT;
            default -> {
                var delay = Long.parseLong(input);
                if(delay <= 0) yield KitDelay.NO_DELAY;
                yield KitDelay.DELAY(Long.parseLong(input));
            }
        };
    }

    /**
     * Re-export of _Predefined::NO_DELAY
     */
    KitDelay NO_DELAY = _Predefined.NO_DELAY;
    /**
     * Re-export of _Predefined::ONE_USE_KIT
     */
    KitDelay ONE_USE_KIT = _Predefined.ONE_USE_KIT;
    /**
     * Re-export of _Predefined::NOT_CONFIGURED
     */
    KitDelay NOT_CONFIGURED = _Predefined.NOT_CONFIGURED;

    /**
     * Convenience class to construct a delay without the new keyword
     */
    static KitDelay.Delay DELAY(long delay) {
        return new KitDelay.Delay(delay);
    }

    /**
     * There is a delay before the kit may be used again
     */
    record Delay(long delay) implements KitDelay {}

    /**
     * Implementation detail.
     * Not intended for public use, use the re-exported versions in KitDelay
     */
    enum _Predefined implements KitDelay {
        /**
         * @link {KitDelay#NO_DELAY}
         */
        NO_DELAY,
        /**
         * @link {KitDelay#ONE_USE_KIT}
         */
        ONE_USE_KIT,
        /**
         * @link {KitDelay#NOT_CONFIGURED}
         */
        NOT_CONFIGURED,
    }
}