package bm.it.mobile.library

import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat

class DraggableView(private val callback: OnViewSelection) : BMDraggableView(callback) {

    fun setDestView(view: View) {
        this.destViewGroups = mutableListOf()
        this.destViewGroups.add(view)
        setDestEvents()
    }

    fun setDestView(viewGroup: MutableList<View>) {
        this.destViewGroups = viewGroup
        setDestEvents()
    }

    fun setDraggableView(views: MutableList<View>) {
        this.viewsArrayList = views
        setItemsEvents()
    }

    fun setDraggableView(view: View) {
        this.viewsArrayList = mutableListOf()
        this.viewsArrayList.add(view)
        setItemsEvents()
    }

    fun reset() {
        hashMapByPosition = HashMap()
        destViewGroups.forEach {
            val container = it as LinearLayoutCompat
            container.removeAllViews()
        }
    }
}
