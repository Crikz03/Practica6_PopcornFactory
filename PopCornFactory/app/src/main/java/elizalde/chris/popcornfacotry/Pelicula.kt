package elizalde.chris.popcornfacotry

data class Pelicula(var titulo: String,
                    var Image: Int,
                    var header: Int,
                    var sinopsis: String, var seats: ArrayList<Cliente>) {
}