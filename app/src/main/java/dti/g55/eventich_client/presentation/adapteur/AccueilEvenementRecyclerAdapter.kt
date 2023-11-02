package dti.g55.eventich_client.presentation.adapteur

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.EvenementListeItem

class AccueilEvenementRecyclerAdapter(var listeEvenements: List<Evenement>, var context: Context): RecyclerView.Adapter<AccueilEvenementRecyclerAdapter.AccueilViewHolder>()  {

    class AccueilViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item: LinearLayout
        val image: ImageView
        val nom: TextView
        val date: TextView
        val location: TextView


        init {
            item = itemView.findViewById(R.id.evenement_liste_item)
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

        holder.item.setOnClickListener { goToEvent(holder.item) }
    }

    override fun getItemCount(): Int {
        return listeEvenements.size
    }

    fun goToEvent(item: LinearLayout) {
        item.findNavController().navigate(R.id.action_accueil_to_fragment_afficher_evenement)
    }
}