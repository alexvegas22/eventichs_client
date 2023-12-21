package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.domaine.entite.Evenement

class EvenementModele(val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")) : IModeleEvenement {
    override suspend fun obtenirNbParticipants(evenement: Evenement): String {
        return source.obtenirNbParticipants(evenement).toString()
    }

    override fun verifierParticipationEvenement(idEvenement: Int, listeEvenementsInscrits: ArrayList<Evenement>): Boolean {
        return (listeEvenementsInscrits.any { it.id == idEvenement })
    }

    override suspend fun rejoindreEvenement(idEvenement: Int, idUtilisateur: Int): Boolean {
        return source.rejoindreEvenement(idEvenement, idUtilisateur)
    }

    override lateinit var evenementCourant: Evenement
}