package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.presentation.modeles.AccueilViewModel
import dti.g55.eventich_client.presentation.modeles.EvenementListeItem



class accueilFragment : Fragment() {

    private lateinit var listeEvenements: List<EvenementListeItem>
    private lateinit var recycler: RecyclerView
    private lateinit var model: AccueilViewModel
    private lateinit var context: Context

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
        recycler = view.findViewById(R.id.carouselRecyclerView)
        model = AccueilViewModel()
        listeEvenements = model.listeEvenementsInscrits()
        setInfoAdapter()
    }
    private fun setInfoAdapter(){
        var adapter = AccueilEvenementRecyclerAdapter(listeEvenements, context)
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter
    }
}