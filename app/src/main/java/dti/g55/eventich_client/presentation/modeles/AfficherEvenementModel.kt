package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.domaine.entite.Evenement

val testEvenement = Evenement("TEST")
class AfficherEvenementModel(var événement: Evenement = testEvenement) {

}