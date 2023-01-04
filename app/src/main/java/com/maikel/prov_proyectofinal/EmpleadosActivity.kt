package com.maikel.prov_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_empleados.*
import kotlinx.android.synthetic.main.activity_empleados.VolverButton

class EmpleadosActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleados)

        title="Sistema Planillas"

        getDatos()
        back()
    }


    fun getDatos() {
        var datos = ""
        VerDatosButton.setOnClickListener {

            db.collection("Empleados").get().addOnSuccessListener { resultado ->
                for (documento in resultado) {
                    val NomEmpleado = documento["NombreEmpleado"].toString()
                    val NumCedula = documento["NumCedula"].toString()
                    val SalEmpleado = documento["SalEmpleado"].toString()
                    val DepEmpleado = documento["DepEmpleado"].toString()

                    datos += """Código de Empleado ${documento.id}
                        |Nombre de Empleado:  ${NomEmpleado} 
                        |Número de Cédula:  ${NumCedula} 
                        |Salario de Empleado:  $SalEmpleado
                        |Departamento de Empleado: ${DepEmpleado}
                        |
                        |""".trimMargin()

                    textDatos.text = datos
                }
            }
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
}




