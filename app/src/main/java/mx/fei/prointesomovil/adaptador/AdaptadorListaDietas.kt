package mx.fei.prointesomovil.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.fei.prointesomovil.Interfaz.ClicLista
import mx.fei.prointesomovil.R
import mx.fei.prointesomovil.pojos.Dietas

class AdaptadorListaDietas : RecyclerView.Adapter<AdaptadorListaDietas.ViewHolder>() {

    var listener : ClicLista? = null
    var dietas = ArrayList<Dietas>()
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card  = LayoutInflater.from(parent.context).inflate(R.layout.adapter_lista_dietas, parent, false)
        return ViewHolder(card)
    }

    override fun getItemCount(): Int {
        return dietas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dieta = dietas.get(position)

        holder.tvNumeroDieta.text = "Número dieta: "+dieta.numero_dieta
        holder.tvNombre.text = "Nombre alimento: "+dieta.tipo

        holder.btEditar.setOnClickListener {
            listener!!.clicElementoEditar(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var tvNumeroDieta : TextView
        var tvNombre : TextView
        var btEditar : Button

        init {
            tvNumeroDieta = itemView.findViewById(R.id.tvNumeroDieta)
            tvNombre = itemView.findViewById(R.id.tvNombre)
            btEditar = itemView.findViewById(R.id.btEditar)
        }
    }
}