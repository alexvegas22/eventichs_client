package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import dti.g55.eventich_client.MainActivity

import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import dti.g55.eventich_client.presentation.presentateur.MainPresentateur
import dti.g55.eventich_client.presentation.presentateur.MainPresenterImpl
import dti.g55.eventich_client.presentation.presentateur.ProfilPresentateur

class ProfilVue : Fragment() {

    private lateinit var context: Context
    private lateinit var imageProfil : ImageView
    private lateinit var nomProfil : TextView
    private lateinit var emailProfil : TextView
    private lateinit var adresseProfil : TextView
    private lateinit var profilUtilisateur: ProfilUtilisateur
    private var presentateur: ProfilPresentateur= ProfilPresentateur(this)
    private var mairPresentateur : MainPresentateur = MainPresenterImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context = container!!.context

        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        mairPresentateur.attachView(requireActivity() as MainActivity)
        val toggleTheme : Button = view.findViewById(R.id.ThemeToggle)
        toggleTheme.setOnClickListener { mairPresentateur.toggleTheme() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        imageProfil = view.findViewById(R.id.profile_image)
        nomProfil = view.findViewById(R.id.profile_name)
        emailProfil = view.findViewById(R.id.profile_email)
        adresseProfil = view.findViewById(R.id.profile_adresse)
        imageProfil.setBackgroundResource(R.drawable.round_image)

        presentateur.traiterDemarrage()

    }
    fun updateProfileComponents(DonneesProfil : ProfilUtilisateur){
        imageProfil.setImageResource(DonneesProfil.imageId)
        nomProfil.setText(DonneesProfil.prenom+" "+DonneesProfil.nom)
        emailProfil.setText(DonneesProfil.email)
        adresseProfil.setText(DonneesProfil.adresse)
        imageProfil.setBackgroundResource(R.drawable.round_image)
    }

    override fun onDestroyView() {
        mairPresentateur.detachView()
        super.onDestroyView()
    }
}