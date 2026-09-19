package eu.kanade.tachiyomi.ui.reader.viewer

/**
 * Shared tuning for the auto-scroll feature. Speed is expressed as a percentage (1..100) so it can
 * drive both smooth-scrolling viewers (distance per second) and page-based viewers (interval
 * between page advances).
 */
object AutoScroll {

    /** Minimum user-selectable speed percentage. */
    const val MIN_SPEED = 1

    /** Maximum user-selectable speed percentage. */
    const val MAX_SPEED = 100

    /** Distance scrolled per second at 100% speed, in dp, for smooth-scrolling viewers. */
    const val MAX_DP_PER_SECOND = 500f

    /** Interval between page advances at minimum speed, for page-based viewers. */
    const val MAX_PAGE_INTERVAL_MILLIS = 6000L

    /** Interval between page advances at maximum speed, for page-based viewers. */
    const val MIN_PAGE_INTERVAL_MILLIS = 250L

    /** Linearly maps [speedPercent] to the interval between two page advances. */
    fun pageIntervalMillis(speedPercent: Int): Long {
        val clamped = speedPercent.coerceIn(MIN_SPEED, MAX_SPEED)
        val fraction = (clamped - MIN_SPEED) / (MAX_SPEED - MIN_SPEED).toFloat()
        return (MAX_PAGE_INTERVAL_MILLIS - (MAX_PAGE_INTERVAL_MILLIS - MIN_PAGE_INTERVAL_MILLIS) * fraction).toLong()
    }
}
