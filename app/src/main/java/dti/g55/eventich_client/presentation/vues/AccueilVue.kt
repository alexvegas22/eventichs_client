package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.AccueilPresentateur
import dti.g55.eventich_client.utilitaire.CustomRecyclerAdapter


class AccueilVue : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var context: Context
    private var presentateur =  AccueilPresentateur(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context = container!!.context

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.SubscribedEventsRecyclerView)

        presentateur.init()
    }

    fun setupRecyclerView(listeEvenements: ArrayList<Evenement>){
        var adapter = CustomRecyclerAdapter(listeEvenements, R.layout.home_featured_event, ::AccueilEvenementViewHolder, presentateur)
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter
    }

    fun allerVersEvenement() {
        findNavController().navigate(R.id.action_accueil_to_fragment_afficher_evenement)
    }
}