package dti.g55.eventich_client.presentation.vues

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.presentateur.BoutonDateTag
import dti.g55.eventich_client.presentation.presentateur.ListeEvenementPresentateur
import dti.g55.eventich_client.utilitaire.CustomRecyclerAdapter
import java.util.Calendar
import java.util.Date

class ListeEvenementVue : Fragment(), IVueListeEvenement {

    private lateinit var presentateur : ListeEvenementPresentateur
    private lateinit var recycler: RecyclerView

    private lateinit var btnDateDebut: Button
    private lateinit var btnDateFin: Button
    private lateinit var inputRecherche: EditText
    private lateinit var animationChargement : LottieAnimationView
    private lateinit var context: Context
    private lateinit var startDateSelectorDialog: DatePickerDialog
    private lateinit var endDateSelectorDialog: DatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        context = container!!.context
        presentateur = ListeEvenementPresentateur(this, context)
        return inflater.inflate(R.layout.fragment_liste_evenement, container, false)
    }

    override fun disposerVueChargement(){
        btnDateDebut.isEnabled = false
        btnDateFin.isEnabled = false
        recycler.isVisible = false
        animationChargement.isVisible = true
        inputRecherche.isEnabled = false
    }

    override fun disposerVueChargementTerminé(){
        btnDateDebut.isEnabled = true
        btnDateFin.isEnabled = true
        recycler.isVisible = true
        animationChargement.isVisible = false
        inputRecherche.isEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentateur = ListeEvenementPresentateur(this, context)
        recycler = view.findViewById(R.id.rvListeEvenements)
        btnDateDebut = view.findViewById(R.id.btnDateDebut)
        btnDateFin = view.findViewById(R.id.btnDateFin)
        inputRecherche = view.findViewById(R.id.inputRecherche)
        animationChargement = view.findViewById(R.id.lottieChargementListe)

        btnDateDebut.setOnClickListener { presentateur.traiterOuvertureDatePickerDialog(
            BoutonDateTag.DEBUT) }
        btnDateFin.setOnClickListener { presentateur.traiterOuvertureDatePickerDialog(BoutonDateTag.FIN) }

        inputRecherche.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                presentateur.traiterChangementRecherche(s.toString())
            }
        })

        presentateur.init()
    }

    override fun setupListeEvenements(evenements: ArrayList<Evenement>){
        var adapter = CustomRecyclerAdapter(evenements, R.layout.fragment_evenement_liste_item, ::ListeEvenementItemViewHolder, presentateur)
        var layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter
    }

    override fun setupDatePickerDialog(date: Date, tag: BoutonDateTag) {
        val dateSetListener = DatePickerDialog.OnDateSetListener {
                datePicker, year, monthOfYear, dayOfMonth -> presentateur.traiterChangementDate(datePicker, year, monthOfYear, dayOfMonth)
        }

        var calendrier: Calendar = Calendar.getInstance()
        calendrier.time = date

        var year = calendrier.get(Calendar.YEAR)
        var month = calendrier.get(Calendar.MONTH)
        var day = calendrier.get(Calendar.DAY_OF_MONTH)

        when (tag) {
            BoutonDateTag.DEBUT -> {
                startDateSelectorDialog = DatePickerDialog(requireContext(), dateSetListener, year, month, day)
                startDateSelectorDialog.datePicker.tag = BoutonDateTag.DEBUT
            }
            BoutonDateTag.FIN -> {
                endDateSelectorDialog = DatePickerDialog(requireContext(), dateSetListener, year, month, day)
                endDateSelectorDialog.datePicker.tag = BoutonDateTag.FIN
            }
        }
    }

    override fun rafraichirListeEvenements(evenements: ArrayList<Evenement>){
        recycler.adapter = CustomRecyclerAdapter(evenements, R.layout.fragment_evenement_liste_item, ::ListeEvenementItemViewHolder, presentateur)
    }

    override fun ouvrirSelecteurDateDebut(){
        startDateSelectorDialog.show()
    }

    override fun ouvrirSelecteurDateFin(){
        endDateSelectorDialog.show()
    }

    override fun changerTexteBoutonDateDebut(texte: String) {
        btnDateDebut.text = texte
    }

    override fun changerTexteBoutonDateFin(texte: String){
        btnDateFin.text = texte
    }

    override fun afficherErreurDateDebutInvalide() {
        Toast.makeText(context, "La date de début ne peut pas dépasser la date de fin.", Toast.LENGTH_SHORT).show()
    }

    override fun afficherErreurDateFinInvalide() {
        Toast.makeText(context, "La date de fin ne peut pas être avant la date de début.", Toast.LENGTH_SHORT).show()
    }

    override fun allerVersEvenement() {
        findNavController().navigate(R.id.action_liste_evenements_to_fragment_afficher_evenement)
    }
}