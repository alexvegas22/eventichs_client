package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.AccueilVue

class AccueilPresentateur(var vue: AccueilVue) {

    private var model = ModeleFactory.listeEvenements


    /**
     * Traite le démarrage de la vue Fragment Profil
     */
    fun traiterDemarrage() {
        afficherListeEvenements()
    }

    /**
     * Passe la liste des evenements à la vue
     */
    fun afficherListeEvenements() {
        vue.listeEvenements = model.listeEvenementsInscrits()
    }

}