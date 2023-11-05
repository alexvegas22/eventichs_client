package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.adapteur.AccueilEvenementRecyclerAdapter
import dti.g55.eventich_client.presentation.modeles.modele

class RecyclerPresentateur(accueilViewHolder: AccueilEvenementRecyclerAdapter.AccueilViewHolder)  {

    private var model : modele = modele()

    /**
     * Passe les données du recyclerView au modèle
     */
    fun passerEvenementChoisiAuModele(evenementChoisi : Evenement) {
        model.setEvenementSelectionnne(evenementChoisi)

    }
}