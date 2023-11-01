package dti.g55.eventich_client.presentation.vues

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dti.g55.eventich_client.presentation.modeles.FragmentProfilViewModel
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur

class fragment_profil : Fragment() {

    private lateinit var viewModel: FragmentProfilViewModel
    private lateinit var context: Context
    private lateinit var imageProfil : ImageView
    private lateinit var nomProfil : TextView
    private lateinit var emailProfil : TextView
    private lateinit var adresseProfil : TextView
    private lateinit var profilUtilisateur: ProfilUtilisateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context = container!!.context
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        imageProfil = view.findViewById<ImageView>(R.id.profile_image)
        nomProfil = view.findViewById<TextView>(R.id.profile_name)
        emailProfil = view.findViewById<TextView>(R.id.profile_email)
        adresseProfil = view.findViewById(R.id.profile_adresse)
        imageProfil.setBackgroundResource(R.drawable.round_image)
        viewModel = ViewModelProvider(this).get(FragmentProfilViewModel::class.java)
        profilUtilisateur = ProfilUtilisateur(R.drawable.alistaire_cockburn, "Cockburn", "Alistaire", "agileKing@alliance.com","6400 16e Avenue, Montreal, Quebec")

        setProfileComponents(profilUtilisateur)
    }
    fun setProfileComponents(DonneesProfil : ProfilUtilisateur){
        imageProfil.setImageResource(DonneesProfil.imageId)
        nomProfil.setText(DonneesProfil.prenom+" "+DonneesProfil.nom)
        emailProfil.setText(DonneesProfil.email)
        adresseProfil.setText(DonneesProfil.adresse)
        imageProfil.setBackgroundResource(R.drawable.round_image)

    }
}