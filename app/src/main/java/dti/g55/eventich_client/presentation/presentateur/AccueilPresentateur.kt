package dti.g55.eventich_client.presentation.presentateur

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dti.g55.eventich_client.R
import dti.g55.eventich_client.SourceDeDonnees.DatabaseHelper
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesException
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesSQL
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.Organisation
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import dti.g55.eventich_client.presentation.modeles.ListeEvenementModele
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.AccueilVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.CoroutineContext

class AccueilPresentateur(var vue: AccueilVue,  context: Context): IPresentateur {

    private var listeEvenementModele = ModeleFactory.listeEvenements
    var donneesLocales = SourceDeDonneesSQL(context)
    private var evenementModele = ModeleFactory.evenements
    private var organisationModele = ModeleFactory.listeOrganisations
    private var profilModele = ModeleFactory.profil
    private lateinit var nomsOrganisation : ArrayList<Organisation>
    var dbh = DatabaseHelper(context)
    val coroutineContext: CoroutineContext = Dispatchers.IO
    /**
     * Traite le démarrage de la vue Fragment Profil
     */
    override fun init() {
        afficherListeEvenements()
        val profil= ProfilUtilisateur(
            "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjlBNDRET0VwQTNYMUw2bUhESG8tWiJ9.eyJpc3MiOiJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZkM2NiNmExOTU5OWM5MjA5YTYwOTkiLCJhdWQiOlsiaHR0cDovL2V2ZW50aWNocy5hcGkiLCJodHRwczovL2Rldi14YTJmNnFjcmI3a2FwMzNuLnVzLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE3MDMyMzc0OTksImV4cCI6MTcwMzMyMzg5OSwiYXpwIjoibFdpa3dMRzJVa01Yb0N2TG9hNHo3QUdBUldPQ1BaaXYiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIGFkZHJlc3MgcGhvbmUiLCJndHkiOiJwYXNzd29yZCJ9.dGFAEl-34_D3HyYBdEHJi-HsvXtNNezaavg4h917P9l-9f7IolMyB_GVncD4aA4-O_u1pC-FuTXHsQwpAC3-t3ZY-OhvoBAMgtxWvI-Id2QY0Lwqy51N2g7iImvt9R1qCINVNHTZYLrianIYQ5y-g_U5NBSaj1Tlkq-sjtc-lyiEhAxSxYhEQkFjse0QrwR4aTk6rnEkb_1yMYncWvBRStbzNw3mTQ5lb2ocp_ubdMru48hmyNKTB4935WfwTAiLbNmbugO6UkCWz2dEn0AP91XzfIjwv58Z7skVVFWNwdJDFE8OGdR1y8XaLZYjY8BhLx7v-_v4ABuwniFsWNZXAg",
            "https://alistaircockburn.com/gallery_gen/7bb3d21dfcbc79ed0da594e3f713c736_492x492_0x57_492x606_crop.png",
            "Délice",
            "Joe",
            "5142224444",
            "joe@email.com",
            "6400 16e Avenue, Montreal, Quebec"
        )
        donneesLocales.seConnecter(profil)
        Log.e("SQLite", dbh.getTableColumns().toString())
        profilModele.setProfil(donneesLocales.obtenirUtilisateur())
    }

    /**
     * Passe la liste des evenements, la liste de noms d'organisations et les évènements de ces organisations à la vue
    */
    fun afficherListeEvenements() {
        vue.afficherChargement()
        var evenementsInscrits = ArrayList<Evenement>()
        nomsOrganisation = ArrayList<Organisation>()
        var evenementsOrganisations = ArrayList<Evenement>()
        listeEvenementModele = ModeleFactory.listeEvenements
        CoroutineScope(Dispatchers.IO).launch {
            try {
                evenementsInscrits = listeEvenementModele.listeEvenementsInscrits()
                nomsOrganisation = organisationModele.retournerOrganisationsInscrites()
                Thread.sleep(1000)
                evenementsOrganisations = listeEvenementModele.getEvenementParOrganisation(Organisation(1,"Rosemont"))
                if (evenementsInscrits.isNotEmpty()) listeEvenementModele.sync(vue.requireContext())

            } catch (e : TimeoutCancellationException){
               evenementsInscrits = donneesLocales.obtenirListeEvenements()
                nomsOrganisation = donneesLocales.obtenirOrganisations()
                evenementsOrganisations = donneesLocales.obtenirEvenementsParOrganisation(Organisation(2,"Illuminati"))
            }

            CoroutineScope(Dispatchers.Main).launch {

                vue.masquerChargement()
                vue.setupRecyclerView(evenementsInscrits, nomsOrganisation, evenementsOrganisations)
            }
        }


    }
    fun getEvenementsParOrganisation(organisation : Organisation){
        var listeEvenements = arrayListOf<Evenement>()
        CoroutineScope(coroutineContext).launch {
            try {
                val listeEvenements = withContext(Dispatchers.IO) {
                    listeEvenementModele.getEvenementParOrganisation(organisation)
                }

                Log.e("Update org events", listeEvenements.toString())

                // Update the UI with the obtained events
                withContext(Dispatchers.Main) {
                    if (listeEvenements.isNotEmpty()) {
                        vue.rafraichirOrganisationEvenements(listeEvenements)
                    }
                }
            } catch (e: SourceDeDonneesException) {
                println(e)
            }
        }
    }
    fun traiterClickOrganisation(organisation : String){
        Log.e("Click Org",organisation)
        for (org in nomsOrganisation){
            if (org.nom == organisation) {
                getEvenementsParOrganisation(org)
            }
        }



    }
    fun traiterClickEvenement(evenement: Evenement){
        evenementModele.evenementCourant = evenement
        vue.allerVersEvenement()
    }
}