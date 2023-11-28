package dti.g55.eventich_client.SourceDeDonnees

import dti.g55.eventich_client.domaine.entite.Evenement
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class EventichsAPI : ISourceDonnee {

    override fun obtenirListeEvenements(): ArrayList<Evenement> {
        TODO("Not yet implemented")
    }

    override fun obtenirOrganisations(): ArrayList<String> {
        TODO("Not yet implemented")
    }
    private val client = OkHttpClient()

    fun obtenirTousLesEvenements() {
        val request = Request.Builder()
            .url("www.v34l.com:8080/evenements")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())
                }
            }
        })
    }
}