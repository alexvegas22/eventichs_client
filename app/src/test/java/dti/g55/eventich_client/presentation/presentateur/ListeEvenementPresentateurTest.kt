package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.IModeleListeEvenements
import dti.g55.eventich_client.presentation.modeles.ListeEvenementModele
import dti.g55.eventich_client.presentation.vues.IVueListeEvenement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.Date

class ListeEvenementPresentateurTest {
    val evenement1 = Evenement(
        0,
        "Événement de Test",
        "3 Rue de la rue",
        Date(),
        Date(),
        "Repentigny, QC J5Y 2C2",
        "Divers",
        "Objet factice créé à des fins de tests",
        "https://www.paperlesspost.com/blog/wp-content/uploads/Blog00_FallParty_PeopleToastInField.png",
        "Alpha Group"
    )
    val evenement2 = Evenement(
        1,
        "Party de sorciers",
        "5 Rue de la rue",
        Date(),
        Date(),
        "Repentigny, QC J5Y 2C2",
        "Divers",
        "Objet factice créé à des fins de tests",
        "https://www.paperlesspost.com/blog/wp-content/uploads/Blog00_FallParty_PeopleToastInField.png",
        "Alpha Group"
    )
    val evenement3 = Evenement(
        2,
        "Assemblée générale des lutins",
        "x-5678 y-120 z-33 Atelier secret",
        Date(),
        Date(),
        "Public",
        "Loisirs",
        "Assemblée très importante, humains interdits",
        "https://www.paperlesspost.com/blog/wp-content/uploads/Blog00_FallParty_PeopleToastInField.png",
        "Santa Inc"
    )

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    @Test
    fun `Étant donné un ListeEvenementPresentateurTest nouvellement instantié, lorsqu'on appelle la fonction setupListeEvenements, la fonction setupListeEvenements de la vue est appelé avec la liste d'evenements du modèle passé en paramètre`() {
        val mockVue = mock(IVueListeEvenement::class.java)

        val modèle = ListeEvenementModele();

        val présentateur = ListeEvenementPresentateur(
            mockVue,
            listeEvenementsModele = modèle
        )

        présentateur.setupListeEvenements()

        verify(mockVue).setupListeEvenements(modèle.listeEvenements)
    }

    @Test
    fun `Étant donné un ListeEvenementPresentateurTest, lorsqu'on appelle la fonction getListeEvenementsEntreDatesFiltrer avec «arty», alors rafraichirListeEvenements de la vue avec la liste filtré du modèle est appelé`() = runTest {
        val mockVue = mock(IVueListeEvenement::class.java)
        val mockSource = mock(ISourceDonnee::class.java)
        val modèle = ListeEvenementModele(mockSource)
        val présentateur = ListeEvenementPresentateur(
            mockVue,
            listeEvenementsModele = modèle
        )

        modèle.dateDebut.time = 0
        modèle.dateFin.time = Long.MAX_VALUE

        `when`(mockSource.obtenirListeEvenements()).thenReturn(arrayListOf(evenement1, evenement2, evenement3))

        println(modèle.getListeEvenementsEntreDates(modèle.dateDebut, modèle.dateFin))
        println(modèle.getListeFiltrer(arrayListOf(evenement1, evenement2, evenement3), "arty"))
        présentateur.getListeEvenementsEntreDatesFiltrer("arty")

        verify(mockVue).rafraichirListeEvenements(arrayListOf(evenement2))
    }
}