package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

class ListeEvenementModele(val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")) : IModeleListeEvenements {
    override var listeEvenements = arrayListOf<Evenement>()
    override var dateDebut = Date()
    override var dateFin = Date()
    override var filtre = ""
    var profilModele = ProfilModele()

    override suspend fun retournerListeÉvénements(): ArrayList<Evenement> {
        return source.obtenirListeEvenements()
    }

    override suspend fun listeEvenementsInscrits(): ArrayList<Evenement>{
        return source.obtenirListeEvenementsInscrits(profilModele.getProfil())
    }

    override suspend fun filtrerOrganisation() : ArrayList<Evenement> {
        val organisations = source.obtenirOrganisations(profilModele.getProfil())
        var evenements = source.obtenirListeEvenements()

        var resultatOrganisation = arrayListOf<Evenement>()

        for(organisation in organisations){
            resultatOrganisation += ArrayList(evenements.filter {
                it.organisation == organisation
            })
        }
        return resultatOrganisation
    }

    override suspend fun getListeEvenementsEntreDates(dateDebut: Date, dateFin: Date): ArrayList<Evenement> {
        val evenements = source.obtenirListeEvenements()
        return ArrayList(evenements.filter { it.dateDebut in dateDebut..dateFin }.sortedByDescending { it.dateDebut })
    }

    override fun getListeFiltrer(liste: ArrayList<Evenement>, filtreRecherche: String): ArrayList<Evenement>{
        var lowercaseFiltre = filtreRecherche.lowercase().trim()

        return ArrayList(liste.filter {
            it.nom.lowercase().trim().contains(lowercaseFiltre) || it.adresse.lowercase().trim().contains(lowercaseFiltre)
        })
    }

    override suspend fun getEvenementParOrganisation(organisation: String): ArrayList<Evenement> {
        return source.obtenirEvenementsParOrganisation(organisation)
    }
}