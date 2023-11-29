package dti.g55.eventich_client.SourceDeDonnees

import android.util.JsonReader
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import java.io.StringReader
import java.text.SimpleDateFormat

class DecodeurJson {
    companion object {
        fun décoderJsonVersListeEvenements(json: String): ArrayList<Evenement> {
            val liste = arrayListOf<Evenement>()

            val reader = JsonReader(StringReader(json))

            reader.beginArray()
            while (reader.hasNext()){
                liste += décoderJsonVersEvenement(reader)
            }
            reader.endArray()

            return liste
        }

        fun décoderJsonVersEvenement(reader: JsonReader): Evenement {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            reader.beginObject()

            var id: Int = 0
            var nom: String = ""
            var adresse: String = ""
            var dateDebut: String = ""
            var dateFin: String = ""
            var type: String = ""
            var categorie: String = ""
            var description: String = ""
            var photo: String = ""
            var organisation: String = ""

            while (reader.hasNext()){
                val clé = reader.nextName()

                when (clé){
                    "id" -> {
                        id = reader.nextInt()
                    }
                    "nom" -> {
                        nom = reader.nextString()
                    }
                    "adresse" -> {
                        adresse = reader.nextString()
                    }
                    "dateDebut" -> {
                        dateDebut = reader.nextString()
                    }
                    "dateFin" -> {
                        dateFin = reader.nextString()
                    }
                    "type" -> {
                        type = reader.nextString()
                    }
                    "categorie_Id" -> {
                        categorie = reader.nextString()
                    }
                    "description" -> {
                        description = reader.nextString()
                    }
                    "photo" -> {
                        photo = reader.nextString()
                    }
                    "organisation_Id" -> {
                        organisation = reader.nextString()
                    }
                }
            }
            reader.endObject()

            return Evenement(id, R.drawable.ic_search, nom, 0, dateFormat.parse(dateDebut), dateFormat.parse(dateFin), adresse, organisation, categorie, description)
        }
    }
}