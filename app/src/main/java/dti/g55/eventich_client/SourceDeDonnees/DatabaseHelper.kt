package dti.g55.eventich_client.SourceDeDonnees

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import java.time.LocalDate
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "EventichsDB"

        const val TABLE_Utilisateur = "Utilisateur"
        private const val COLUMN_codeUtilisateur = "code"
        const val COLUMN_nom = "nom"
        const val COLUMN_prénom = "prénom"
        const val COLUMN_numéroDeTéléphone = "numéroDeTéléphone"
        const val COLUMN_email = "email"
        const val COLUMN_adresse_utilisateur = "adresse"

        const val TABLE_Evenement = "Evenement"
        const val COLUMN_idEvenement = "idEvenement"
        const val COLUMN_titre = "titre"
        const val COLUMN_description = "description"
        const val COLUMN_dateDebut = "date_debut"
        const val COLUMN_dateFin = "date_fin"
        const val COLUMN_location = "location"
        const val COLUMN_cheminImage = "cheminImage"
        const val COLUMN_organisation = "organisation"
        const val COLUMN_type = "type"
        const val COLUMN_categorie = "categorie"

        const val TABLE_Organisation = "Organisation"
        const val COLUMN_idOrganisation = "id"
        const val COLUMN_nomOrganisation = "nom"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_Utilisateur = "CREATE TABLE $TABLE_Utilisateur ($COLUMN_codeUtilisateur String PRIMARY KEY, $COLUMN_cheminImage TEXT,$COLUMN_nom TEXT, $COLUMN_prénom TEXT, $COLUMN_numéroDeTéléphone INTEGER,$COLUMN_email TEXT, $COLUMN_adresse_utilisateur TEXT);"
        db.execSQL(CREATE_TABLE_Utilisateur)
        val CREATE_TABLE_Evenement = "CREATE TABLE $TABLE_Evenement ($COLUMN_idEvenement INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_titre TEXT,$COLUMN_location TEXT,$COLUMN_dateDebut Date, $COLUMN_dateFin Date,$COLUMN_description Text, $COLUMN_cheminImage TEXT, $COLUMN_organisation TEXT, $COLUMN_type TEXT, $COLUMN_categorie);"
        db.execSQL(CREATE_TABLE_Evenement)
        val  CREATE_TABLE_Organisation = "CREATE TABLE $TABLE_Organisation ($COLUMN_idOrganisation INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_nomOrganisation TEXT);"
        db.execSQL(CREATE_TABLE_Organisation)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Utilisateur)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Evenement)
        onCreate(db)
    }
    fun clearEvenements(){
        val db = writableDatabase
        db.execSQL("DELETE FROM " + TABLE_Evenement)
        ajouterEvenement(Evenement(1,"Événement Local","155 mont royal", dateFormat.parse("2023-12-11"),dateFormat.parse("2023-12-11"),"public","Party","Fourni Par SQlite","https://www.bettaeventhire.com.au/wp-content/uploads/2020/01/shutterstock_1527035324.jpg","Rosemont"))

    }

    @SuppressLint("Range")
    public fun obtenirEvenements(): ArrayList<Evenement> {
        var listeEvenements = mutableListOf<Evenement>()
        val db = readableDatabase
        val query = "SELECT * from $TABLE_Evenement"
        val cursor : Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_idEvenement))
            val nom = cursor.getString(cursor.getColumnIndex(COLUMN_titre))
            val adresse = cursor.getString(cursor.getColumnIndex(COLUMN_location))
            val dateDebut = cursor.getString(cursor.getColumnIndex(COLUMN_dateDebut))
            val dateFin = cursor.getString(cursor.getColumnIndex(COLUMN_dateFin))
            val type = cursor.getString(cursor.getColumnIndex(COLUMN_type))
            val categorie = cursor.getString(cursor.getColumnIndex(COLUMN_categorie))
            val description = cursor.getString(cursor.getColumnIndex(COLUMN_description))
            val image = cursor.getString(cursor.getColumnIndex(COLUMN_cheminImage))
            val organisation = cursor.getString(cursor.getColumnIndex(COLUMN_organisation))
            Log.e("Evenement", id.toString() + nom + adresse + dateFormat.parse(dateDebut)+ dateFormat.parse(dateFin)  + type + categorie + description + organisation)
            val evenement = Evenement(id,nom, adresse, dateFormat.parse(dateDebut), dateFormat.parse(dateFin), type, categorie, description, image, organisation)
            listeEvenements.add(evenement)
        }
        cursor.close()
        return ArrayList(listeEvenements)
    }

    fun ajouterUtilisateur(h: ProfilUtilisateur){
        val valeur = ContentValues()
        valeur.put(COLUMN_codeUtilisateur, h.code)
        valeur.put(COLUMN_cheminImage, h.imageId)
        valeur.put(COLUMN_nom, h.nom)
        valeur.put(COLUMN_email, h.email)
        valeur.put(COLUMN_prénom, h.prenom)
        valeur.put(COLUMN_numéroDeTéléphone, h.telephone)
        valeur.put(COLUMN_adresse_utilisateur, h.adresse)
        val db = writableDatabase
        db.execSQL("DELETE FROM " + TABLE_Utilisateur)
        db.insert(TABLE_Utilisateur, null, valeur)
        db.close()
    }
    fun ajouterEvenement(h: Evenement) {
        val valeur = ContentValues()
        valeur.put(COLUMN_titre, h.nom)
        valeur.put(COLUMN_description, h.description)
        valeur.put(COLUMN_dateDebut, dateFormat.format(h.dateDebut))
        valeur.put(COLUMN_dateFin, dateFormat.format(h.dateFin))
        valeur.put(COLUMN_organisation, h.organisation)
        valeur.put(COLUMN_cheminImage, h.image)
        valeur.put(COLUMN_location, h.adresse)
        valeur.put(COLUMN_type, h.type)
        valeur.put(COLUMN_categorie, h.categorie)

        writableDatabase.use { db ->
            db.insert(TABLE_Evenement, null, valeur)
        }
    }

    fun supprimerEvenement(idEvenement: Int) {
        val db = writableDatabase
        db.execSQL(
            "DELETE FROM " + TABLE_Evenement + " WHERE " +
                    COLUMN_idEvenement + "= \"" + idEvenement + "\";"
        )
    }

    @SuppressLint("Range")
    fun obtenirEvenementsParOrganisation(organisation : String): ArrayList<Evenement> {

        val listeEvenements = mutableListOf<Evenement>()
        val db = readableDatabase
        val query = "SELECT * from $TABLE_Evenement WHERE $COLUMN_organisation = '$organisation'"
        val cursor : Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_idEvenement))
            val nom = cursor.getString(cursor.getColumnIndex(COLUMN_titre))
            val adresse = cursor.getString(cursor.getColumnIndex(COLUMN_location))
            val dateDebut = cursor.getString(cursor.getColumnIndex(COLUMN_dateDebut))
            val dateFin = cursor.getString(cursor.getColumnIndex(COLUMN_dateFin))
            val type = cursor.getString(cursor.getColumnIndex(COLUMN_type))
            val categorie = cursor.getString(cursor.getColumnIndex(COLUMN_categorie))
            val description = cursor.getString(cursor.getColumnIndex(COLUMN_description))
            val image = cursor.getString(cursor.getColumnIndex(COLUMN_cheminImage))
            val organisation = cursor.getString(cursor.getColumnIndex(COLUMN_organisation))
            val evenement = Evenement(id,nom, adresse, dateFormat.parse(dateDebut), dateFormat.parse(dateFin), type, categorie, description, image, organisation)
            listeEvenements.add(evenement)
        }
        cursor.close()
        return ArrayList(listeEvenements)
    }


    fun obtenirUtilisateur(): ProfilUtilisateur {
        val db = readableDatabase
        var profil: ProfilUtilisateur? = null

        val query = "SELECT * from $TABLE_Utilisateur"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val codeIndex = cursor.getColumnIndex(COLUMN_codeUtilisateur)
            val imageIndex = cursor.getColumnIndex(COLUMN_cheminImage)
            val nomIndex = cursor.getColumnIndex(COLUMN_nom)
            val prenomIndex = cursor.getColumnIndex(COLUMN_prénom)
            val telephoneIndex = cursor.getColumnIndex(COLUMN_numéroDeTéléphone)
            val emailIndex = cursor.getColumnIndex(COLUMN_email)
            val adresseIndex = cursor.getColumnIndex(COLUMN_adresse_utilisateur)

                profil = ProfilUtilisateur(
                cursor.getString(codeIndex),
                cursor.getString(imageIndex),
                cursor.getString(nomIndex),
                cursor.getString(prenomIndex),
                cursor.getString(telephoneIndex),
                cursor.getString(emailIndex),
                cursor.getString(adresseIndex)
            )
        }
        cursor.close()
        return profil ?: throw NoSuchElementException("No user found in the database")
    }


    @SuppressLint("Range")
    fun getTableColumns(): List<String> {
        val columns = mutableListOf<String>()
        val db = readableDatabase

        // Query to get table information from sqlite_master
        val query = "PRAGMA table_info('Utilisateur');"
        val cursor: Cursor = db.rawQuery(query, null)

        // Iterate through the cursor to get column names
        while (cursor.moveToNext()) {
            val columnName = cursor.getString(cursor.getColumnIndex("name"))
            columns.add(columnName)
        }

        cursor.close()
        return columns
    }




}

