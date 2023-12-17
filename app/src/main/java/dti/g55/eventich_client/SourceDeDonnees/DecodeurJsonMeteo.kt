package dti.g55.eventich_client.SourceDeDonnees

import android.util.JsonReader
import android.widget.ImageView
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

                                        0 -> {
                                            description = "Dégagé"
                                        }
                                        1  -> {
                                            description = "Partiellement nuageux"
                                        }
                                        2 -> {
                                            description = "Partiellement nuageux"
                                        }
                                        3 -> {
                                            description = "Nuageux"
                                        }
                                        45 -> {
                                            description = "Nuageux"
                                        }
                                        48 -> {
                                            description = "Nuageux"
                                        }
                                        51 -> {
                                            description = "Légère pluie"
                                        }
                                        53 -> {
                                            description = "Pluie"
                                        }
                                        55 -> {
                                            description = "Forte pluie"
                                        }
                                        56 -> {
                                            description = "Légère pluie verglacente"
                                        }
                                        57 -> {
                                            description = "Forte pluie verglacente"
                                        }
                                        61 -> {
                                            description = "Légère pluie"
                                        }
                                        63 -> {
                                            description = "Pluie"
                                        }
                                        65 -> {
                                            description = "Forte pluie"
                                        }
                                        66 -> {
                                            description = "Légère pluie verglacente"
                                        }
                                        67 -> {
                                            description = "Forte pluie verglacente"
                                        }
                                        71 -> {
                                            description = "Neige"
                                        }
                                        73 -> {
                                            description = "Neige"
                                        }
                                        75 -> {
                                            description = "Neige"
                                        }
                                        77 -> {
                                            description = "Neige"
                                        }
                                        80 -> {
                                            description = "Forte pluie"
                                        }
                                        81 -> {
                                            description = "Forte pluie"
                                        }
                                        82 -> {
                                            description = "Forte pluie"
                                        }
                                        85 -> {
                                            description = "Forte pluie"
                                        }
                                        86 -> {
                                            description = "Forte pluie"
                                        }
                                        95 -> {
                                            description = "Orages"
                                        }
                                        96 -> {
                                            description = "Orages"
                                        }
                                        99 -> {
                                            description = "Orages"
                                        }
                                        else -> {
                                            description = "Non disponible"
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
                var image = R.drawable.day_cloudy_icon
                when( listeCode[i]){
                    0 -> {
                        listeDescription += "Dégagé"
                        image = R.drawable.day_sunny_icon
                    }
                    1  -> {
                        listeDescription += "Partiellement nuageux"
                        image = R.drawable.day_cloudy_icon
                    }
                    2 -> {
                        listeDescription += "Partiellement nuageux"
                        image = R.drawable.day_cloudy_icon
                    }
                    3 -> {
                        listeDescription += "Nuageux"
                        image = R.drawable.cloudy_icon
                    }
                    45 -> {
                        listeDescription += "Nuageux"
                        image = R.drawable.cloudy_icon
                    }
                    48 -> {
                        listeDescription += "Nuageux"
                        image = R.drawable.cloudy_icon
                    }
                    51 -> {
                        listeDescription += "Légère pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    53 -> {
                        listeDescription += "Pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    55 -> {
                        listeDescription += "Forte pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    56 -> {
                        listeDescription += "Légère pluie verglacente"
                        image = R.drawable.cloud_snow_icon
                    }
                    57 -> {
                        listeDescription += "Forte pluie verglacente"
                        image = R.drawable.cloud_snow_icon
                    }
                    61 -> {
                        listeDescription += "Légère pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    63 -> {
                        listeDescription += "Pluie"
                        image = R.drawable.cloud_snow_icon

                    }
                    65 -> {
                        listeDescription += "Forte pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    66 -> {
                        listeDescription += "Légère pluie verglacente"
                        image = R.drawable.cloud_snow_icon
                    }
                    67 -> {
                        listeDescription += "Forte pluie verglacente"
                        image = R.drawable.cloud_snow_icon
                    }
                    71 -> {
                        listeDescription += "Neige"
                        image = R.drawable.snowflake_icon
                    }
                    73 -> {
                        listeDescription += "Neige"
                        image = R.drawable.snowflake_icon
                    }
                    75 -> {
                        listeDescription += "Neige"
                        image = R.drawable.snowflake_icon
                    }
                    77 -> {
                        listeDescription += "Neige"
                        image = R.drawable.snowflake_icon
                    }
                    80 -> {
                        listeDescription += "Forte pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    81 -> {
                        listeDescription += "Forte pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    82 -> {
                        listeDescription += "Forte pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    85 -> {
                        listeDescription += "Forte pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    86 -> {
                        listeDescription += "Forte pluie"
                        image = R.drawable.cloud_snow_icon
                    }
                    95 -> {
                        listeDescription += "Orages"
                        image = R.drawable.cloud_snow_lightning_icon
                    }
                    96 -> {
                        listeDescription += "Orages"
                        image = R.drawable.cloud_snow_lightning_icon
                    }
                    99 -> {
                        listeDescription += "Orages"
                        image = R.drawable.cloud_snow_lightning_icon
                    }
                    else -> {
                        listeDescription += "Non disponible"
                    }
                }
                listeHeureMeteo += HeureMeteo(i, image, listeDescription[i], listeTemp[i], listeHumidite[i])
            }

            return listeHeureMeteo
        }
    }
}
