package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesException
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.domaine.entite.Evenement

class EvenementModele(val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")) : IModeleEvenement {
    override suspend fun obtenirNbParticipants(evenement: Evenement): String {
        return source.obtenirNbParticipants(evenement).toString()
    }

    override lateinit var evenementCourant: Evenement
}