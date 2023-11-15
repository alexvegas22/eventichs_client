package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.MainActivity
import dti.g55.eventich_client.presentation.modeles.ModeleFactory

interface MainPresentateur {
    fun attachView(view: MainActivity)
    fun detachView()
    fun toggleTheme()
}
class MainPresenterImpl : MainPresentateur {

    private var view: MainActivity? = null
    var modele = ModeleFactory.preferences

    override fun attachView(view: MainActivity) {
        this.view = view
        view.updateTheme(modele.getTheme())
    }

    override fun detachView() {
        this.view = null
    }

    override fun toggleTheme() {
        modele.toggleTheme()
    }
}
