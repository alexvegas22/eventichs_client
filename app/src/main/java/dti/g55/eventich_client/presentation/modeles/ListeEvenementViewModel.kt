package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.EvenementListeItem
import java.text.SimpleDateFormat
import java.util.Date

class ListeEvenementViewModel {
    fun getListeEvenementsBetweenDates(dateDebut: Date, dateFin: Date): List<EvenementListeItem> {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        val evenements = arrayListOf(
            EvenementListeItem(R.drawable.ic_search, "Événement 1", dateFormat.parse("22-10-2023"), "123 rue Chemin, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Un autre événement", dateFormat.parse("22-10-2023"), "1928 rue Rue, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Festival de quelque chose", dateFormat.parse("23-10-2023"), "8765 rue Principale, Chambly, QC"),
            EvenementListeItem(R.drawable.ic_search, "Événement 4", dateFormat.parse("23-10-2023"), "1928 rue Rue, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Événement 5", dateFormat.parse("31-10-2023"), "123 rue Chemin, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Spectacle de musique", dateFormat.parse("26-10-2023"), "1928 rue Rue, Montréal, QC"),
            EvenementListeItem(R.drawable.ic_search, "Festival du hamburger", dateFormat.parse("28-10-2023"), "8765 rue Principale, Chambly, QC"),
            EvenementListeItem(R.drawable.ic_search, "Danse en ligne", dateFormat.parse("4-11-2023"), "123 rue Chemin, Montréal, QC"),
        )

        return evenements
        return evenements.filter { it.date in dateDebut..dateFin }.sortedBy { it.date }
    }
}