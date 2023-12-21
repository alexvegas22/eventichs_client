package dti.g55.eventich_client.SourceDeDonnees

import android.util.JsonWriter
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class SourceDeDonneesHTTP(val url_api: String): ISourceDonnee {
    @Throws(SourceDeDonneesException::class)
    override suspend fun obtenirListeEvenements(): ArrayList<Evenement> {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().url("$url_api/evenements/public").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }
            return DecodeurJson.décoderJsonVersListeEvenements(body)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }
    @Throws(SourceDeDonneesException::class)
    override suspend fun obtenirListeEvenementsInscrits(utilisateur: ProfilUtilisateur): ArrayList<Evenement> {
        try {
            val client = OkHttpClient()

            val id = utilisateur.id
            val requête = Request.Builder().url("$url_api/utilisateurs/$id/evenements").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }

            return DecodeurJson.décoderJsonVersListeEvenements(body)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }

    @Throws(SourceDeDonneesException::class)
    override suspend fun obtenirNbParticipants(evenement: Evenement): Int {
        try {
            val client = OkHttpClient()

            val id = evenement.id
            val requête = Request.Builder().url("$url_api/evenements/$id/participants").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }

            return DecodeurJson.décoderJsonVersNbParticipants(body)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }

    @Throws(SourceDeDonneesException::class)
    override suspend fun obtenirOrganisations(utilisateur: ProfilUtilisateur): ArrayList<String> {
        try {
            val client = OkHttpClient()

            val id = utilisateur.id
            //val requête = Request.Builder().url("$url_api/utilisateurs/$id/organisations").build()
            val requête = Request.Builder().url("$url_api/organisations").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }

            return DecodeurJson.décoderJSONVersListeOrganisations(body)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }

    @Throws(SourceDeDonneesException::class)
    override suspend fun obtenirConditionMeteorologique(): ConditionMeterologique {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().url("$url_api/forecast?latitude=45.5088&longitude=-73.5878&current=temperature_2m,relative_humidity_2m,weather_code&daily=weather_code&forecast_days=1").build()
            val requêteHeures = Request.Builder().url("$url_api/forecast?latitude=45.5088&longitude=-73.5878&hourly=temperature_2m,relative_humidity_2m,weather_code&forecast_days=1").build()


            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            val réponseHeures = client.newCall(requêteHeures).execute()

            val bodyHeures = réponseHeures.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }
            if (bodyHeures == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }

            return DecodeurJsonMeteo.décoderJsonVersConditionMeteo(body,bodyHeures)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }

    override suspend fun obtenirOrganisationsPubliques(): ArrayList<String> {
        try {
            val client = OkHttpClient()

            val requête = Request.Builder().url("$url_api/organisations/publiques").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }

            return DecodeurJson.décoderJSONVersListeOrganisations(body)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }

    override suspend fun obtenirEvenementsParOrganisation(organisation: String): ArrayList<Evenement> {
        try{
            val client = OkHttpClient()

            val requête = Request.Builder().url("$url_api/organisations/$organisation/evenements").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }

            return DecodeurJson.décoderJsonVersListeEvenements(body)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }

    override suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: Int): Boolean {
        try{
            val client = OkHttpClient()

            val output = ByteArrayOutputStream()
            val writer = JsonWriter( OutputStreamWriter( output ) )

            writer.beginObject()
            writer.name("idUtilisateur").value(idUtilisateur)
            writer.name("idEvenement").value(idEvenement)
            writer.endObject()
            writer.close()

            val body = RequestBody.create(
                "application/json".toMediaTypeOrNull(), output.toString()
            )

            val requête = Request.Builder()
                .url( "$url_api/rejoindre" )
                .post( body )
                .build()

            val réponse = client.newCall( requête ).execute();

            if( réponse.code != 200 ){
                return false
            }
            if( réponse.body == null ){
                throw SourceDeDonneesException( "Pas de données reçues" )
                return false
            }

            return true
        }
        catch(e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
            return false
        }

    }

    suspend fun rejoindreEvenement(){

    }

}