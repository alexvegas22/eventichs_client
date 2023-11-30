package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.domaine.entite.Evenement

class EvenementModele(val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")) {
    suspend fun obtenirBbParticipants(evenement: Evenement): String {
        return source.obtenirNbParticipants(evenement).toString()
    }

    lateinit var evenementCourant: Evenement
}