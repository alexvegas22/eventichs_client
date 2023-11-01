package dti.g55.eventich_client.presentation.vues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateurs.PrésentateurAfficherÉvénement

class AfficherEvenementFragment : Fragment() {

    var présentateur = PrésentateurAfficherÉvénement(this)

    lateinit var meteoLayout: LinearLayout
    lateinit var backButtonImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_afficher_evenement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meteoLayout = view.findViewById(R.id.lytConteneurInfosMeteo)
        backButtonImage = view.findViewById(R.id.imgBoutonArrière)

        meteoLayout.setOnClickListener { voirMeteo() }
        backButtonImage.setOnClickListener { retour() }

        présentateur.initialiser_fragment()
    }

    fun voirMeteo(){
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_meteo2)
    }

    fun retour() {
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_liste_evenements)
    }

    fun afficher_données(événement: Evenement) {
        //à faire
    }
}