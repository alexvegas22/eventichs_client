package dti.g55.eventich_client.presentation.vues

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.utilitaire.CustomViewHolder

class ListeEvenementItemViewHolder(itemView: View): CustomViewHolder<Evenement>(itemView){
    val item: LinearLayout
    val image: ImageView
    val nom: TextView
    val date: TextView
    val location: TextView

    init {
        item = itemView.findViewById(R.id.evenement_liste_item)
        image = itemView.findViewById(R.id.evenement_liste_item_image)
        nom = itemView.findViewById(R.id.evenement_liste_item_nom)
        date = itemView.findViewById(R.id.evenement_liste_item_date)
        location = itemView.findViewById(R.id.evenement_liste_item_location)
    }

    override fun bindItem(evenement: Evenement) {
        image.setImageResource(evenement.imageId)
        nom.text = evenement.nom
        date.text = evenement.date.toString()
        location.text = evenement.location

        item.setOnClickListener { goToEvent(item) }
    }
    private fun goToEvent(item: LinearLayout) {
        item.findNavController().navigate(R.id.action_liste_evenements_to_fragment_afficher_evenement)
    }
}