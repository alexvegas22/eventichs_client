package dti.g55.eventich_client.SourceDeDonnees

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.time.LocalDate

class SourceDeDonneesSQL(context: Context):ISourceDonnee {
    private val dbHelper = DatabaseHelper(context)
    override suspend fun obtenirListeEvenements(): ArrayList<Evenement> {
        return dbHelper.obtenirEvenements()
    }

    override suspend fun obtenirListeEvenementsInscrits(profil: ProfilUtilisateur): ArrayList<Evenement> {
        return dbHelper.obtenirEvenements()
    }

    override suspend fun obtenirNbParticipants(evenement: Evenement): Int {
        return 0
    }

    override suspend fun obtenirOrganisations(profil: ProfilUtilisateur): ArrayList<String> {
        val message = mutableListOf(
            "Vous êtes hors ligne, connectez-vous afin de consulter vos organisations"
        )
        return ArrayList(message)
    }

    override suspend fun obtenirConditionMeteorologique(): ConditionMeterologique {
        TODO("Not yet implemented")
    }

    override suspend fun obtenirOrganisationsPubliques(): ArrayList<String> {
        val message = mutableListOf(
            "Vous êtes hors ligne, connectez-vous afin de consulter vos organisations"
        )
        return ArrayList(message)
    }

    override suspend fun obtenirEvenementsParOrganisation(organisation : String): ArrayList<Evenement> {
        return dbHelper.obtenirEvenementsParOrganisation(organisation)
    }

    override suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: Int): Boolean {
        return true
    }

    suspend fun synchroniserEvenementsVersDB(listeEvenement: ArrayList<Evenement>) {
        dbHelper.clearEvenements()
        for (evenement in listeEvenement) {
            dbHelper.ajouterEvenement(evenement)
        }
    }
}