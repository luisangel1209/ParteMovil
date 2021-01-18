package mx.fei.prointesomovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mx.fei.prointesomovil.pojos.Citas

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Principal"
        val bundle = intent.extras
        val nombre = bundle?.getString("Nombre")
        val idpaciente = bundle?.getInt("idPaciente")
        textBienvenido.setText("Bienvenido "+nombre)
        informacion.setOnClickListener {
            irInfo()
        }
        citas.setOnClickListener {
            citas()
        }
        dietas.setOnClickListener {
            dietas()
        }
    }

    fun irInfo(){
        val bundle = intent.extras
        val user = bundle?.getString("Usuario")
        val contra = bundle?.getString("Contra")
        val i = Intent(this@MainActivity, InformacionPacienteActivity::class.java)
        i.putExtra("Usu", user)
        i.putExtra("Con", contra)
        startActivity(i)
        //this.finish()
    }

    fun citas(){
        val bundle = intent.extras
        val idpaciente = bundle?.getInt("idPaciente")
        val i = Intent(this@MainActivity, CitasActivity::class.java)
        i.putExtra("idpaciente", idpaciente)
        startActivity(i)
    }

    fun dietas(){
        val bundle = intent.extras
        val idpaciente = bundle?.getInt("idPaciente")
        val i = Intent(this@MainActivity, DietasActivity::class.java)
        i.putExtra("idpaciente", idpaciente)
        startActivity(i)
    }
}
