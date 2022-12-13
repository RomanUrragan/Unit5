class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = Int.MAX_VALUE,
    val placeholder: PlaceHolder = PlaceHolder(),
    val canPublishFreeCopy: Boolean = true,
    val editMode: String = "all",

) {

}

class PlaceHolder {
    val placeholder: String = "Запись доступна пользователям, оформившим Donut"
}
