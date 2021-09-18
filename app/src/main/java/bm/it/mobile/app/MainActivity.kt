package bm.it.mobile.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import bm.it.mobile.library.DraggableView
import bm.it.mobile.library.OnViewSelection

class MainActivity : AppCompatActivity(), OnViewSelection {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val draggableViewMain = DraggableView(this)
        draggableViewMain.setDestViewGroup(getDestinations())
        draggableViewMain.addViews(getViews())
    }

    private fun getDestinations(): MutableList<View> {
        val destination: LinearLayoutCompat = findViewById(R.id.destination)
        val destination1: LinearLayoutCompat = findViewById(R.id.destination1)
        val destination2: LinearLayoutCompat = findViewById(R.id.destination2)

        val destinations = mutableListOf<View>()
        destinations.add(destination)
        destinations.add(destination1)
        destinations.add(destination2)

        return destinations
    }

    private fun getViews(): MutableList<View> {
        val imgFacebook: AppCompatImageView = findViewById(R.id.imgFacebook)
        val imgGoogle: AppCompatImageView = findViewById(R.id.imgGoogle)
        val imgLinkedIn: AppCompatImageView = findViewById(R.id.imgLinkedIn)

        val views = mutableListOf<View>()
        views.add(imgFacebook)
        views.add(imgGoogle)
        views.add(imgLinkedIn)

        return views
    }

    override fun viewSelectedPosition(position: Int): Int {
        when (position) {
            0 -> {
//                Toast.makeText(this, "Login with Facebook", Toast.LENGTH_SHORT).show()
            }
            1 -> {
//                Toast.makeText(this, "Login with Google", Toast.LENGTH_SHORT).show()
            }
            2 -> {
//                Toast.makeText(this, "Login with LinkedIn", Toast.LENGTH_SHORT).show()
            }
        }
        return position
    }
}
