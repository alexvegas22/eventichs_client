package dti.g55.eventich_client.presentation.modeles

import android.content.Context
import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesException
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesSQL
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.Organisation
import java.util.Date

class ListeEvenementModele() : IModeleListeEvenements {
    val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")

    override var listeEvenements = arrayListOf<Evenement>()
    override var listeEvenementsInscrits = arrayListOf<Evenement>()
    override var dateDebut = Date()
    override var dateFin = Date()
    override var filtre = ""
    var profilModele = ProfilModele()

    override suspend fun retournerListeÉvénements(): ArrayList<Evenement> {
        try {
            listeEvenements = source.obtenirListeEvenements()
        } catch (e : SourceDeDonneesException){
        }
        return listeEvenements
    }

    override suspend fun listeEvenementsInscrits(): ArrayList<Evenement>{
        try {
            listeEvenementsInscrits = source.obtenirListeEvenementsInscrits()
        } catch (e : SourceDeDonneesException){
        }
        return listeEvenementsInscrits
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

    override suspend fun getEvenementParOrganisation(organisation: Organisation): ArrayList<Evenement> {
        return source.obtenirEvenementsParOrganisation(organisation)
    }
    suspend fun sync(context: Context){
        val sourceLocale = SourceDeDonneesSQL(context)
        sourceLocale.synchroniserEvenementsVersDB(listeEvenements)
    }
}