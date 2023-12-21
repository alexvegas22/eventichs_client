package dti.g55.eventich_client.SourceDeDonnees

import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur

interface ISourceDonnee {
    suspend fun obtenirListeEvenements() : ArrayList<Evenement>
    suspend fun obtenirListeEvenementsInscrits(profil: ProfilUtilisateur) : ArrayList<Evenement>
    suspend fun obtenirNbParticipants(evenement: Evenement) : Int
    suspend fun obtenirOrganisations(profil : ProfilUtilisateur): ArrayList<String>
    suspend fun obtenirConditionMeteorologique(): ConditionMeterologique
    suspend fun obtenirOrganisationsPubliques(): ArrayList<String>
    suspend fun obtenirEvenementsParOrganisation(organisation : String): ArrayList<Evenement>
    suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: Int): Boolean
}