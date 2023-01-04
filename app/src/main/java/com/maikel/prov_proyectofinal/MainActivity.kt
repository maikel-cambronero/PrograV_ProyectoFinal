package com.maikel.prov_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title="Sistema Planillas"
        setUp()
    }

    private fun setUp()
    {

        SingUpButton.setOnClickListener{
            if (editTextTextEmailAddress.text.isNotEmpty() && editTextTextPassword.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextTextEmailAddress.text.toString(),
                editTextTextPassword.text.toString()).addOnCompleteListener(){
                    if(it.isSuccessful)
                    {
                        showMenu()
                    }
                    else
                    {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al autenticar usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showMenu()
    {
        val homeIntent = Intent(this,MenuActivity::class.java).apply{
        }
        startActivity(homeIntent)
    }
}

