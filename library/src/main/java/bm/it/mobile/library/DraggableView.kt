package bm.it.mobile.library

import android.content.Context
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.RelativeLayout
import java.util.*

class DraggableView(
    private val context: Context,
    private val destiViewGroup: ViewGroup
) : View.OnTouchListener, View.OnDragListener {

    private var viewsArrayList = ArrayList<View>()
    private var viewSelection: OnViewSelection? = null

    init {
        this.viewSelection = context as OnViewSelection
        destiViewGroup.setOnDragListener(this)
    }

    fun addView(view: View) {
        viewsArrayList.add(view)
        for (i in viewsArrayList.indices) {
            viewsArrayList[i].setOnTouchListener(this)
        }
    }

    override fun onDrag(layoutview: View, dragevent: DragEvent): Boolean {
        val action = dragevent.action
        val view = dragevent.localState as View
        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
            }
            DragEvent.ACTION_DRAG_EXITED -> {
            }
            DragEvent.ACTION_DROP -> {
                val owner = view.parent as ViewGroup
                owner.removeView(view)
                val container = layoutview as RelativeLayout
                container.addView(view)
                view.visibility = View.VISIBLE
                if (container.id == destiViewGroup!!.id) {
                    view.setOnTouchListener(null)
                    owner.setOnDragListener(null)
                }
                var i = 0
                while (i < viewsArrayList.size) {
                    if (view.id == viewsArrayList[i].id) {
                        viewSelection!!.viewSelectedPosition(i)
                    }
                    i++
                }
            }
            DragEvent.ACTION_DRAG_ENDED -> if (dropEventNotHandled(dragevent)) {
                view.visibility = View.VISIBLE
            }
            else -> {
            }
        }
        return true
    }

    private fun dropEventNotHandled(dragEvent: DragEvent): Boolean {
        return !dragEvent.result
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            val shadowBuilder = DragShadowBuilder(view)
            view.startDrag(null, shadowBuilder, view, 0)
            view.visibility = View.INVISIBLE
            true
        } else {
            false
        }
    }
}
