package dti.g55.eventich_client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class Meteo : Fragment() {
    lateinit var btnRetourImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_meteo, container, false)

        val parentLayout: LinearLayout = rootView.findViewById(R.id.llTempératures)

        var heure: Int = 0
        for (i in 0 .. 23) {
            // Crée un nouveau LinearLayout horizontal
            val horizontalLayout = LinearLayout(requireContext())
            horizontalLayout.orientation = LinearLayout.HORIZONTAL

            // Ajoute le nouveau LinearLayout horizontal à llTempératures
            parentLayout.addView(horizontalLayout)

            // Ajoute le TextView tvHeure au nouveau LinearLayout
            val tvHeure = TextView(requireContext())
            tvHeure.text = "${heure}h"
            tvHeure.textSize = 18f
            val paramsTvHeure = LinearLayout.LayoutParams(
                3,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            paramsTvHeure.weight = 1f
            tvHeure.layoutParams = paramsTvHeure
            horizontalLayout.addView(tvHeure)

            // Ajoute l'ImageView imgTemp au nouveau LinearLayout
            var imgTemp = ImageView(requireContext())
            val paramsImgTemp = LinearLayout.LayoutParams(
                35,
                70
            )
            paramsImgTemp.weight = 1f
            imgTemp.layoutParams = paramsImgTemp
            imgTemp.setImageResource(R.drawable.day_cloudy_icon)
            horizontalLayout.addView(imgTemp)

            //Ajoute le textView tvTypePrecision au LinearLayout
            val tvTypePrevision = TextView(requireContext())
            tvTypePrevision.text = "Partiellement nuageux"
            tvTypePrevision.textSize = 14f
            val paramsTvTypePrevision = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            paramsTvTypePrevision.weight = 1f
            tvTypePrevision.layoutParams = paramsTvTypePrevision
            horizontalLayout.addView(tvTypePrevision)

            //Ajoute le textView tvTempérature au LinearLayout
            val tvTempérature = TextView(requireContext())
            tvTempérature.text = "T: 17°C"
            tvTempérature.textSize = 14f
            val paramsTvTempérature = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            paramsTvTempérature.weight = 1f
            tvTempérature.layoutParams = paramsTvTempérature
            horizontalLayout.addView(tvTempérature)

            //Ajoute le textView tvHumidité au LinearLayout
            val tvHumidité = TextView(requireContext())
            tvHumidité.text = "H: 73%"
            tvHumidité.textSize = 14f
            val paramsTvHumidité = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            paramsTvHumidité.weight = 1f
            tvHumidité.layoutParams = paramsTvHumidité
            horizontalLayout.addView(tvHumidité)

            heure++
        }


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRetourImage = view.findViewById(R.id.imageView)

        btnRetourImage.setOnClickListener { retour() }
    }

    fun retour() {
        findNavController().navigate(R.id.action_meteo2_to_fragment_afficher_evenement)
    }
}