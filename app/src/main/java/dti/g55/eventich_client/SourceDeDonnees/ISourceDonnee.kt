package dti.g55.eventich_client.SourceDeDonnees

import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement

interface ISourceDonnee {
    suspend fun obtenirListeEvenements(): ArrayList<Evenement>
    suspend fun obtenirOrganisations(): ArrayList<String>
    suspend fun obtenirConditionMeteorologique(): ConditionMeterologique
}