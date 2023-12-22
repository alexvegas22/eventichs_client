package dti.g55.eventich_client.SourceDeDonnees

import android.annotation.SuppressLint
import android.util.JsonReader
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.Organisation
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
        fun décoderJsonVersNbParticipants(json: String) : Int{

            var nbParticipants = 0
            val reader = JsonReader(StringReader(json))

            reader.beginObject()
            while (reader.hasNext()){
                val clé = reader.nextName()

                when (clé){
                    "nbParticipants" -> {
                        nbParticipants = reader.nextInt()
                    }
                    else -> reader.skipValue()
                }
            }
            reader.endObject()
            return nbParticipants
        }

        @SuppressLint("SimpleDateFormat")
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
                    "categorie" -> {
                        categorie = reader.nextString()
                    }
                    "description" -> {
                        description = reader.nextString()
                    }
                    "image" -> {
                        photo = reader.nextString()
                    }
                    "organisation" -> {
                        organisation = reader.nextString()
                    }
                    else -> reader.skipValue()
                }
            }
            reader.endObject()

            return Evenement(id, nom,  adresse, dateFormat.parse(dateDebut), dateFormat.parse(dateFin), type ,description, categorie, photo, organisation)
        }

        fun décoderJSONVersListeOrganisations(json: String): ArrayList<Organisation> {
            val liste = arrayListOf<Organisation>()

            val reader = JsonReader(StringReader(json))
            reader.beginArray()
            while (reader.hasNext()) {
                reader.beginObject()
                var id = 0
                var nomOrganisation = ""
                while (reader.hasNext()) {
                    val clé = reader.nextName()

                    when (clé) {
                        "id_organisation" -> {
                            id = reader.nextInt()
                        }
                        "code_utilisateur" -> {
                            nomOrganisation = reader.nextString()
                        }
                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
                liste += Organisation(id, nomOrganisation)
            }

            return liste
        }

        fun décoderJSONVersOrganisations(json: String): Organisation {
            val reader = JsonReader(StringReader(json))

            reader.beginObject()
            var id : Int = 0
            var nomOrganisation : String = ""
            while (reader.hasNext()) {
                val clé = reader.nextName()

                when (clé) {
                    "id_organisation" -> {
                        id = reader.nextInt()
                    }
                    "nomOrganisation" -> {
                        nomOrganisation = reader.nextString()
                    }
                    else -> reader.skipValue()
                }
            }
            reader.endObject()
           var liste = Organisation(id, nomOrganisation)



            return liste
        }
    }
}