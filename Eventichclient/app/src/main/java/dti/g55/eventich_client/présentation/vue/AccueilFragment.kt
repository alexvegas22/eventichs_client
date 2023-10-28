package dti.g55.eventich_client.présentation.vue

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eventich_client.R
import dti.g55.eventich_client.présentation.modèle.AccueilViewModel

class AccueilFragment : Fragment() {

    companion object {
        fun newInstance() = AccueilFragment()
    }

    private lateinit var viewModel: AccueilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accueil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccueilViewModel::class.java)
        // TODO: Use the ViewModel
    }

}