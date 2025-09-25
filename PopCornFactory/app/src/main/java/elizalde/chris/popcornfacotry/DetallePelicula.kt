package elizalde.chris.popcornfacotry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val iv_pelicula_imagen: ImageView = findViewById(R.id.iv_pelicula_imagen)
        val tv_nombre_pelicula: TextView = findViewById(R.id.tv_nombre_pelicula)
        val tv_pelicula_desc: TextView = findViewById(R.id.tv_pelicula_desc)
        val seatLeft: TextView = findViewById(R.id.seatLeft)
        val buyTickets: Button = findViewById(R.id.buyTickets)

        val bundle = intent.extras
        var nseats = 0
        var id = -1

        if (bundle != null) {
            nseats = bundle.getInt("numberSeats")
            id = bundle.getInt("pos")
            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            tv_nombre_pelicula.setText(bundle.getString("titulo"))
            tv_pelicula_desc.setText(bundle.getString("sinopsis"))
            seatLeft.setText("$nseats seats available")

        }
        if (nseats == 0) {
            buyTickets.isEnabled = false
        } else {
            buyTickets.isEnabled = true
            buyTickets.setOnClickListener {
                var intent = Intent(this, SeatSelection::class.java)

                intent.putExtra("id",id)
                intent.putExtra("name", tv_nombre_pelicula.text)
                intent.putExtra("header", bundle?.getInt("header"))

                startActivity(intent)
            }
        }
    }
}