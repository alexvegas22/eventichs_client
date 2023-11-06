package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import dti.g55.eventich_client.presentation.modeles.modele
import dti.g55.eventich_client.presentation.vues.fragment_profil

class ProfilPresentateur(var vue: fragment_profil) {

    private var model : modele = modele()


    /**
     * Traite le démarrage de la vue Fragment Profil
     */
    fun traiterDemarrage() {
        afficherUtilisateur()
    }

    /**
     * Passe les données du profil de l'utilisateur connecté à la vue
     */
    fun afficherUtilisateur() {
        var profilConnecté : ProfilUtilisateur = model.getProfil()
        vue.updateProfileComponents(profilConnecté)
    }
}