package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique

class MeteoModele(val source: ISourceDonnee = SourceDeDonneesHTTP("https://api.open-meteo.com/v1")) {

    suspend fun obtenirMétéo(): ConditionMeterologique {
        return source.obtenirConditionMeteorologique()
    }

}