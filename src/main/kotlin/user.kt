fun main() {

    var mt = MatrixCreator.fromRows(doubleArrayOf(1.0, 2.0),
                                    doubleArrayOf(3.0, 4.0))
    print(MatrixCreator.embed(mt, 3, 5))
}