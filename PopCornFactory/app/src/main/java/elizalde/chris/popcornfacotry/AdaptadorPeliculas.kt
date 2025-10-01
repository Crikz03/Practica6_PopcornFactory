package elizalde.chris.popcornfacotry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPelículas(val peliculas_list: List<Pelicula>, val onItemClick: (Pelicula, Int) -> Unit
) : RecyclerView.Adapter<AdaptadorPelículas.PeliculaViewHolder>() {

    class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagen_pelicula: ImageView = itemView.findViewById(R.id.iv_pelicula)
        var título: TextView = itemView.findViewById(R.id.iv_titulo)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pelicula, parent, false)
        return PeliculaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula: Pelicula = peliculas_list[position]
        holder.imagen_pelicula.setImageResource(pelicula.Image)
        holder.título.text = pelicula.titulo

        holder.itemView.setOnClickListener {
            onItemClick(pelicula, position)
        }
    }

    override fun getItemCount(): Int {
        return peliculas_list.size
    }
}