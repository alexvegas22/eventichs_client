package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement

class EvenementModele {
    lateinit var evenementCourant: Evenement

    fun obtenirMétéo(): ConditionMeterologique {
        return MockData.météo
    }
}