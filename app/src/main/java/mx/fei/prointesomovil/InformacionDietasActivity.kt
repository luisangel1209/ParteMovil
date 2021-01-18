package mx.fei.prointesomovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_informacion_dietas.*
import mx.fei.prointesomovil.pojos.Dietas

class InformacionDietasActivity : AppCompatActivity() {

    private var datosEdicion = ""
    private var isEdicion = false
    private var dietaVisualizacion : Dietas? = null

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

    fun cargaDatosVisualizacion() {
        val gson = Gson()
        dietaVisualizacion = gson.fromJson(datosEdicion, Dietas::class.java)
        tvNumDieta.text = "Número dieta: " + dietaVisualizacion!!.numero_dieta
        tvNombre.text = "Hora: " + dietaVisualizacion!!.tipo
        tvCantidad.text = "Cantidad: " + dietaVisualizacion!!.cantidad
        tvHora.text = "Hora: " + dietaVisualizacion!!.hora_dia
        tvCalorias.text = "Calorías: " + dietaVisualizacion!!.calorias_dieta
        tvObserva.text = "Observaciones: " + dietaVisualizacion!!.observaciones
    }
}
