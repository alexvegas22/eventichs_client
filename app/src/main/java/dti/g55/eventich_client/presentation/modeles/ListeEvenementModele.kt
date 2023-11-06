package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.MockData
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

        val evenements = MockData.evenementsListe

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