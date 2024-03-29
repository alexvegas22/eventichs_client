package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.AccueilPresentateur
import dti.g55.eventich_client.utilitaire.CustomRecyclerAdapter

class AccueilVue : Fragment() {
    private lateinit var recyclerEvenementsProchains: RecyclerView
    private lateinit var recyclerEvenementsOrganisations: RecyclerView
    private lateinit var nomUtilisateur : TextView
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

        recyclerEvenementsProchains = view.findViewById(R.id.SubscribedEventsRecyclerView)
        recyclerEvenementsOrganisations = view.findViewById(R.id.OrganisationEventsRecyclerView)

        presentateur.init()
    }

    fun setupRecyclerView(listeEvenements: ArrayList<Evenement>, listeEvenementsOrganisations: ArrayList<Evenement>) {
        var adapter = CustomRecyclerAdapter(
            listeEvenements,
            R.layout.home_featured_event,
            ::AccueilEvenementViewHolder,
            presentateur
        )
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerEvenementsProchains.layoutManager = layoutManager
        recyclerEvenementsProchains.itemAnimator = DefaultItemAnimator()
        recyclerEvenementsProchains.adapter = adapter

        if (listeEvenementsOrganisations != null) {
            var adapterOrganisation = CustomRecyclerAdapter(
                listeEvenementsOrganisations,
                R.layout.home_featured_event,
                ::AccueilEvenementViewHolder,
                presentateur
            )
            var layoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager2.reverseLayout = true
            layoutManager2.stackFromEnd = true
            recyclerEvenementsOrganisations.layoutManager = layoutManager2
            recyclerEvenementsOrganisations.itemAnimator = DefaultItemAnimator()
            recyclerEvenementsOrganisations.adapter = adapterOrganisation
        }
    }

    fun allerVersEvenement() {
        findNavController().navigate(R.id.action_accueil_to_fragment_afficher_evenement)
    }
}