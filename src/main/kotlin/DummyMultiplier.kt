import multiplyStrategy.Companion.isCompatibleShapes

class DummyMultiplier : multiplyStrategy {

    private companion object {

        fun pairIndexToFlatIndex(row: Int, column: Int, shape: Pair<Int, Int>) : Int {
            return row * shape.second + column
        }
    }

    override fun multiply(L: D2DoubleMatrix, R: D2DoubleMatrix): D2DoubleMatrix {
        if (!isCompatibleShapes(L.shape(), R.shape())) {
            throw IllegalArgumentException()
        }

        val result = MatrixCreator.zero(L.shape().first, R.shape().second)
        for (i in 0 until result.shape().first) {
            for (j in 0 until result.shape().second) {
                var a_ij = 0.0
                for (k in 0 until L.shape().second) {
                    a_ij += L.numberAt(i, k) * R.numberAt(k, j)
                    //a_ij += entryA[pairIndexToFlatIndex(i, k, L.shape())] *
                    //        entryB[pairIndexToFlatIndex(k, j, R.shape())]
                }
                result.setAt(i, j, a_ij)
            }
        }
        return result
    }
}