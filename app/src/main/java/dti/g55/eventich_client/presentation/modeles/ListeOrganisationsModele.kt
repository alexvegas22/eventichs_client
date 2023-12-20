package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP

class ListeOrganisationsModele(val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")) : IModeleListeOrganisations {

    override var listeOrganisations = ArrayList<String>()
    var profilModele = ProfilModele()

    override suspend fun retournerOrganisationsPubliques(): ArrayList<String> {
        return source.obtenirOrganisationsPubliques()
    }

    override suspend fun retournerOrganisationsInscrites(): ArrayList<String> {
        return source.obtenirOrganisations(profilModele.getProfil())
    }

}