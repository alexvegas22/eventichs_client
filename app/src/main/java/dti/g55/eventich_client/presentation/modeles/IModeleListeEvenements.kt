package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

interface IModeleListeEvenements {
    var listeEvenements: ArrayList<Evenement>
    var dateDebut: Date
    var dateFin: Date
    var filtre: String
    suspend fun retournerListeÉvénements(): ArrayList<Evenement>;
    suspend fun listeEvenementsInscrits(): ArrayList<Evenement>;
    suspend fun filtrerOrganisation(): ArrayList<Evenement>;
    suspend fun getListeEvenementsEntreDates(dateDebut: Date, dateFin: Date): ArrayList<Evenement>;
    fun getListeFiltrer(liste: ArrayList<Evenement>, filtre: String): ArrayList<Evenement>;
}