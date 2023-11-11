package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.ProfilVue

class ProfilPresentateur(var vue: ProfilVue) {

    private var model = ModeleFactory.profil


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
