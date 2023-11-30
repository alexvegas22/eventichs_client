package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

class ListeEvenementModele(val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")) {
    var listeEvenements: ArrayList<Evenement> = arrayListOf()
    var dateDebut: Date = Date()
    var dateFin: Date = Date()
    var filtre: String = ""
    var profilModele = ProfilModele()

    suspend fun retournerListeÉvénements(): ArrayList<Evenement> {
        return source.obtenirListeEvenements()
    }

    suspend fun listeEvenementsInscrits(): ArrayList<Evenement>{
        // À FAIRE
        return source.obtenirListeEvenementsInscrits(profilModele.getProfil())
    }

    suspend fun filtrerOrganisation() : ArrayList<Evenement> {
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

    suspend fun getListeEvenementsEntreDates(dateDebut: Date, dateFin: Date): ArrayList<Evenement> {
        val evenements = source.obtenirListeEvenements()
        return ArrayList(evenements.filter { it.dateDebut in dateDebut..dateFin }.sortedByDescending { it.dateDebut })
    }

    fun getListeFiltrer(liste: ArrayList<Evenement>, filtre: String): ArrayList<Evenement>{
        var lowercaseFiltre = filtre.lowercase().trim()

        return ArrayList(liste.filter {
            it.nom.lowercase().trim().contains(lowercaseFiltre) || it.adresse.lowercase().trim().contains(lowercaseFiltre)
        })
    }
}