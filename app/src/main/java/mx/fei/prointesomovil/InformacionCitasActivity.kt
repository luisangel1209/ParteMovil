package mx.fei.prointesomovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_informacion_citas.*
import mx.fei.prointesomovil.pojos.Citas

class InformacionCitasActivity : AppCompatActivity() {

    private var datosEdicion = ""
    private var isEdicion = false
    private var citaVisualizacion : Citas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_citas)
        title = "Detalle cita"
        isEdicion = intent.getBooleanExtra("isEdicion", false)
        intent.getStringExtra("datos")?.let {
            datosEdicion = it
            cargaDatosVisualizacion()
        }
    }

    fun cargaDatosVisualizacion(){
        val gson = Gson()
        citaVisualizacion = gson.fromJson(datosEdicion, Citas::class.java)
        tvFecha.text = "Fecha: "+citaVisualizacion!!.fecha_cita
        tvHora.text = "Hora: "+citaVisualizacion!!.hora_cita
        tvObserva.text = "Observaciones: "+citaVisualizacion!!.observaciones
    }
}
