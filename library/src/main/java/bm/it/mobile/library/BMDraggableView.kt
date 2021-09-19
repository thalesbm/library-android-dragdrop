package bm.it.mobile.library

import android.annotation.SuppressLint
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat

open class BMDraggableView(private val callback: OnViewSelection) : View.OnTouchListener,
    View.OnDragListener {

    companion object {
        private val TAG = BMDraggableView::class.simpleName
    }

    protected lateinit var destViewGroups: MutableList<View>
    protected lateinit var viewsArrayList: MutableList<View>

    protected var hashMapByPosition: HashMap<Int, Int> = HashMap()
    protected var hashMapByTag: HashMap<String, String> = HashMap()

    protected fun setDestEvents() {
        destViewGroups.forEach {
            it.setOnDragListener(this)
        }
    }

    protected fun setItemsEvents() {
        viewsArrayList.forEach {
            it.setOnTouchListener(this)
        }
    }

    override fun onDrag(layoutView: View, dragEvent: DragEvent): Boolean {
        val view = dragEvent.localState as View
        when (dragEvent.action) {
            DragEvent.ACTION_DROP -> actionDrop(view, layoutView)
            DragEvent.ACTION_DRAG_ENDED -> actionDragEnded(view)
        }
        return true
    }

    private fun actionDragEnded(view: View) {
        view.visibility = View.VISIBLE
    }

    private fun actionDrop(view: View, layoutView: View) {
        val container = layoutView as LinearLayoutCompat
        for (i in destViewGroups.indices) {
            val it = destViewGroups[i]
            if (container.id == it.id && (it as LinearLayoutCompat).childCount == 0) {
                val owner = view.parent as ViewGroup
                owner.removeView(view)

                container.addView(view)

                view.visibility = View.VISIBLE
                view.setOnTouchListener(null)
                owner.setOnDragListener(null)

                updateCallback(destViewGroups[i], i, view)
            }
        }
        logCallback()
        invokeCallback()
    }

    private fun updateCallback(destinationView: View, destinationPosition: Int, view: View) {
        for (j in viewsArrayList.indices) {
            if (viewsArrayList[j].id == view.id) {

                hashMapByPosition[destinationPosition] = j
                hashMapByTag[destinationView.tag as String] = viewsArrayList[j].tag as String
            }
        }
    }

    private fun logCallback() {
        hashMapByPosition.forEach {
            Log.d(TAG, "(POSITION) DESTINATION:" + it.key + " ITEM:" + it.value)
        }

        hashMapByTag.forEach {
            Log.d(TAG, "(TAG) DESTINATION:" + it.key + " ITEM:" + it.value)
        }
    }

    private fun invokeCallback() {
        if (hashMapByPosition.size == viewsArrayList.size &&
            hashMapByPosition.size == destViewGroups.size) {
            callback.viewSelectedByPosition(hashMapByPosition)
        }

        if (hashMapByTag.size == viewsArrayList.size &&
            hashMapByTag.size == destViewGroups.size) {
            callback.viewSelectedByTag(hashMapByTag)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            val shadowBuilder = View.DragShadowBuilder(view)
            view.startDrag(null, shadowBuilder, view, 0)
            view.visibility = View.INVISIBLE
            true
        } else {
            false
        }
    }
}
