package bm.it.mobile.library

import android.annotation.SuppressLint
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import java.util.*

class DraggableView(private val callback: OnViewSelection) : View.OnTouchListener, View.OnDragListener {

    private val viewsArrayList: ArrayList<View> = ArrayList<View>()

    private lateinit var destViewGroups: MutableList<View>

    fun setDestViewGroup(viewGroup: View) {
        this.destViewGroups = mutableListOf()
        this.destViewGroups.add(viewGroup)
        setEvents()
    }

    fun setDestViewGroup(viewGroup: MutableList<View>) {
        this.destViewGroups = viewGroup
        setEvents()
    }

    private fun setEvents() {
        destViewGroups.forEach {
            it.setOnDragListener(this)
        }
    }

    fun addViews(views: List<View>) {
        views.forEach {
            addView(it)
        }
    }

    private fun addView(view: View) {
        viewsArrayList.add(view)
        for (i in viewsArrayList.indices) {
            viewsArrayList[i].setOnTouchListener(this)
        }
    }

    override fun onDrag(layoutView: View, dragEvent: DragEvent): Boolean {
        val action = dragEvent.action
        val view = dragEvent.localState as View
        when (action) {
            DragEvent.ACTION_DROP -> {
                actionDrop(view, layoutView)
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                actionDragEnded(view, dragEvent)
            }
        }
        return true
    }

    private fun actionDragEnded(view: View, dragEvent: DragEvent) {
        if (dropEventNotHandled(dragEvent)) {
            view.visibility = View.VISIBLE
        }
    }

    private fun actionDrop(view: View, layoutView: View) {
        val owner = view.parent as ViewGroup
        owner.removeView(view)

        val container = getContainerType(layoutView)

        (container as ViewGroup).addView(view)
        view.visibility = View.VISIBLE

        destViewGroups.forEach {
            if (container.id == it.id) {
                view.setOnTouchListener(null)
                owner.setOnDragListener(null)
            }
        }

        var i = 0
        while (i < viewsArrayList.size) {
            if (view.id == viewsArrayList[i].id) {
                callback.viewSelectedPosition(i)
            }
            i++
        }
    }

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

    private fun getContainerType(layoutView: View): Any {
        return when (layoutView) {
            is RelativeLayout -> {
                layoutView
            }
            is AppCompatTextView -> {
                layoutView
            }
            else -> {
                layoutView
            }
        }
    }

    private fun dropEventNotHandled(dragEvent: DragEvent): Boolean {
        return !dragEvent.result
    }
}
