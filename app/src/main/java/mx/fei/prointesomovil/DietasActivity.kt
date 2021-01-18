package mx.fei.prointesomovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_dietas.*
import mx.fei.prointesomovil.Interfaz.ClicLista
import mx.fei.prointesomovil.adaptador.AdaptadorListaCitas
import mx.fei.prointesomovil.adaptador.AdaptadorListaDietas
import mx.fei.prointesomovil.pojos.Citas
import mx.fei.prointesomovil.pojos.Dietas
import mx.fei.prointesomovil.util.Constantes

class DietasActivity : AppCompatActivity(), ClicLista {

    private var dietas = ArrayList<Dietas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dietas)
        title = "Mis Dietas"
        descargaListaWS()
    }

    fun descargaListaWS(){
        val bundle = intent.extras
        val idPaciente = bundle?.getInt("idpaciente")
        Ion.with(this@DietasActivity)
            .load("POST", Constantes.URL_WS+"ProyectoIntegracion/ws/fitNutrition/dietaPaciente")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("idPaciente", idPaciente.toString())
            .asString()
            .setCallback { e, result ->
                if (e != null){
                    e.printStackTrace()
                    Toast.makeText(this@DietasActivity, e.message, Toast.LENGTH_LONG).show()
                }else{
                    Log.d("RESPUESTA WS", result)
                    val gson = Gson()
                    val arrType = object: TypeToken<ArrayList<Dietas>>() {}.type
                    dietas = gson.fromJson(result, arrType)
                    cargaElementosLista()
                }
            }
    }

    fun cargaElementosLista(){
        val adaptadorDietas = AdaptadorListaDietas()
        adaptadorDietas.dietas = dietas
        adaptadorDietas.listener = this
        val layoutManager = LinearLayoutManager(this@DietasActivity)
        listaDietas.layoutManager = layoutManager
        listaDietas.adapter = adaptadorDietas
    }

    override fun clicElementoEditar(posicion: Int) {
        val edicion = dietas[posicion]
        val gson = Gson()
        val datoEdicion = gson.toJson(edicion)
        val intent = Intent(this@DietasActivity, InformacionDietasActivity::class.java)
        intent.putExtra("isEdicion", true)
        intent.putExtra("datos", datoEdicion)
        startActivity(intent)
    }

    override fun clicElementoEliminar(posicion: Int) {
        TODO("Not yet implemented")
    }
}
