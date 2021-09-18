package bm.it.mobile.library

import android.annotation.SuppressLint
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import java.util.*

class DraggableView(private val callback: OnViewSelection) : View.OnTouchListener,
    View.OnDragListener {

    private lateinit var destViewGroups: MutableList<View>

    private var viewsArrayList = ArrayList<View>()
    private var dragSuccess = false

    fun setDestView(view: View) {
        this.destViewGroups = mutableListOf()
        this.destViewGroups.add(view)
        setEvents()
    }

    fun setDestView(viewGroup: MutableList<View>) {
        this.destViewGroups = viewGroup
        setEvents()
    }

    fun setDraggableView(views: MutableList<View>) {
        views.forEach {
            setDraggableView(it)
        }
    }

    fun setDraggableView(view: View) {
        viewsArrayList.add(view)
        viewsArrayList.forEach {
            it.setOnTouchListener(this)
        }
    }

    fun reset() {
        dragSuccess = false

        destViewGroups.forEach {
            val container = it as LinearLayoutCompat
            container.removeAllViews()
        }
    }

    private fun setEvents() {
        destViewGroups.forEach {
            it.setOnDragListener(this)
        }
    }

    override fun onDrag(layoutView: View, dragEvent: DragEvent): Boolean {
        val view = dragEvent.localState as View
        when (dragEvent.action) {
            DragEvent.ACTION_DROP -> {
                actionDrop(view, layoutView)
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                actionDragEnded(view)
            }
        }
        return true
    }

    private fun actionDragEnded(view: View) {
        if (!dragSuccess) {
            view.visibility = View.VISIBLE
        }
    }

    private fun actionDrop(view: View, layoutView: View) {
        dragSuccess = false

        val container = layoutView as LinearLayoutCompat
        destViewGroups.forEach {
            if (container.id == it.id && (it as LinearLayoutCompat).childCount == 0) {
                val owner = view.parent as ViewGroup
                owner.removeView(view)

                container.addView(view)

                view.visibility = View.VISIBLE
                view.setOnTouchListener(null)
                owner.setOnDragListener(null)

                dragSuccess = true
            }
        }
    }

//        var i = 0
//        while (i < viewsArrayList.size) {
//            if (view.id == viewsArrayList[i].id) {
//                callback.viewSelectedPosition(i)
//            }
//            i++
//        }
//    }

    @SuppressLint("ClickableViewAccessibility")
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
