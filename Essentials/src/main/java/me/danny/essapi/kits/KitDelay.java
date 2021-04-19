package me.danny.essapi.kits;

/**
 * Provides a better abstraction for denoting delays on kit usage.
 * Previously, the methods returned magic numbers (-1 for only one use, 0 for no delay)
 */
public sealed interface KitDelay {

    KitDelay NO_DELAY = new NoDelay();
    KitDelay ONE_USE_KIT = new OneUseKit();

    /**
     * The kit has no delay between uses, or is ready to be used again
     */
    final class NoDelay implements KitDelay {
        private NoDelay() {}
    }

    /**
     * The kit can be only used one time
     */
    final class OneUseKit implements KitDelay {
        private OneUseKit() {}
    }

    /**
     * There is a delay before the kit may be used again
     */
    record Delay(long delay) implements KitDelay {}
}
