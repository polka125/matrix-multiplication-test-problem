/**
 * Multiplier tries to avoid making big steps over array
 */
class DummyCacheFriendly : multiplyStrategy {
    private companion object {

        fun pairIndexToFlatIndex(row: Int, column: Int, shape: Pair<Int, Int>) : Int {
            return row * shape.second + column
        }
    }

    /**
     * Algorithm description: denote R^T as transposition of R
     * Then (L * R)[i][j] = sum L[i][k] * R[i][k]
     * @param L left matrix to multipy
     * @param R right matric to multiply
     * @return result of multiplication
     */
    override fun multiply(L: D2DoubleMatrix, R: D2DoubleMatrix): D2DoubleMatrix {
        val RTansposed = MatrixCreator.transpose(R)
        val result = MatrixCreator.zero(L.shape().first, R.shape().second)
        for (i in 0 until result.shape().first) {
            for (j in 0 until result.shape().second) {
                var aij = 0.0
                for (k in 0 until L.shape().second) {
                    aij += L.numberAt(i, k) * RTansposed.numberAt(j, k)
//                    aij += entryL[pairIndexToFlatIndex(i, k, L.shape())] *
//                            entryRTansposed[pairIndexToFlatIndex(j, k, Pair(R.shape().second, R.shape().first))]
                }
                result.setAt(i, j, aij)
            }
        }
        return result
    }
}