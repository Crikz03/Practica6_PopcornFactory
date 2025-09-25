package elizalde.chris.popcornfacotry

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class ResumenCompra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resumen_compra)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("cliente_nombre")
        val asiento = intent.getIntExtra("cliente_asiento", -1)
        val pelicula = intent.getStringExtra("pelicula")
        val imagenPelicula = intent.getIntExtra("pelicula_imagen", -1)
        val tipoPago = intent.getStringExtra("tipoPago")

        val ivPeliculaImagen: ImageView = findViewById(R.id.iv_pelicula_imagen)
        val tvCliente: TextView= findViewById(R.id.tv_cliente)
        val tvPelicula: TextView = findViewById(R.id.tv_pelicula)
        val tvAsiento: TextView = findViewById(R.id.tv_asiento)
        val tvTipoPago: TextView = findViewById(R.id.tv_tipoPago)

        tvCliente.text = nombre
        tvPelicula.text = pelicula
        tvAsiento.text = asiento.toString()
        tvTipoPago.text = tipoPago
        ivPeliculaImagen.setImageResource(imagenPelicula)
    }
}