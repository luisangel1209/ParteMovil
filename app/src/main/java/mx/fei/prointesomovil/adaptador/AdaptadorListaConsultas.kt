package mx.fei.prointesomovil.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.channels.consumesAll
import mx.fei.prointesomovil.Interfaz.ClicLista
import mx.fei.prointesomovil.R
import mx.fei.prointesomovil.pojos.Consultas

class AdaptadorListaConsultas : RecyclerView.Adapter<AdaptadorListaConsultas.ViewHolder>() {

    var listener: ClicLista? = null
    var consulta = ArrayList<Consultas>()
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_lista_consultas, parent, false)
        return ViewHolder(card)
    }

    override fun getItemCount(): Int {
        return consulta.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val consulta = consulta.get(position)

        holder.textPeso.text = "El peso es: " + consulta.peso
        holder.textTalla.text = "La talla es: " + consulta.talla
        holder.textIMC.text = "El IMC es: " + consulta.imc
        holder.textObservaciones.text = "Las observaciones son: " + consulta.observaciones

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textPeso: TextView
        var textTalla: TextView
        var textIMC: TextView
        var textObservaciones: TextView

        init {
            textPeso = itemView.findViewById(R.id.textPeso)
            textTalla = itemView.findViewById(R.id.textTalla)
            textIMC = itemView.findViewById(R.id.textIMC)
            textObservaciones = itemView.findViewById(R.id.textObservaciones)

        }
    }
}