package dti.g55.eventich_client

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.EvenementModele
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
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
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.times
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
}