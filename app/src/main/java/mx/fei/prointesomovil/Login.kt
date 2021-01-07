package mx.fei.prointesomovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_login.*
import mx.fei.prointesomovil.pojos.Mensaje
import mx.fei.prointesomovil.util.Constantes

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Inicio de Sesión"

        btIngresar.setOnClickListener {
            validaCampos()
        }
    }

    fun validaCampos() {
        val userName = etUsername.text.toString()
        val password = etPassword.text.toString()
        var isValido = true
        if (userName.isEmpty()) {
            isValido = false
            etUsername.setError("El usuario es requerido")
        }
        if (password.isEmpty()) {
            isValido = false
            etPassword.setError("El password es requerido")
        }
        if (isValido) {
            loginSW(userName, password)
        }
    }

    fun loginSW(username : String, password : String){
        Ion.getDefault(this@Login).conscryptMiddleware.enable(false)
        Ion.with(this@Login)
            .load("POST", Constantes.URL_WS+"ProyectoIntegracion/ws/fitNutrition/loginPaciente")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("usuario", username)
            .setBodyParameter("contraseña", password)
            .asString()
            .setCallback { e, result ->
                if(e != null){
                    e.printStackTrace()
                    Toast.makeText(this@Login, "Error: "+e.message, Toast.LENGTH_LONG).show()
                }else{
                    val gson = Gson()
                    val msj : Mensaje = gson.fromJson(result, Mensaje::class.java)
                    if(msj.error!!) {
                        Toast.makeText(this@Login, "NO jala"+msj.mensaje, Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@Login, "Bienvenido", Toast.LENGTH_LONG).show()
                        irPrincipal()
                    }
                }
            }
    }

    fun irPrincipal(){
        startActivity(Intent(this@Login, PrincipalActivity::class.java))
        this.finish()
    }

}
