package com.krenvpravo.coraller.sample

/**
 * @author Dmitry Borodin on 2017-11-20.
 */

object MainScreenContract {
    interface Presenter {
        fun onViewStarted(view: View)
        fun onViewStopped()
    }

    interface View
}