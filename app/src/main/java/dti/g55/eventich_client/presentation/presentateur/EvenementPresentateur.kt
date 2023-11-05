package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.modele
import dti.g55.eventich_client.presentation.vues.fragment_afficher_evenement

class EvenementPresentateur(var vue : fragment_afficher_evenement, var model : modele) {

    /**
     * Traite le démarrage de la vue Fragment Profil
     */
    fun traiterDemarrage() {
        afficherEvenement()
    }

    /**
     * Passe l'évènement sélectionné à la vue
     */
    fun afficherEvenement() {
        vue.setEvenementInfo(model.getEvenementSelectionne())
    }
}