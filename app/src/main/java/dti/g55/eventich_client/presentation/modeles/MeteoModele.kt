package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import java.util.Date

class MeteoModele {

    fun obtenirMétéo(): ConditionMeterologique {
        return MockData.météo
    }
}