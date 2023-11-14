package dti.g55.eventich_client.presentation.vues

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.AccueilPresentateur
import dti.g55.eventich_client.utilitaire.CustomViewHolder

class AccueilEvenementViewHolder(itemView: View, val presentateur: AccueilPresentateur): CustomViewHolder<Evenement>(itemView, presentateur){
    private val item: LinearLayout
    private val image: ImageView
    private val nom: TextView
    private val date: TextView
    private val location: TextView

    init {
        item = itemView.findViewById(R.id.evenement_liste_item)
        image = itemView.findViewById(R.id.featured_evenement_image)
        nom = itemView.findViewById(R.id.featured_evenement_nom)
        date = itemView.findViewById(R.id.featured_evenement_date)
        location = itemView.findViewById(R.id.featured_evenement_location)
    }

    override fun bindItem(evenement: Evenement) {
        image.setImageResource(evenement.imageId)
        nom.text = evenement.nom
        date.text = evenement.dateDebut.toString()
        location.text = evenement.location

        item.setOnClickListener { presentateur.traiterClickEvenement(evenement) }
    }
}