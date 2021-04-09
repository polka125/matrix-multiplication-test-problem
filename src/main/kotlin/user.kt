fun main() {


    print (MatrixCreator.fromRows(
        doubleArrayOf(0.0, 1.0, 2.0),
        doubleArrayOf(1.0, 2.0, 3.0)
    ).multiplyTo(
        MatrixCreator.fromRows(
            doubleArrayOf(1.0),
            doubleArrayOf(2.0),
            doubleArrayOf(3.0)
        )
    )
    )
}