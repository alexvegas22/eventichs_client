package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.presentation.modeles.EvenementListeItem

class AccueilEvenementRecyclerAdapter(var listeEvenements: List<EvenementListeItem>, var context: Context): RecyclerView.Adapter<AccueilEvenementRecyclerAdapter.AccueilViewHolder>()  {

        class AccueilViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val image: ImageView
            val nom: TextView
            val date: TextView
            val location: TextView

            init {
                image = itemView.findViewById(R.id.featured_evenement_image)
                nom = itemView.findViewById(R.id.featured_evenement_nom)
                date = itemView.findViewById(R.id.featured_evenement_date)
                location = itemView.findViewById(R.id.featured_evenement_location)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccueilViewHolder {
            val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.home_featured_event, parent, false)
            return AccueilViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: AccueilViewHolder, position: Int) {
            val image = listeEvenements[position].imageId
            val nom = listeEvenements[position].nom
            val date = listeEvenements[position].date
            val location = listeEvenements[position].location

            holder.image.setImageResource(image)
            holder.nom.text = nom
            holder.date.text = date.toString()
            holder.location.text = location
        }

        override fun getItemCount(): Int {
            return listeEvenements.size
        }

}