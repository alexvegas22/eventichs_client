package dti.g55.eventich_client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_afficher_evenement.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_afficher_evenement : Fragment() {
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
    }

    fun voirMeteo(){
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_meteo2)
    }

    fun retour() {
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_liste_evenements)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_afficher_evenement.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_afficher_evenement().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}