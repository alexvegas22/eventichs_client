package dti.g55.eventich_client.presentation.vues

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import dti.g55.eventich_client.presentation.presentateur.IPresentateur
import dti.g55.eventich_client.presentation.presentateur.MeteoPresentateur
import dti.g55.eventich_client.utilitaire.CustomViewHolder

class ListeMeteoViewHolder(itemView: View, val presentateur: MeteoPresentateur
): CustomViewHolder<HeureMeteo>(itemView, presentateur) {
    val heure: TextView
    val image: ImageView
    val description: TextView
    val température: TextView
    val humidité: TextView

    init {
        heure = itemView.findViewById(R.id.tvHeure)
        image = itemView.findViewById(R.id.imgTempérature)
        description = itemView.findViewById(R.id.tvDescription)
        température = itemView.findViewById(R.id.tvTempérature)
        humidité = itemView.findViewById(R.id.tvHumidité)

    }

    override fun bindItem(item: HeureMeteo) {
        heure.text = "${item.heure}h"
        image.setImageResource(item.image)
        description.text = item.description
        température.text = "T: ${item.température}°C"
        humidité.text = "H: ${item.humidité}%"
    }

}