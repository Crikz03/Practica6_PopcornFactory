package elizalde.chris.popcornfacotry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre: EditText = findViewById(R.id.etNombreCliente)

        val tittle: TextView = findViewById(R.id.tittleSeats)
        var posMovie = -1
        var headerImage = -1
        val bundle=intent.extras

        if(bundle != null){
            tittle.setText(bundle.getString("name"))
            headerImage = bundle.getInt("header", -1)
        }

        val confirm: Button = findViewById(R.id.confirm)

        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        confirm.setOnClickListener {
            val nombre = etNombre.text.toString()

            val seatId = when {
                row1.checkedRadioButtonId != -1 -> row1.checkedRadioButtonId
                row2.checkedRadioButtonId != -1 -> row2.checkedRadioButtonId
                row3.checkedRadioButtonId != -1 -> row3.checkedRadioButtonId
                row4.checkedRadioButtonId != -1 -> row4.checkedRadioButtonId
                else -> -1
            }

            if (nombre.isBlank()) {
                Toast.makeText(this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (seatId == -1) {
                Toast.makeText(this, "Selecciona un asiento", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val asientoSeleccionado = findViewById<TextView>(seatId).text.toString()



            val cliente = Cliente(nombre, "Efectivo", asientoSeleccionado.toInt())

            val intent = Intent(this, ResumenCompra::class.java)
            intent.putExtra("cliente_nombre", cliente.nombre)
            intent.putExtra("cliente_asiento", cliente.asiento)
            intent.putExtra("pelicula", tittle.text.toString())
            intent.putExtra("pelicula_imagen", headerImage)
            intent.putExtra("tipoPago", cliente.tipoPago)


            startActivity(intent)
            Toast.makeText(this, "Enjoy the movie! :D", Toast.LENGTH_LONG).show()
        }



        row1.setOnCheckedChangeListener(){group,checkedId ->
            if(checkedId>-1){
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row1.check(checkedId)
            }
        }

        row2.setOnCheckedChangeListener(){group,checkedId ->
            if(checkedId>-1){
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row2.check(checkedId)
            }
        }

        row3.setOnCheckedChangeListener(){group,checkedId ->
            if(checkedId>-1){
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()

                row3.check(checkedId)
            }
        }

        row4.setOnCheckedChangeListener(){group,checkedId ->
            if(checkedId>-1){
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()

                row4.check(checkedId)
            }
        }

    }

}
