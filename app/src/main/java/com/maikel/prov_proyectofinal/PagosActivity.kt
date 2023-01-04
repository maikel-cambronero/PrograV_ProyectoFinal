package com.maikel.prov_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_pagos.*

class PagosActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagos)

        title="Sistema Planillas"

        getDatos()
        back()
    }

    fun getDatos() {
        var datos = ""
        VerPagosbutton.setOnClickListener {

            db.collection("Pagos").get().addOnSuccessListener { resultado ->
                for (documento in resultado) {
                    val NomEmpleado = documento["NombreEmpleado"].toString()
                    val NumCedula = documento["NumCedula"].toString()
                    val SalEmpleado = documento["SalEmpleado"].toString()
                    val HraEmpleado = documento["HraEmpleado"].toString()
                    val Total = documento["Total"].toString()

                    datos += """Código de Empleado ${documento.id}
                        |Nombre de Empleado:  ${NomEmpleado} 
                        |Número de Cédula:  ${NumCedula} 
                        |Salario de Empleado:  $SalEmpleado
                        |Horas Trabajadas: ${HraEmpleado}
                        |Total a pagar: ${Total}
                        |
                        |""".trimMargin()

                    textDatos.text = datos
                }
            }
        }
    }

    private fun back()
    {
        Menubutton.setOnClickListener{
            val homeIntent = Intent(this,MenuActivity::class.java).apply{
            }
            startActivity(homeIntent)
        }
    }
}