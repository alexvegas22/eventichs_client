package dti.g55.eventich_client.presentation.presentateur


import dti.g55.eventich_client.presentation.modeles.MeteoModele
import dti.g55.eventich_client.presentation.vues.MeteoVue

class MeteoPresentateur(val vue : MeteoVue): IPresentateur {
    private val modele = MeteoModele()

    override fun init() {
        val listeHeuresMétéo = modele.getListeMétéoParHeure()
        vue.setupListeHeure(listeHeuresMétéo)
    }

    fun traiterClickBoutonRetour() {
        vue.retour()
    }
}