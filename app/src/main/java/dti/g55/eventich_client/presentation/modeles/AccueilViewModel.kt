package dti.g55.eventich_client.presentation.modeles

import android.annotation.SuppressLint
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.EvenementListeItem
import java.text.SimpleDateFormat

class AccueilViewModel {
    @SuppressLint("SimpleDateFormat")
    fun listeEvenementsInscrits(/* code utilisateur*/ ): List<EvenementListeItem>{

        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        val evenements = arrayListOf(
            EvenementListeItem(R.drawable.ic_search, "Marriage Turc", dateFormat.parse("22-10-2023"), "123 rue Chemin, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Conférence sur les mathématiques ésotériques", dateFormat.parse("22-10-2023"), "1928 rue Rue, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Festival des grilles d'égouts", dateFormat.parse("23-10-2023"), "8765 rue Principale, Chambly, QC"),
            EvenementListeItem(R.drawable.ic_search, "Assemblée générale des lutins", dateFormat.parse("23-10-2023"), "1928 rue Rue, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Fin du monde", dateFormat.parse("31-10-2023"), "123 rue Chemin, Montréal, QC"),
            )
        return evenements
    }
}