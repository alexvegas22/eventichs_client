package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.EvenementModele
import dti.g55.eventich_client.presentation.vues.EvenementVue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class EvenementPresentateurTest {
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
    fun `étant donné un Modèle instancié, lorsque le présentateur appelle la fonction init(), Il appelle la fonction changerCouleursTextInitiales(), afficherAnimationChargement() de la vue et charger_données()`() = runTest {

        // mock
        val vue : EvenementVue = Mockito.mock( EvenementVue::class.java )
        val modèle = Mockito.mock( EvenementModele::class.java )
        // sujet de test - mock pour pouvoir spy avec mockito
        val presentateur = EvenementPresentateur(vue, modèle)
        // spy
        val spy = Mockito.spy(presentateur)

        Mockito.`when`(modèle.evenementCourant).thenReturn(Evenement(1, "Test", "Test", Date(), Date(), "public", "test", "Test", "Test", "Test"))

        spy.init()
        Mockito.verify(vue).changerCouleursTextInitiales()
        Mockito.verify(vue).afficherAnimationChargement()
        Mockito.verify(spy, times(1)).charger_données()
        Mockito.verify(spy, times(1)).afficherStatusParticipation()
    }
}