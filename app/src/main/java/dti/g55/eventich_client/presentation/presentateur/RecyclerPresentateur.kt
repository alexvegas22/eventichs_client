package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.Modele
import dti.g55.eventich_client.presentation.vues.AccueilEvenementViewHolder

class RecyclerPresentateur(accueilViewHolder: AccueilEvenementViewHolder)  {

    private var model = Modele()

    /**
     * Passe les données du recyclerView au modèle
     */
    fun passerEvenementChoisiAuModele(evenementChoisi : Evenement) {
        model.setEvenementSelectionnne(evenementChoisi)

    }
}