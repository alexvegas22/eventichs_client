package dti.g55.eventich_client.presentation.presentateur


import dti.g55.eventich_client.presentation.modeles.MeteoModele
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.MeteoVue

class MeteoPresentateur(val vue : MeteoVue): IPresentateur {
    private val modele = ModeleFactory.meteo

    override fun init() {
        val météo = modele.obtenirMétéo()
        vue.setupListeHeure(météo)
    }

    fun traiterClickBoutonRetour() {
        vue.retour()
    }
}