package dti.g55.eventich_client.presentation.modeles

import android.annotation.SuppressLint
import dti.g55.eventich_client.R
import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.EvenementListeItem
import java.text.SimpleDateFormat

class AccueilViewModel {
    @SuppressLint("SimpleDateFormat")
    fun listeEvenementsInscrits(/* code utilisateur*/ ): List<Evenement>{

        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        val evenements = MockData.evenementsInscrits
        return evenements
    }
}