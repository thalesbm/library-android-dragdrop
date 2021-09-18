package bm.it.mobile.app

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import bm.it.mobile.library.DraggableView
import bm.it.mobile.library.OnViewSelection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xml()
        code()
    }

    private fun xml() {
        val destination1: LinearLayoutCompat = findViewById(R.id.destination1)
        val destination2: LinearLayoutCompat = findViewById(R.id.destination2)
        val destination3: LinearLayoutCompat = findViewById(R.id.destination3)
        val img1: AppCompatImageView = findViewById(R.id.img1)
        val img2: AppCompatImageView = findViewById(R.id.img2)
        val img3: AppCompatImageView = findViewById(R.id.img3)

        val destinations = mutableListOf<View>()
        destinations.add(destination1)
        destinations.add(destination2)
        destinations.add(destination3)

        val views = mutableListOf<View>()
        views.add(img1)
        views.add(img2)
        views.add(img3)

        val draggableView = DraggableView(callback)
        draggableView.setDestViewGroup(destinations)
        draggableView.addViews(views)
    }

    private fun code() {
        val itemsView: LinearLayoutCompat = findViewById(R.id.view_items)
        val destinationsViews: LinearLayoutCompat = findViewById(R.id.view_destination)

        val customParams = (LinearLayoutCompat.LayoutParams(200, 200))
        customParams.setMargins(10, 10, 10, 10)

        val views = mutableListOf<View>()
        for (i in 0..2) {
            val image = AppCompatImageView(this)
            image.setBackgroundColor(Color.BLACK)
            image.layoutParams = customParams

            itemsView.addView(image)
            views.add(image)
        }

        val destinations = mutableListOf<View>()
        for (i in 0..2) {
            val image = LinearLayoutCompat(this)
            image.setBackgroundColor(Color.YELLOW)
            image.layoutParams = customParams

            destinationsViews.addView(image)
            destinations.add(image)
        }

        val draggableView = DraggableView(callback)
        draggableView.setDestViewGroup(destinations)
        draggableView.addViews(views)
    }

    private val callback = object : OnViewSelection {
        override fun viewSelectedPosition(position: Int): Int {
            return position
        }
    }
}
