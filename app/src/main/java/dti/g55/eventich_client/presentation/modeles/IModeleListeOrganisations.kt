package dti.g55.eventich_client.presentation.modeles

interface IModeleListeOrganisations {
    var listeOrganisations : ArrayList<String>

    suspend fun retournerOrganisationsPubliques(): ArrayList<String>
    suspend fun retournerOrganisationsInscrites(): ArrayList<String>
}