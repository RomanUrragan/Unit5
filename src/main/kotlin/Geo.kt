import java.time.LocalDateTime

data class Geo(
    val type: String = "",
    val coordinates: String = "",
    val place: Place = Place()
) {

}

class Place(
    val id: Int = 0,
    val title: String = "",
    val latitude: Int = 0,
    val longitude: Int = 0,
    val created: Long = System.currentTimeMillis(),
    val icon: String = "",
    val checkins: Int = 0,
    val updated: Long = System.currentTimeMillis(),
    val type: String = "",
    val country: Int = 0,
    val city: Int = 0,
    val address: String = ""
) {

}
