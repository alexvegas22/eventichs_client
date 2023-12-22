package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.domaine.entite.Evenement

interface IModeleEvenement {
    var evenementCourant: Evenement
    suspend fun obtenirNbParticipants(evenement: Evenement): String
    fun verifierParticipationEvenement(idEvenement: Int, listeEvenementsInscrits: ArrayList<Evenement>): Boolean
    suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: String): Boolean
}