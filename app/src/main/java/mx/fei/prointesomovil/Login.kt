package mx.fei.prointesomovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_login.*
import mx.fei.prointesomovil.pojos.Mensaje
import mx.fei.prointesomovil.pojos.Pacientes
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
                    val datos : Pacientes = gson.fromJson(result, Pacientes::class.java)
                    if(datos.peso == null) {
                        Toast.makeText(this@Login, ""+datos.usuario, Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@Login, "Bienvenido "+datos.nombre, Toast.LENGTH_LONG).show()
                        datos.nombre?.let { irPrincipal(username, password, it) }
                    }
                }
            }
        }

    fun irPrincipal(username: String,password: String,nombre: String) {
        //startActivity(Intent(this@Login, MainActivity::class.java))
        //this.finish()
        val i = Intent(this@Login, MainActivity::class.java)
        i.putExtra("Usuario", username)
        i.putExtra("Contra", password)
        i.putExtra("Nombre", nombre)
        startActivity(i)
        this.finish()

    }

}
