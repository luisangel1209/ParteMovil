package mx.fei.prointesomovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_consultas.*
import kotlinx.android.synthetic.main.activity_dietas.*
import mx.fei.prointesomovil.adaptador.AdaptadorListaConsultas
import mx.fei.prointesomovil.adaptador.AdaptadorListaDietas
import mx.fei.prointesomovil.pojos.Consultas
import mx.fei.prointesomovil.util.Constantes

class ConsultasActivity : AppCompatActivity() {

    private var consultas = ArrayList<Consultas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultas)
        title = "Mis consultas"
        descargarListaWS()
    }

    fun descargarListaWS(){
        val bundle = intent.extras
        val idPaciente = bundle?.getInt("idpaciente")
        Ion.with(this@ConsultasActivity)
            .load("POST", Constantes.URL_WS+"ProyectoIntegracion/ws/fitNutrition/consultaPaciente")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("idPaciente", idPaciente.toString())
            .asString()
            .setCallback { e, result ->
                if (e != null){
                    e.printStackTrace()
                    Toast.makeText(this@ConsultasActivity, e.message, Toast.LENGTH_LONG).show()
                }else{
                    Log.d("RESPUESTA WS", result)
                    val gson = Gson()
                    val arrType = object: TypeToken<ArrayList<Consultas>>() {}.type
                    consultas = gson.fromJson(result, arrType)
                    cargaElementosLista()
                }
            }
    }

    fun cargaElementosLista(){
        val adaptadorConsulta = AdaptadorListaConsultas()
        adaptadorConsulta.consulta = consultas
        //adaptadorConsulta.listener = this
        val layoutManager = LinearLayoutManager(this@ConsultasActivity)
        //listConsultas.layoutManager = layoutManager
        //listConsultas.adapter = adaptadorConsulta
    }

}
