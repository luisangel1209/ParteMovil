package mx.fei.prointesomovil.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.fei.prointesomovil.Interfaz.ClicLista
import mx.fei.prointesomovil.R
import mx.fei.prointesomovil.pojos.Citas

class AdaptadorListaCitas : RecyclerView.Adapter<AdaptadorListaCitas.ViewHolder>() {

    var listener : ClicLista? = null
    var citas = ArrayList<Citas>()
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card  = LayoutInflater.from(parent.context).inflate(R.layout.adapter_lista_citas, parent, false)
        return ViewHolder(card)
    }

    override fun getItemCount(): Int {
        return citas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cita = citas.get(position)

        holder.tvCodigo.text = "Fecha: "+cita.fecha_cita
        holder.tvNombre.text = "Hora: "+cita.hora_cita

        holder.btEditar.setOnClickListener {
            listener!!.clicElementoEditar(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var tvCodigo : TextView
        var tvNombre : TextView
        var btEditar : Button

        init {
            tvCodigo = itemView.findViewById(R.id.tvCodigo)
            tvNombre = itemView.findViewById(R.id.tvNombre)
            btEditar = itemView.findViewById(R.id.btEditar)
        }
    }
}