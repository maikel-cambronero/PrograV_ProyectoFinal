package com.maikel.prov_proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_empleados.*
import kotlinx.android.synthetic.main.activity_planilla.*
import kotlinx.android.synthetic.main.activity_registrar.*

class PlanillaActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planilla)

        title="Sistema Planillas"
        getEmpleado()
        pagar()
    }

    private fun getEmpleado()
    {

        buscarButton.setOnClickListener{
            db.collection("Empleados").document(editTextTextBuscar.text.toString()).get().addOnSuccessListener { documento ->
                if(documento.exists())
                {
                    ViewNombre.setText(documento.data?.get("NombreEmpleado").toString())
                    ViewCedula.setText(documento.data?.get("NumCedula").toString())
                    ViewSalario.setText(documento.data?.get("SalEmpleado").toString())
                }
                else
                {
                    ShowAlert()
                }
            }
        }
    }

    private fun pagar()
    {
        CalcularButton.setOnClickListener {
            var datoSalario : String = ViewSalario.text.toString()
            var datoHoras : String = editTextTextCanHoras.text.toString()

            var salario : Int = datoSalario.toInt()
            var horas : Int = datoHoras.toInt()

            var total: Int = salario*horas
            var datoTotal : String = total.toString()

            ViewDeuda.text = "₡\u200E" + datoTotal.toString()
        }

        PagarButton.setOnClickListener {

            db.collection("Pagos").document(editTextTextBuscar.text.toString()).set(
                hashMapOf("NombreEmpleado" to ViewNombre.text.toString(),
                    "NumCedula" to ViewCedula.text.toString(),
                    "SalEmpleado" to ViewSalario.text.toString(),
                    "HraEmpleado" to editTextTextCanHoras.text.toString(),
                    "Total" to ViewDeuda.text.toString())
            )
            ShowGreat()
        }
    }

    private fun ShowGreat()
    {
        val buider = AlertDialog.Builder(this)
        buider.setTitle("Felicidades")
        buider.setMessage("El pago se ha realizado con exito y los dato se guardaron correctamente")
        buider.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = buider.create()
        dialog.show()
    }

    private fun ShowAlert()
    {
        val buider = AlertDialog.Builder(this)
        buider.setTitle("Error")
        buider.setMessage("Lo sentimos, no se encontró al empleado, por favor intente nuevamente")
        buider.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = buider.create()
        dialog.show()
    }
}
