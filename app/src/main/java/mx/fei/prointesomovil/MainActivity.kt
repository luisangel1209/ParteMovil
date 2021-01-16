package mx.fei.prointesomovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Principal"
        informacion.setOnClickListener {
            irInfo()
        }
        val bundle = intent.extras
        val nombre = bundle?.getString("Nombre")
        textBienvenido.setText("Bienvenido "+nombre)
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
}
