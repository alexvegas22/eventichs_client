package dti.g55.eventich_client

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import dti.g55.eventich_client.presentation.presentateur.MainPresentateur
import dti.g55.eventich_client.presentation.presentateur.MainPresenterImpl


class MainActivity : AppCompatActivity() {
    lateinit var fragmentContainerView: FragmentContainerView
    var prentateur : MainPresentateur = MainPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        fragmentContainerView = findViewById(R.id.fragmentContainerView)
        prentateur.attachView(this)
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
    fun updateTheme(theme: Int) {
        setTheme(theme)
    }

    override fun onDestroy() {
        prentateur.detachView()
        super.onDestroy()
    }

}