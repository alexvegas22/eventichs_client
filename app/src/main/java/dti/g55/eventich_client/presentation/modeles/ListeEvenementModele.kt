package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import java.text.SimpleDateFormat
import java.util.Date

class ListeEvenementModele {
    lateinit var listeEvenements: ArrayList<Evenement>
    var dateDebut: Date = Date()
    var dateFin: Date = Date()
    var filtre: String = ""

    fun getListeEvenementsEntreDates(dateDebut: Date, dateFin: Date): ArrayList<Evenement> {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        val evenements = mutableListOf(
            Evenement(R.drawable.ic_search, "Événement 1", dateFormat.parse("22-7-2023"), "123 rue Chemin, Montréal, QC"),
            Evenement(R.drawable.ic_search, "Un autre événement", dateFormat.parse("22-9-2023"), "1928 rue Rue, Montréal, QC"),
            Evenement(R.drawable.ic_search, "Festival de quelque chose", dateFormat.parse("4-11-2023"), "8765 rue Principale, Chambly, QC"),
            Evenement(R.drawable.ic_search, "Événement 4", dateFormat.parse("23-11-2023"), "1928 rue Rue, Montréal, QC"),
            Evenement(R.drawable.ic_search, "Événement 5", dateFormat.parse("31-11-2023"), "123 rue Chemin, Montréal, QC"),
            Evenement(R.drawable.ic_search, "Spectacle de musique", dateFormat.parse("26-12-2023"), "1928 rue Rue, Montréal, QC"),
            Evenement(R.drawable.ic_search, "Festival du hamburger", dateFormat.parse("28-12-2023"), "8765 rue Principale, Chambly, QC"),
            Evenement(R.drawable.ic_search, "Danse en ligne", dateFormat.parse("4-1-2024"), "123 rue Chemin, Montréal, QC"),
        )

        println(evenements[0].date)

        return ArrayList(evenements.filter { it.date in dateDebut..dateFin }.sortedByDescending { it.date })
    }

    fun getListeFiltrer(): ArrayList<Evenement>{
        var lowercaseFiltre = filtre.lowercase().trim()

        return ArrayList(listeEvenements.filter {
            it.nom.lowercase().trim().contains(lowercaseFiltre) || it.location.lowercase().trim().contains(lowercaseFiltre)
        })
    }
}