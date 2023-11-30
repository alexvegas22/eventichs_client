package dti.g55.eventich_client.presentation.vues

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
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
        image.setImageResource(R.drawable.ic_search)
        try {
            // Use Picasso to load the image from the URL
            Picasso.get().load(evenement.image).into(image)
        } catch (e : Exception) {
            Log.e("Image Error","Woopsie")
        }
        nom.text = evenement.nom
        date.text = evenement.dateDebut.toString()
        location.text = evenement.adresse

        item.setOnClickListener { presentateur.traiterClickEvenement(evenement) }
    }
}