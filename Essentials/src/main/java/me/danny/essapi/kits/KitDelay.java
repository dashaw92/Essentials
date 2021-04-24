package me.danny.essapi.kits;

/**
 * Provides a better abstraction for denoting delays on kit usage.
 * Previously, the methods returned magic numbers (-1 for only one use, 0 for no delay)
 */
public sealed interface KitDelay {

    /**
     * Re-export of KitDelayPredefined::NO_DELAY
     */
    KitDelay NO_DELAY = _Predefined.NO_DELAY;
    /**
     * Re-export of KitDelayPredefined::ONE_USE_KIT
     */
    KitDelay ONE_USE_KIT = _Predefined.ONE_USE_KIT;

    /**
     * There is a delay before the kit may be used again
     */
    record Delay(long delay) implements KitDelay {}

    enum _Predefined implements KitDelay {
        NO_DELAY,
        ONE_USE_KIT
    }
}