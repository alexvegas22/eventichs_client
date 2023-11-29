package dti.g55.eventich_client.SourceDeDonnees

import android.util.JsonReader
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import java.io.StringReader


class DecodeurJsonMeteo {
    companion object {

        fun décoderJsonVersConditionMeteo( json : String, json2: String ): ConditionMeterologique {
            val reader = JsonReader(StringReader( json ) )
            lateinit var description : String
            var tempMoyenne: Double = 0.0
            var humMoyenne: Int = 0
            lateinit var listeHeureMeteo: ArrayList<HeureMeteo>
            var codeDescription: Int = 0

            reader.beginObject()
            while (reader.hasNext()) {
                val clé = reader.nextName()

                when( clé ) {
                    "current" -> {
                        reader.beginObject()
                        while (reader.hasNext()){
                            val clé2 = reader.nextName()
                            when(clé2){
                                "temperature_2m" -> {
                                    tempMoyenne = reader.nextDouble()
                                }
                                "relative_humidity_2m" -> {
                                    humMoyenne = reader.nextInt()
                                }
                                else -> {
                                    reader.skipValue()
                                }
                            }
                        }
                        reader.endObject()
                    }
                    "daily" -> {
                        reader.beginObject()
                        while (reader.hasNext()){
                            val clé2 = reader.nextName()
                            when(clé2){
                                "weather_code" -> {
                                    reader.beginArray()
                                    codeDescription = reader.nextInt()
                                    when(codeDescription){
                                        3 -> {
                                            description = "Nuageux?"
                                        }
                                        else -> {
                                            description = "ca fonctionne"
                                        }
                                    }
                                    reader.endArray()
                                }
                                else -> {
                                    reader.skipValue()
                                }
                            }
                        }
                        reader.endObject()
                    }
                    else -> {
                        reader.skipValue()
                    }
                }
            }
            reader.endObject()
            listeHeureMeteo = décoderJsonVersHeureListe(json2)
            return ConditionMeterologique( description, tempMoyenne, humMoyenne, listeHeureMeteo )
        }

        fun décoderJsonVersHeureListe( json : String ): ArrayList<HeureMeteo>{
            val reader = JsonReader(StringReader( json ) )
            var listeTemp = arrayListOf<Double>()
            var listeHumidite = arrayListOf<Int>()
            var listeCode = arrayListOf<Int>()
            var listeDescription = arrayListOf<String>()
            var listeHeureMeteo = arrayListOf<HeureMeteo>()

            reader.beginObject()
            while (reader.hasNext() ) {
                val clé = reader.nextName()

                when( clé ) {
                    "hourly" -> {
                        reader.beginObject()
                        while (reader.hasNext()){
                            val clé2 = reader.nextName()
                            when(clé2){
                                "temperature_2m" -> {
                                    reader.beginArray()
                                    while(reader.hasNext()){
                                        listeTemp += reader.nextDouble()
                                    }
                                    reader.endArray()
                                }
                                "relative_humidity_2m" -> {
                                    reader.beginArray()
                                    while(reader.hasNext()){
                                        listeHumidite += reader.nextInt()
                                    }
                                    reader.endArray()
                                }
                                "weather_code" -> {
                                    reader.beginArray()
                                    while(reader.hasNext()){
                                        listeCode += reader.nextInt()
                                    }
                                    reader.endArray()
                                }
                                else -> {
                                    reader.skipValue()
                                }
                            }

                        }
                        reader.endObject()
                    }
                    else -> {
                        reader.skipValue()
                    }
                }
            }
            reader.endObject()

            for (i in 0..23) {
                listeHeureMeteo += HeureMeteo(i, R.drawable.day_cloudy_icon, "Partiellement Nuageux", listeTemp[i], listeHumidite[i])
            }

            return listeHeureMeteo
        }
    }
}
