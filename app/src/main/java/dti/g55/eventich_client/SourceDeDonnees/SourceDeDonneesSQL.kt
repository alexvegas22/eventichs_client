package dti.g55.eventich_client.SourceDeDonnees

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.Organisation
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.time.LocalDate

class SourceDeDonneesSQL(val context: Context):ISourceDonnee {
    private val dbHelper = DatabaseHelper(context)
    override suspend fun obtenirListeEvenements(): ArrayList<Evenement> {
        return dbHelper.obtenirEvenements()
    }

    override suspend fun obtenirListeEvenementsInscrits(): ArrayList<Evenement> {
        return dbHelper.obtenirEvenements()
    }

    override suspend fun obtenirNbParticipants(evenement: Evenement): Int {
        return 0
    }

    override suspend fun obtenirOrganisations(): ArrayList<Organisation> {
        val message = mutableListOf<Organisation>()
        return ArrayList(message)
    }

    override suspend fun obtenirConditionMeteorologique(): ConditionMeterologique {
        TODO("Not yet implemented")
    }

    override suspend fun obtenirOrganisationsPubliques(): ArrayList<Organisation> {
        val message = mutableListOf<Organisation>()
        return ArrayList(message)
    }

    override suspend fun obtenirEvenementsParOrganisation(organisation : Organisation): ArrayList<Evenement> {
        return dbHelper.obtenirEvenementsParOrganisation(organisation.nom)
    }

    override suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: String): Boolean {
        return true
    }
    fun seConnecter(profilUtilisateur: ProfilUtilisateur){
        dbHelper.ajouterUtilisateur(profilUtilisateur)
    }
    fun obtenirUtilisateur() : ProfilUtilisateur{
        return dbHelper.obtenirUtilisateur()
    }

    suspend fun synchroniserEvenementsVersDB(listeEvenement: ArrayList<Evenement>) {
        dbHelper.clearEvenements()
        for (evenement in listeEvenement) {
            dbHelper.ajouterEvenement(evenement)
        }
    }
}