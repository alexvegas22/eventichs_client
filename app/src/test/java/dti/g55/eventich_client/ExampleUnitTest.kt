package dti.g55.eventich_client

import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.Modele
import dti.g55.eventich_client.presentation.presentateur.EvenementPresentateur
import dti.g55.eventich_client.presentation.vues.EvenementVue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
//import org.junit.Test
import org.junit.Assert.*
import kotlin.test.*
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.After
import kotlinx.coroutines.test.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

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
    fun `étant donné un Modèle instancié, lorsque le présentateur appelle la fonction getEvenementSelectionne, on obtient un événement nommé bravo`() = runTest {

        val modele : Modele = Mockito.mock( Modele::class.java )
        val bravo = Evenement(999,R.drawable.ic_search, "bravo",  1111, Date(), "sans importance", "sans importance", "",
            "sans importance")
        Mockito.`when`( modele.getEvenementSelectionne(999) ).thenReturn( bravo )

        val evenement = modele.getEvenementSelectionne(999)
        kotlin.test.assertEquals(bravo, evenement)
    }

    @Test
    fun `étant donné un Modèle instancié, lorsque le présentateur appelle la fonction getEvenementSelectionne, on obtient l'événement au bon index passé en parametre à la fonction`() = runTest {

        val modele = Modele()

        val evenement = modele.getEvenementSelectionne(3)
        kotlin.test.assertEquals(evenement, MockData.evenements[3])
    }
}