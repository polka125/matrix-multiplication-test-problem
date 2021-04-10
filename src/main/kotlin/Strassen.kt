import multiplyStrategy.Companion.isCompatibleShapes
import kotlin.math.min

class Strassen : multiplyStrategy {
    companion object {
        // if one of dimension is smaller then threshold, we switch to dummyMultilier
        // value of threshold is empirical and can be changed without damage
        private val strassenThreshold = 200

        /**
         * fit matrix into minimal matrix that has both even dimension
         * if matrix already has even dimensions, return it unchanged
         * (for sake of performance, we want to avoid unnecessary copying)
         */
        private fun fitIntoEvenShaped(A: D2DoubleMatrix): D2DoubleMatrix {
            var row = A.shape().first
            var column = A.shape().second

            if(row % 2 == 0 && column % 2 == 0) {
                return A
            }

            row = if(row % 2 == 0) row else row + 1
            column = if(row % 2 == 0) row else row + 1
            return MatrixCreator.embed(A, row, column)
        }

        private fun strassen(L: D2DoubleMatrix, R: D2DoubleMatrix): D2DoubleMatrix {
            if (minOf(L.shape().first, L.shape().second, R.shape().first, R.shape().second) <= strassenThreshold) {
                return DummyMultiplier().multiply(L, R)
            }
            val evenL = fitIntoEvenShaped(L)
            val evenR = fitIntoEvenShaped(R)

            //following variables coincides with variables in algorithm described here:
            //https://en.wikipedia.org/wiki/Strassen_algorithm#Algorithm
            // evenL = [[A11, A12],
            //          [A21, A22]]
            // evenR = [[B11, B12],
            //          [B21, B22]]
            val A11 = MatrixCreator.slice(
                evenL,
                0,
                evenL.shape().first / 2,
                0,
                evenL.shape().second / 2
            )
            val A12 = MatrixCreator.slice(
                evenL,
                0,
                evenL.shape().first / 2,
                evenL.shape().second / 2,
                evenL.shape().second
            )
            val A21 = MatrixCreator.slice(
                evenL,
                evenL.shape().first / 2,
                evenL.shape().first,
                0,
                evenL.shape().second / 2
            )
            val A22 = MatrixCreator.slice(
                evenL,
                evenL.shape().first / 2,
                evenL.shape().first,
                evenL.shape().second / 2,
                evenL.shape().second
            )
            val B11 = MatrixCreator.slice(
                evenR,
                0,
                evenR.shape().first / 2,
                0,
                evenR.shape().second / 2
            )
            val B12 = MatrixCreator.slice(
                evenR,
                0,
                evenR.shape().first / 2,
                evenR.shape().second / 2,
                evenR.shape().second
            )
            val B21 = MatrixCreator.slice(
                evenR,
                evenR.shape().first / 2,
                evenR.shape().first,
                0,
                evenR.shape().second / 2
            )
            val B22 = MatrixCreator.slice(
                evenR,
                evenR.shape().first / 2,
                evenR.shape().first,
                evenR.shape().second / 2,
                evenR.shape().second
            )


            val M1 = strassen(A11.plus(A22), B11.plus(B22))
            val M2 = strassen(A21.plus(A22), B11)
            val M3 = strassen(A11, B12.minus(B22))
            val M4 = strassen(A22, B21.minus(B11))
            val M5 = strassen(A11.plus(A12), B22)
            val M6 = strassen(A21.minus(A11), B11.plus(B12))
            val M7 = strassen(A12.minus(A22), B21.plus(B22))

            val C11 = M1.plus(M4).minus(M5).plus(M7)
            val C12 = M3.plus(M5)
            val C21 = M2.plus(M4)
            val C22 = M1.minus(M2).plus(M3).plus(M6)

            val result = MatrixCreator.zero(L.shape().first, R.shape().second)

            for (i in 0 until evenL.shape().first / 2) {
                for (j in 0 until evenR.shape().second / 2) {
                    result.setAt(i, j, C11.numberAt(i, j))
                }
            }

            for (i in 0 until evenL.shape().first / 2) {
                for (j in evenR.shape().second / 2 until min(evenR.shape().second, R.shape().second)) {
                    result.setAt(i, j, C12.numberAt(i, j - evenR.shape().second / 2))
                }
            }

            for (i in evenL.shape().first / 2 until min(evenL.shape().first, L.shape().first)) {
                for (j in 0 until evenR.shape().second / 2) {
                    result.setAt(i, j, C21.numberAt(i - evenL.shape().first / 2, j))
                }
            }

            for (i in evenL.shape().first / 2 until min(evenL.shape().first, L.shape().first)) {
                for (j in evenR.shape().second / 2 until min(evenR.shape().second, R.shape().second)) {
                    result.setAt(i, j, C22.numberAt(i - evenL.shape().first / 2, j - evenR.shape().second / 2))
                }
            }


            return result

        }

    }

    override fun multiply(L: D2DoubleMatrix, R: D2DoubleMatrix): D2DoubleMatrix {
        if(!isCompatibleShapes(L.shape(), R.shape())) {
            throw IllegalArgumentException()
        }
        assert(strassenThreshold >= 1)

        return strassen(L, R)
    }

}