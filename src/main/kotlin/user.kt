fun main() {

//    val A: Pair<Int, Int> = Pair(200, 300)
//    val B: Pair<Int, Int> = Pair(200, 300)
//    print(A == B)

//    var mt = MatrixCreator.fromRows(doubleArrayOf(1.0, 2.0),
//                                    doubleArrayOf(3.0, 4.0))
//    print(MatrixCreator.embed(mt, 3, 5))

    var mt1 = MatrixCreator.fromFlatArray(4, 4,
        doubleArrayOf(
            1.0,  2.0,  3.0,  4.0,
            5.0,  6.0,  7.0,  8.0,
            9.0,  10.0, 11.0, 12.0,
            13.0, 14.0, 15.0, 16.0
        )
    )
    var mt2 = MatrixCreator.fromFlatArray(4, 4,
        doubleArrayOf(
            1.0,  5.0,  9.0,  12.0,
            2.0,  6.0,  10.0, 14.0,
            3.0,  6.0,  11.0, 15.0,
            4.0,  8.0,  12.0, 16.0
        )
    )
//    println(Strassen().multiply(mt1, mt2))
//    println(DummyMultiplier().multiply(mt1, mt2))

    mt1 = MatrixCreator.randomElementUniformMatrix(1001, 1001, 0.0, 1.0)
    mt2 = MatrixCreator.randomElementUniformMatrix(1001, 1001, 0.0, 1.0)
    println(MatrixCreator.slice(Strassen().multiply(mt1, mt2), 0, 10, 0, 10))
    println(MatrixCreator.slice(DummyMultiplier().multiply(mt1, mt2), 0, 10, 0, 10))
}