
fun main() {

    val mt1 = MatrixCreator.fromRows(doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0))
    val mt2 = MatrixCreator.transpose(mt1)
    println(mt1)
    println(mt2)
    println(mt1 * mt2)
    println(mt2 * mt1)
    println(mt2 * mt1 * mt2)

    println("we are going to multiply something big")

    //random big matrices
    val mt3 = MatrixCreator.randomElementUniformMatrix(1000, 1000, 0.0, 1.0)
    val mt4 = MatrixCreator.randomElementUniformMatrix(1000, 1000, 0.0, 1.0)

    val mt5: D2DoubleMatrix
    val mt6: D2DoubleMatrix

    var timeDelta = kotlin.system.measureTimeMillis {
        mt5 = mt3 * mt4
    }
    println("time elapsed: ${timeDelta / 1000.0} s")

    println("now do it manually")
    timeDelta = kotlin.system.measureTimeMillis {
        mt6 = MatrixCreator.zero(1000, 1000)
        for (i in 0 until mt6.shape().first) {
            for (j in 0 until mt6.shape().second) {
                var aij = 0.0
                for (k in 0 until mt4.shape().first) {
                    aij += mt3.numberAt(i, k) * mt4.numberAt(k, j)
                }
                mt6.setAt(i, j, aij)
            }

        }
    }
    println("time elapsed: ${timeDelta / 1000.0}")

    println("matrix is too large, but we can inspect little piece")

    println(MatrixCreator.slice(mt5, 100, 105, 100, 105))
    println(MatrixCreator.slice(mt6, 100, 105, 100, 105))
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
