package com.maikel.prov_proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        title="Sistema Planillas"
        mainMenu()
    }

    private fun mainMenu()
    {
        RegistrarButton.setOnClickListener{
            val homeIntent = Intent(this,RegistrarActivity::class.java).apply{
            }
            startActivity(homeIntent)
        }

        EmpleadosButton.setOnClickListener{
            val homeIntent = Intent(this,EmpleadosActivity::class.java).apply{
            }
            startActivity(homeIntent)
        }

        PlanillaButton.setOnClickListener{
            val homeIntent = Intent(this,PlanillaActivity::class.java).apply{
            }
            startActivity(homeIntent)
        }

        PagosButton.setOnClickListener{
            val homeIntent = Intent(this,PagosActivity::class.java).apply{
            }
            startActivity(homeIntent)
        }
        
    }
}