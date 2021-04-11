
fun main() {

    val mt1 = MatrixCreator.fromRows(doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0))
    val mt2 = MatrixCreator.transpose(mt1)
    println(mt1)
    println(mt2)
    println(mt1 * mt2)
    println(mt2 * mt1)
    println(mt2 * mt1 * mt2)

    // а теперь умножим что-нибудь большое


//    var mt3 = MatrixCreator.fromFlatArray(2, 4,
//        doubleArrayOf(
//            1.0,  5.0,  9.0,  12.0,
//            2.0,  6.0,  10.0, 14.0
//        )
//    )
//    println(mt2)
//
//    var mt4 = MatrixCreator.transpose(mt2)
//    println(mt2 * MatrixCreator.transpose(mt2))

}
