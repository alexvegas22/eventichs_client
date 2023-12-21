package dti.g55.eventich_client.presentation.modeles

import android.content.Context

object ModeleFactory {
    val evenements = EvenementModele()
    val listeEvenements = ListeEvenementModele()
    val listeOrganisations = ListeOrganisationsModele()
    val meteo = MeteoModele()
    val profil = ProfilModele()
    val preferences = PreferenceModele()
    lateinit var donneesLocales : DonneesLocalesModele
    fun connectSQLite(context: Context){
        donneesLocales = DonneesLocalesModele(context)
    }
}