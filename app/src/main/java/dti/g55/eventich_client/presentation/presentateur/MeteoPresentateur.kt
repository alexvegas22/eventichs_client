package dti.g55.eventich_client.presentation.presentateur


import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.MeteoVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeteoPresentateur(val vue : MeteoVue): IPresentateur {
    private val modele = ModeleFactory.meteo

    override fun init() {
        setupListeHeure()
        obtenir_Meteo()

    }

    fun traiterClickBoutonRetour() {
        vue.retour()
    }

    private fun setupListeHeure() {
        val météo = ConditionMeterologique( "---", 0.0, 0, arrayListOf<HeureMeteo>() )
        vue.setupListeHeure(météo)
    }

    private fun obtenir_Meteo() {
        CoroutineScope(Dispatchers.IO).launch {
            val météo = modele.obtenirMétéo()

            CoroutineScope(Dispatchers.Main).launch {
                vue.rafraichirListeHeure(météo)
            }
        }
    }

}