package dti.g55.eventich_client.SourceDeDonnees

import dti.g55.eventich_client.domaine.entite.Evenement
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class SourceDeDonneesHTTP(val url_api: String): ISourceDonnee {
    @Throws(SourceDeDonneesException::class)
    override suspend fun obtenirListeEvenements(): ArrayList<Evenement> {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().url("$url_api/evenements").build()

            val réponse = client.newCall(requête).execute()

            val body = réponse.body()?.string()

            if (réponse.code() != 200) {
                throw SourceDeDonneesException("Erreur: ${réponse.code()}")
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
    override suspend fun obtenirOrganisations(): ArrayList<String> {
        return arrayListOf<String>()
    }

}