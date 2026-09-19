package eu.kanade.tachiyomi.ui.reader.viewer

import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import eu.kanade.tachiyomi.ui.reader.model.ReaderPage
import eu.kanade.tachiyomi.ui.reader.model.ViewerChapters

/**
 * Interface for implementing a viewer.
 */
interface Viewer {

    /**
     * Returns the view this viewer uses.
     */
    fun getView(): View

    /**
     * Destroys this viewer. Called when leaving the reader or swapping viewers.
     */
    fun destroy() {}

    /**
     * Tells this viewer to set the given [chapters] as active.
     */
    fun setChapters(chapters: ViewerChapters)

    /**
     * Tells this viewer to move to the given [page].
     */
    fun moveToPage(page: ReaderPage)

    /**
     * Called from the containing activity when a key [event] is received. It should return true
     * if the event was handled, false otherwise.
     */
    fun handleKeyEvent(event: KeyEvent): Boolean

    /**
     * Called from the containing activity when a generic motion [event] is received. It should
     * return true if the event was handled, false otherwise.
     */
    fun handleGenericMotionEvent(event: MotionEvent): Boolean

    /**
     * Whether this viewer lays its content out top-to-bottom and supports auto-scrolling.
     */
    val supportsAutoScroll: Boolean get() = false

    /**
     * Called when auto-scroll starts, so the viewer can cancel any running scroll animation.
     */
    fun onAutoScrollStarted() {}

    /**
     * Advances auto-scroll by one frame, where [elapsedMillis] is the time since the previous
     * frame and [speedPercent] the user-configured speed (1..100).
     *
     * Returns false when the end of the content is reached and auto-scroll should stop.
     */
    fun onAutoScrollFrame(elapsedMillis: Long, speedPercent: Int): Boolean = false
}
