package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import dti.g55.eventich_client.presentation.modeles.Modele
import dti.g55.eventich_client.presentation.vues.accueilFragment
import dti.g55.eventich_client.presentation.vues.fragment_profil

class AccueilPresentateur(var vue: accueilFragment) {

    private var model = Modele()


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