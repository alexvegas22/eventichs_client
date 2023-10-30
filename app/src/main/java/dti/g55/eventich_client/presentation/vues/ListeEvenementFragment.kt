package dti.g55.eventich_client.presentation.vues

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.presentation.modeles.EvenementListeItem
import dti.g55.eventich_client.presentation.modeles.ListeEvenementViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ListeEvenementFragment : Fragment() {

    private lateinit var listeEvenements: List<EvenementListeItem>
    private lateinit var recycler: RecyclerView
    private lateinit var model: ListeEvenementViewModel

    private lateinit var context: Context
    private lateinit var startDateSelectorDialog: DatePickerDialog
    private lateinit var endDateSelectorDialog: DatePickerDialog
    private lateinit var btnDateDebut: Button
    private lateinit var btnDateFin: Button
    private lateinit var inputRecherche: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        context = container!!.context

        return inflater.inflate(R.layout.fragment_liste_evenement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.rvListeEvenements)
        model = ListeEvenementViewModel()

        var calendrier: Calendar = Calendar.getInstance()
        var annee = calendrier.get(Calendar.YEAR)
        var mois = calendrier.get(Calendar.MONTH)
        var moisCourant = mois + 1
        var jour = calendrier.get(Calendar.DAY_OF_MONTH)

        initSelecteurDate()

        inputRecherche = view.findViewById(R.id.inputRecherche)

        btnDateDebut = view.findViewById(R.id.btnDateDebut)
        btnDateDebut.setOnClickListener { view: View -> ouvrirDebutSelecteurDate(view) }
        btnDateDebut.text = makeDateString(jour, moisCourant, annee)
        btnDateFin = view.findViewById(R.id.btnDateFin)
        btnDateFin.setOnClickListener { view: View -> ouvrirFinSelecteurDate(view) }
        btnDateDebut.text = makeDateString(jour, moisCourant, annee)

        setInfoAdapter()
        updateAdapter(parseDate("10-09-2023"), parseDate("30-12-2023"))
    }

    private fun setInfoAdapter(){
        listeEvenements = mutableListOf()
        var adapter = ListeEvenementRecyclerAdapter(listeEvenements, context)
        var layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter
    }

    private fun updateAdapter(dateDebut: Date, dateFin: Date){
        listeEvenements = model.getListeEvenementsBetweenDates(dateDebut, dateFin)
            .filter {
                it.nom.contains(inputRecherche.text) || it.location.contains(inputRecherche.text)
            }

        println(listeEvenements);

        recycler.adapter = ListeEvenementRecyclerAdapter(listeEvenements, context)
    }

    private fun initSelecteurDate(){
        val startDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            btnDateDebut.text = makeDateString(dayOfMonth, monthOfYear, year)
            updateAdapter(parseDate(btnDateDebut.text.toString()), parseDate(btnDateFin.text.toString()))
        }

        val endDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            btnDateFin.text = makeDateString(dayOfMonth, monthOfYear, year)
            updateAdapter(parseDate(btnDateDebut.text.toString()), parseDate(btnDateFin.text.toString()))
        }

        var calendrier: Calendar = Calendar.getInstance()

        var year = calendrier.get(Calendar.YEAR)
        var month = calendrier.get(Calendar.MONTH)
        var day = calendrier.get(Calendar.DAY_OF_MONTH)

        startDateSelectorDialog = DatePickerDialog(context, startDateSetListener, year, month, day)
        endDateSelectorDialog = DatePickerDialog(context, endDateSetListener, year, month, day)
    }

    private fun parseDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        return dateFormat.parse(dateString)
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String {
        var actualMonth = month + 1
        return "$day-$actualMonth-$year"
    }

    fun ouvrirDebutSelecteurDate(view: View){
        startDateSelectorDialog.show()
    }

    fun ouvrirFinSelecteurDate(view: View){
        endDateSelectorDialog.show()
    }
}