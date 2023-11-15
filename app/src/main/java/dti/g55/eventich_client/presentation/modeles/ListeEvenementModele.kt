package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

class ListeEvenementModele(val source: ISourceDonnee = MockData) {
    lateinit var listeEvenements: ArrayList<Evenement>
    var dateDebut: Date = Date()
    var dateFin: Date = Date()
    var filtre: String = ""

    fun retournerListeÉvénements(): ArrayList<Evenement> {
        return source.obtenirListeEvenements()
    }

    fun listeEvenementsInscrits(): ArrayList<Evenement>{
        // À FAIRE
        return source.obtenirListeEvenements()
    }

    fun filtrerOrganisation() : ArrayList<Evenement> {
        val organisations = source.obtenirOrganisations()
        var evenements = source.obtenirListeEvenements()

        var resultatOrganisation = arrayListOf<Evenement>()

        for(organisation in organisations){
            resultatOrganisation += ArrayList(evenements.filter {
                it.organisation == organisation
            })
        }

        return resultatOrganisation

    }

    fun getListeEvenementsEntreDates(dateDebut: Date, dateFin: Date): ArrayList<Evenement> {
        val evenements = source.obtenirListeEvenements()
        return ArrayList(evenements.filter { it.dateDebut in dateDebut..dateFin }.sortedByDescending { it.dateDebut })
    }

    fun getListeFiltrer(liste: ArrayList<Evenement>, filtre: String): ArrayList<Evenement>{
        var lowercaseFiltre = filtre.lowercase().trim()

        return ArrayList(liste.filter {
            it.nom.lowercase().trim().contains(lowercaseFiltre) || it.location.lowercase().trim().contains(lowercaseFiltre)
        })
    }
}