package mx.fei.prointesomovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_informacion_paciente.*
import mx.fei.prointesomovil.pojos.Mensaje
import mx.fei.prointesomovil.pojos.Pacientes
import mx.fei.prointesomovil.util.Constantes

class InformacionPacienteActivity : AppCompatActivity() {

    private var pacienteEdicion : Pacientes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_paciente)
        title = "Mi Información Personal"
        val bundle = intent.extras
        val user = bundle?.getString("Usu")
        val contra = bundle?.getString("Con")
        if (user != null) {
            if (contra != null) {
                loginSW(user, contra)
            }
        }

        Actualizar.setOnClickListener {
            editar()
        }

    }

    fun loginSW(username : String, password : String){
        Ion.getDefault(this@InformacionPacienteActivity).conscryptMiddleware.enable(false)
        Ion.with(this@InformacionPacienteActivity)
            .load("POST", Constantes.URL_WS+"ProyectoIntegracion/ws/fitNutrition/loginPaciente")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("usuario", username)
            .setBodyParameter("contraseña", password)
            .asString()
            .setCallback { e, result ->
                if(e != null){
                    e.printStackTrace()
                    Toast.makeText(this@InformacionPacienteActivity, "Error: "+e.message, Toast.LENGTH_LONG).show()
                }else{
                    val gson = Gson()
                    val datos : Pacientes = gson.fromJson(result, Pacientes::class.java)
                    if(datos.peso == null) {
                        Toast.makeText(this@InformacionPacienteActivity, "Error al cargar tu información", Toast.LENGTH_LONG).show()
                    }else{
                        Log.i("RESPUESTA WS", result)
                        pacienteEdicion = gson.fromJson(result, Pacientes::class.java)
                        val contra : String? = datos.contraseña
                        textPeso.setText(""+datos.peso)
                        textEstatura.setText(""+datos.estatura)
                        textTalla.setText(""+datos.talla)
                        textEmail.setText(datos.email)
                        textTele.setText(""+datos.tel)
                        textDomicilio.setText(datos.domicilio)
                        textUsuario.setText(datos.usuario)
                        textContra.setText(contra)
                        textFoto.setText(datos.paciente_foto)
                        //Toast.makeText(this@InformacionPacienteActivity, "datos "+result, Toast.LENGTH_LONG).show()
                        //irPrincipal(username, password)
                    }
                }
            }
    }

    fun editar(){
        Ion.with(this@InformacionPacienteActivity)
            .load("PUT", Constantes.URL_WS+"ProyectoIntegracion/ws/fitNutrition/actualizarPaciente")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("idPaciente", pacienteEdicion!!.idPaciente.toString())
            .setBodyParameter("peso", textPeso.text.toString())
            .setBodyParameter("estatura", textEstatura.text.toString())
            .setBodyParameter("talla", textTalla.text.toString())
            .setBodyParameter("email", textEmail.text.toString())
            .setBodyParameter("tel", textTele.text.toString())
            .setBodyParameter("domicilio", textDomicilio.text.toString())
            .setBodyParameter("usuario", textUsuario.text.toString())
            .setBodyParameter("contraseña", textContra.text.toString())
            .setBodyParameter("paciente_foto", textFoto.text.toString())
            .asString()
            .setCallback { e, result ->
                if(e != null){
                    e.printStackTrace()
                    Toast.makeText(this@InformacionPacienteActivity, "Error: "+e.message, Toast.LENGTH_LONG).show()
                }else{
                    Log.d("RESPUESTA WS", result)
                    val gson = Gson()
                    val msj : Mensaje = gson.fromJson(result, Mensaje::class.java)
                    if(msj.error!!){
                        Toast.makeText(this@InformacionPacienteActivity, ""+msj.mensaje, Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@InformacionPacienteActivity, "Información editada con éxito", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            }
    }
}
