package dti.g55.eventich_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    lateinit var fragmentContainerView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainerView = findViewById(R.id.fragmentContainerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_accueil -> {
                fragmentContainerView.findNavController().navigate(R.id.action_aller_accueil)
                return true
            }
            R.id.item_recherche -> {
                fragmentContainerView.findNavController().navigate(R.id.action_aller_recherche)
                return true
            }
            R.id.item_profil -> {
                fragmentContainerView.findNavController().navigate(R.id.action_aller_profil)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}