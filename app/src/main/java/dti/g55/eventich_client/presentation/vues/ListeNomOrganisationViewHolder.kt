package dti.g55.eventich_client.presentation.vues

import android.view.View
import android.widget.TextView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.presentation.presentateur.AccueilPresentateur
import dti.g55.eventich_client.utilitaire.CustomViewHolder

class ListeNomOrganisationViewHolder(itemView : View, val presentateur : AccueilPresentateur) : CustomViewHolder<String>(itemView, presentateur) {
    val nomOrganisation : TextView

    init {
        nomOrganisation = itemView.findViewById(R.id.txtOrganisationName)
    }
    override fun bindItem(nom: String){
        nomOrganisation.text = nom
    }
}