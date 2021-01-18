package mx.fei.prointesomovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_informacion_consulta.*
import mx.fei.prointesomovil.pojos.Consultas

class InformacionConsultaActivity : AppCompatActivity() {

    private var datosEdicion = ""
    private var isEdicion = false
    private var consultaVisualizacion : Consultas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_dietas)
        title = "Detalle dieta"
        isEdicion = intent.getBooleanExtra("isEdicion", false)
        intent.getStringExtra("datos")?.let {
            datosEdicion = it
            cargaDatosVisualizacion()
        }
    }

    fun cargaDatosVisualizacion(){
        val gson = Gson()
        consultaVisualizacion = gson.fromJson(datosEdicion, Consultas::class.java)
        textPeso.text = "El Peso es: " + consultaVisualizacion!!.peso
        textTalla.text = "La talla es: " + consultaVisualizacion!!.talla
        textIMC.text = "El IMC es: " + consultaVisualizacion!!.imc
        textObservaciones.text = "Las observaciones son: " + consultaVisualizacion!!.observaciones
    }

}
