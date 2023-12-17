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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.Calendar
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
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }


    @Test
    fun `Étant donné un ListeEvenementPresentateur nouvellement instantié, lorsqu'on appelle la fonction setupListeEvenements, la fonction setupListeEvenements de la vue est appelé avec la liste d'evenements du modèle passé en paramètre`() {
        val mockVue = mock(IVueListeEvenement::class.java)

        val modèle = ListeEvenementModele();

        val présentateur = ListeEvenementPresentateur(
            mockVue,
            listeEvenementsModele = modèle
        )

        présentateur.setupListeEvenements()

        verify(mockVue).setupListeEvenements(modèle.listeEvenements)
    }

    /*
    @Test
    fun `Étant donné un ListeEvenementPresentateur, lorsqu'on appelle la fonction getListeEvenementsEntreDatesFiltrer avec «arty», alors rafraichirListeEvenements de la vue avec la liste filtré du modèle est appelé`() = runTest {
        val mockVue = mock(IVueListeEvenement::class.java)
        val mockModèle = mock(IModeleListeEvenements::class.java)
        val présentateur = ListeEvenementPresentateur(
            mockVue,
            listeEvenementsModele = mockModèle
        )

        val calendar = Calendar.getInstance()
        calendar.set(2000, 20, 10)
        val dateDebut = calendar.time
        calendar.set(2030, 20, 10)
        val dateFin = calendar.time

        `when`(mockModèle.dateDebut).thenReturn(dateDebut)
        `when`(mockModèle.dateFin).thenReturn(dateFin)

        `when`(mockModèle.getListeEvenementsEntreDates(dateDebut, dateFin)).thenReturn(arrayListOf(evenement1, evenement2, evenement3))

        présentateur.getListeEvenementsEntreDatesFiltrer("arty")

        verify(mockVue).rafraichirListeEvenements(arrayListOf<Evenement>())
    }
     */

    @Test
    fun `Étant donné un ListeEvenementPresentateur, lorsqu'on appelle la fonction formaterDateVersAnneeMoisJour avec un objet date du 20 janvier 2022, alors on obtient 2022-01-20`() = runTest {
        val mockVue = mock(IVueListeEvenement::class.java)
        val présentateur = ListeEvenementPresentateur(mockVue)

        val calendar = Calendar.getInstance()
        calendar.set(2022, 0, 20)
        val date = calendar.time

        assertEquals("2022-01-20", présentateur.formaterDateVersAnneeMoisJour(date))
    }
}