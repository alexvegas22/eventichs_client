package dti.g55.eventich_client.SourceDeDonnees

import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.Organisation
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur

interface ISourceDonnee {
    suspend fun obtenirListeEvenements() : ArrayList<Evenement>
    suspend fun obtenirListeEvenementsInscrits() : ArrayList<Evenement>
    suspend fun obtenirNbParticipants(evenement: Evenement) : Int
    suspend fun obtenirOrganisations(): ArrayList<Organisation>
    suspend fun obtenirConditionMeteorologique(): ConditionMeterologique
    suspend fun obtenirOrganisationsPubliques(): ArrayList<Organisation>
    suspend fun obtenirEvenementsParOrganisation(organisation : Organisation): ArrayList<Evenement>
    suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: String): Boolean
}