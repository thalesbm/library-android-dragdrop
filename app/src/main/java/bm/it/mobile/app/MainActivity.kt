package bm.it.mobile.app

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        val rlDestination: RelativeLayout = findViewById(R.id.rlDestination)
        val rlDestination1: RelativeLayout = findViewById(R.id.rlDestination1)
        val rlDestination2: RelativeLayout = findViewById(R.id.rlDestination2)

        val destinations = mutableListOf<View>()
        destinations.add(rlDestination)
        destinations.add(rlDestination1)
        destinations.add(rlDestination2)

        return destinations
    }

    private fun getViews(): MutableList<View> {
        val imgFacebook: ImageView = findViewById(R.id.imgFacebook)
        val imgGoogle: ImageView = findViewById(R.id.imgGoogle)
        val imgLinkedIn: ImageView = findViewById(R.id.imgLinkedIn)

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
