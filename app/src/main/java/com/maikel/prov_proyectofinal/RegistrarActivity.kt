package com.maikel.prov_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registrar.*

class RegistrarActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        title="Sistema Planillas"

        saveEmpleados()
        clean()
        back()
    }

    private fun saveEmpleados()
    {
        GuardarButton.setOnClickListener{
            var codigo:String  = editTextTexCodigo.text.toString() // Este es el ID de los documentos.

           db.collection("Empleados").document(codigo).set(
               hashMapOf("NombreEmpleado" to editTextTextName.text.toString(),
               "NumCedula" to editTextTextCedula.text.toString(),
               "SalEmpleado" to editTextTextSalario.text.toString(),
               "DepEmpleado" to editTextTextDepartamento.text.toString())
           )
            ShowGreat()
        }
    }

    private fun clean()
    {
        LimpiarButton.setOnClickListener {
            editTextTexCodigo.text.clear()
            editTextTextName.text.clear()
            editTextTextCedula.text.clear()
            editTextTextSalario.text.clear()
            editTextTextDepartamento.text.clear()
        }
    }

    private fun back()
    {
        VolverButton.setOnClickListener{
            val homeIntent = Intent(this,MenuActivity::class.java).apply{
            }
            startActivity(homeIntent)
        }
    }

    private fun ShowGreat()
    {
        val buider = AlertDialog.Builder(this)
        buider.setTitle("Felicidades")
        buider.setMessage("Los datos se han guardado exitosamente!")
        buider.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = buider.create()
        dialog.show()
    }
}