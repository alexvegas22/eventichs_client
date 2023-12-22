package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import com.squareup.picasso.Picasso
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context = container!!.context

        val view = inflater.inflate(R.layout.fragment_profil, container, false)
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
        imageProfil.setImageResource(R.drawable.ic_search)
        try {
            // Use Picasso to load the image from the URL
            Picasso.get().load(DonneesProfil.imageId).into(imageProfil)
        } catch (e : Exception) {
            Log.e("Image Error","Woopsie")
        }

        nomProfil.setText(DonneesProfil.prenom+" "+DonneesProfil.nom)
        emailProfil.setText(DonneesProfil.email)
        adresseProfil.setText(DonneesProfil.adresse)
        imageProfil.setBackgroundResource(R.drawable.round_image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
