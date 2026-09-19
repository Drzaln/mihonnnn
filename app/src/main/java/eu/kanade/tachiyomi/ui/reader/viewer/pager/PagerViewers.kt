package eu.kanade.tachiyomi.ui.reader.viewer.pager

import eu.kanade.tachiyomi.ui.reader.ReaderActivity
import eu.kanade.tachiyomi.ui.reader.viewer.AutoScroll

/**
 * Implementation of a left to right PagerViewer.
 */
class L2RPagerViewer(activity: ReaderActivity) : PagerViewer(activity) {
    /**
     * Creates a new left to right pager.
     */
    override fun createPager(): Pager {
        return Pager(activity)
    }
}

/**
 * Implementation of a right to left PagerViewer.
 */
class R2LPagerViewer(activity: ReaderActivity) : PagerViewer(activity) {
    /**
     * Creates a new right to left pager.
     */
    override fun createPager(): Pager {
        return Pager(activity)
    }

    /**
     * Moves to the next page. On a R2L pager the next page is the one at the left.
     */
    override fun moveToNext() {
        moveLeft()
    }

    /**
     * Moves to the previous page. On a R2L pager the previous page is the one at the right.
     */
    override fun moveToPrevious() {
        moveRight()
    }
}

/**
 * Implementation of a vertical (top to bottom) PagerViewer.
 */
class VerticalPagerViewer(activity: ReaderActivity) : PagerViewer(activity) {
    /**
     * Creates a new vertical pager.
     */
    override fun createPager(): Pager {
        return Pager(activity, isHorizontal = false)
    }

    private var autoScrollElapsed = 0L

    override val supportsAutoScroll: Boolean get() = true

    override fun onAutoScrollStarted() {
        autoScrollElapsed = 0L
    }

    override fun onAutoScrollFrame(elapsedMillis: Long, speedPercent: Int): Boolean {
        autoScrollElapsed += elapsedMillis
        if (autoScrollElapsed < AutoScroll.pageIntervalMillis(speedPercent)) {
            return true
        }
        autoScrollElapsed = 0L
        if (pager.currentItem >= adapterItemCount - 1) {
            return false
        }
        moveToNext()
        return true
    }
}
