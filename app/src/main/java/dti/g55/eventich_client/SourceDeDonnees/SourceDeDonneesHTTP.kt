package dti.g55.eventich_client.SourceDeDonnees

import android.os.Build
import android.util.JsonWriter
import android.util.Log
import androidx.annotation.RequiresApi
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.Organisation
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import requestAccessTokenWithPassword

class SourceDeDonneesHTTP(val url_api: String): ISourceDonnee {
    //Sam
    //val accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjlBNDRET0VwQTNYMUw2bUhESG8tWiJ9.eyJpc3MiOiJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZkM2NiNmExOTU5OWM5MjA5YTYwOTkiLCJhdWQiOlsiaHR0cDovL2V2ZW50aWNocy5hcGkiLCJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE3MDMyMzc0OTksImV4cCI6MTcwMzMyMzg5OSwiYXpwIjoibFdpa3dMRzJVa01Yb0N2TG9hNHo3QUdBUldPQ1BaaXYiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIGFkZHJlc3MgcGhvbmUiLCJndHkiOiJwYXNzd29yZCJ9.dGFAEl-34_D3HyYBdEHJi-HsvXtNNezaavg4h917P9l-9f7IolMyB_GVncD4aA4-O_u1pC-FuTXHsQwpAC3-t3ZY-OhvoBAMgtxWvI-Id2QY0Lwqy51N2g7iImvt9R1qCINVNHTZYLrianIYQ5y-g_U5NBSaj1Tlkq-sjtc-lyiEhAxSxYhEQkFjse0QrwR4aTk6rnEkb_1yMYncWvBRStbzNw3mTQ5lb2ocp_ubdMru48hmyNKTB4935WfwTAiLbNmbugO6UkCWz2dEn0AP91XzfIjwv58Z7skVVFWNwdJDFE8OGdR1y8XaLZYjY8BhLx7v-_v4ABuwniFsWNZXAg"
    //Kieran
    var accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjlBNDRET0VwQTNYMUw2bUhESG8tWiJ9.eyJpc3MiOiJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZkM2Y4ZTQxNzhhZWZjMDM0Mjk2MDYiLCJhdWQiOlsiaHR0cDovL2V2ZW50aWNocy5hcGkiLCJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE3MDMyNDQ1NDcsImV4cCI6MTcwMzMzMDk0NywiYXpwIjoibFdpa3dMRzJVa01Yb0N2TG9hNHo3QUdBUldPQ1BaaXYiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIGFkZHJlc3MgcGhvbmUiLCJndHkiOiJwYXNzd29yZCJ9.Ia7BCDc8w3EgW1UtpTbcLlguMJGEgEqlUbgfHtEk2aRVBypLAMH49meOE3zML5YUn_6vs4jB-Deu7mRqKU3TSkuaP-SNNutVeXIya6PCyBRMjEtLzyBb5mAgDxb1bQqbUCiwm--Vy6O1EBdShp0BhPslER-1xv4BxYYtB0VPt9XB3DHQD7mjtqIP-GhL62zxiJ7sP704s2BmN55gnQyR1RfxKfOLhd6lk8UM8kEeq0fQAUM50Tvqk3HEo18rgFgY3W4ZLUGpV8y0H-vkLPldKKRF_q8sl45Bo033mr_F6fvl5Zr1rEQJB6l1l9qJyj1f2SktJWlSnsWe24un6CMvkA"
        @RequiresApi(Build.VERSION_CODES.O)
    //val accessToken = requestAccessTokenWithPassword()
    //Joe
    //val accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjlBNDRET0VwQTNYMUw2bUhESG8tWiJ9.eyJpc3MiOiJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZkMmRiZWExOTU5OWM5MjA5YTRmMDEiLCJhdWQiOlsiaHR0cDovL2V2ZW50aWNocy5hcGkiLCJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE3MDMxOTk5MzEsImV4cCI6MTcwMzI4NjMzMSwiYXpwIjoibFdpa3dMRzJVa01Yb0N2TG9hNHo3QUdBUldPQ1BaaXYiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIGFkZHJlc3MgcGhvbmUiLCJndHkiOiJwYXNzd29yZCJ9.isCqhobaR7Ao1miGDRLR6We9Vkkw9pJZ2HLEXbULafD1E41PR-vKgffC2kIFwodjPsZUmUh2Y4f55DRYvRTkF9Ou7iT4HeAL8AAEktCzw6pHJSw1zj9hLrgearqERBz1CBx-GHKsM2JWuuiJLMLssOC-3OpdlbzchCe85DxquHPhk8egHDipdBaxY66EwCCghlHwejQNw2DXdcbzfS00evPgzBknaBagHR3LVXW-zt3NLJi9o_7pprEK-5V8UW6HdpRGgxRlMEZ9UkWska2fhs11kZlFN99-AxNekW2LAmdcOQm-__YYIbecILpsGa19Zq-mMsOoukk8ajDj6QCsiQ"

    @Throws(SourceDeDonneesException::class)
    override suspend fun obtenirListeEvenements(): ArrayList<Evenement> {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().header("Authorization", "Bearer $accessToken").url("$url_api/evenements").build()

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
    override suspend fun obtenirListeEvenementsInscrits(): ArrayList<Evenement> {
        try {
            val client = OkHttpClient()

            val requête = Request.Builder().header("Authorization", "Bearer $accessToken").url("$url_api/utilisateurs/evenements").build()

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
            val requête = Request.Builder().header("Authorization", "Bearer $accessToken").url("$url_api/evenements/$id/participants").build()

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
    override suspend fun obtenirOrganisations(): ArrayList<Organisation> {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().header("Authorization", "Bearer $accessToken").url("$url_api/utilisateurs/organisations").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }
            var listeOrg = arrayListOf<Organisation>()
            for(org in DecodeurJson.décoderJSONVersListeOrganisations(body)){
                Log.e("Organisation for loop", org.nom)
                listeOrg += obtenirOrganisationById(org.id)
            }
            Log.e("Liste Organisations",listeOrg.map{it.nom}.toString())
            return listeOrg
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }
    @Throws(SourceDeDonneesException::class)
    fun obtenirOrganisationById(id : Int): Organisation {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().header("Authorization", "Bearer $accessToken").url("$url_api/organisations/$id").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }
            if (body == null){
                throw SourceDeDonneesException("Aucune donnée reçues")
            }

            return DecodeurJson.décoderJSONVersOrganisations(body)


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

    override suspend fun obtenirOrganisationsPubliques(): ArrayList<Organisation> {
        try {
            val client = OkHttpClient()

            val requête = Request.Builder()
                .url("$url_api/organisations")
                .header("Authorization", "Bearer $accessToken")
                .build()

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

    override suspend fun obtenirEvenementsParOrganisation(organisation: Organisation): ArrayList<Evenement> {
        try{
            Log.e("Organisation id", organisation.id.toString())
            val client = OkHttpClient()

            val requête = Request.Builder().header("Authorization", "Bearer $accessToken").url("$url_api/organisations/${organisation.id}/evenements").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body?.string()

            if (réponse.code != 200) {

                throw SourceDeDonneesException("Erreur: ${réponse.code}")
            }

            if (body == null){

                throw SourceDeDonneesException("Aucune donnée reçues")

            }

            Log.e("Org debug", body)
            return DecodeurJson.décoderJsonVersListeEvenements(body)
        }
        catch (e: IOException){
            throw SourceDeDonneesException(e.message ?: "Erreur inconnue")
        }
    }

    override suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: String): Boolean {
        try{
            val client = OkHttpClient()

            val output = ByteArrayOutputStream()

            val body = RequestBody.create(
                "application/json".toMediaTypeOrNull(), output.toString()
            )

            val requête = Request.Builder()
                .header("Authorization", "Bearer $accessToken")
                .url( "$url_api/evenements/$idEvenement/rejoindre" )
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
}