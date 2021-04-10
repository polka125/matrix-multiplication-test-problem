abstract class MatrixCreator {
    companion object {
        /**
         * creates matrix from Double arrays by stacking they together vertically
         * @param rows sequence of duuble array that has same sizes, separated by comma (or in *[] notation)
         * @return matrix with rows from argument
         */
        fun fromRows(vararg rows: DoubleArray): D2DoubleMatrix {
            //TODO: check rows has same sizes
            val rowNumber = rows.size
            val columnNumber = rows[0].size
            val answer = D2DoubleMatrix(rowNumber, columnNumber)
            for (i in 0 until rowNumber) {
                for (j in 0 until columnNumber) {
                    answer.setAt(i, j, rows[i][j])
                }
            }
            return answer
        }
        fun fromColumns(vararg columns: DoubleArray): D2DoubleMatrix {
            TODO()
        }

        /**
         * creates matrix filled with 0.0 of shape rows x columns
         * @param rows rows number
         * @param columns column number
         * @return matrix filled with 0.0
         */
        fun zero(rows: Int, columns: Int) : D2DoubleMatrix {
            return D2DoubleMatrix(rows, columns)
        }
        /**
         * Returns matrix such that each element has uniform [leftBount, rightBound]
         * of shape rows x columns
         * @param rows rows number
         * @param columns column number
         * @param leftBound minimal value of uniform variable
         * @param rightBound maximal value of uniform variable
         * @return random matrix
         */

        fun randomElementUniformMatrix(rows: Int, columns: Int, leftBound: Double, rightBound: Double) : D2DoubleMatrix {
            val result = zero(rows, columns)
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    val valueToSet = Math.random() * (rightBound - leftBound) - leftBound
                    result.setAt(i, j, valueToSet)
                }
            }
            return result
        }

        /**
         * returns clone of matrix
         * @param A matrix to clone
         * @return cloned matrix
         */
        fun clone(A: D2DoubleMatrix): D2DoubleMatrix {
            val B = zero(A.shape().first, A.shape().second)
            for (i in 0 until A.shape().first) {
                for (j in 0 until A.shape().second) {
                    B.setAt(i, j, A.numberAt(i, j))
                }
            }
            return B
        }

        fun transpose(A: D2DoubleMatrix): D2DoubleMatrix {
            val B = zero(A.shape().second, A.shape().first)
            for (i in 0 until A.shape().first) {
                for (j in 0 until A.shape().second) {
                    B.setAt(j, i, A.numberAt(i, j))
                }
            }
            return B
        }

        /**
         * reshapes flat array to given shape
         * rows * columns == arr.size must holds.
         * @param rows rows number
         * @param columns column number
         * @return matrix with given shape
         */
        fun fromFlatArray(rows: Int, columns: Int, arr: DoubleArray): D2DoubleMatrix {
            assert(rows * columns == arr.size)
            val result = zero(rows, columns)
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    result.setAt(i, j, arr[i * columns + j])
                }
            }
            return result
        }

        /**
         * Embedding of an existing matrix to left upper corner of larger matrix, filled with 0.0
         * Examples (not actual code, just concept):
         * embed([[1.0, 2.0],
         *        [3.0, 4.0]],
         *        rows=3,
         *        columns=4)
         * results in: [[1.0, 2.0, 0.0, 0.0],
         *              [3.0, 4.0, 0.0, 0.0],
         *              [0.0, 0.0, 0.0, 0.0]]
         *
         * @param A matrix we want to embed
         * @param rows rows number
         * @param columns column number
         * @return embeded matrix
         */
        fun embed(A: D2DoubleMatrix, rows: Int, columns: Int): D2DoubleMatrix {
            assert(rows >= A.shape().first && columns >= A.shape().second)
            val result = zero(rows, columns)
            for (i in 0 until A.shape().first) {
                for(j in 0 until A.shape().second) {
                    result.setAt(i, j, A.numberAt(i, j));
                }
            }
            return result
        }

        /**
         * cut a piece of matrix. Time complexity: O(number of elements in new matrix)
         * @param A matrix from which we want to cut
         * @param fromRowInclusive lower inclusive row bound
         * @param toRowExclusive upper exclusive row bound
         * @param fromColumnInclusive lower inclusive column bound
         * @param toColumnExclusive upper exclusive column bound
         * @return cut piece
         */
        fun slice(A: D2DoubleMatrix,
                  fromRowInclusive: Int,
                  toRowExclusive: Int,
                  fromColumnInclusive: Int,
                  toColumnExclusive: Int
        ) : D2DoubleMatrix {
            assert(
                fromRowInclusive in 0 until A.shape().first &&
                toRowExclusive in 0..A.shape().first &&
                fromColumnInclusive in 0 until A.shape().second &&
                toColumnExclusive in 0..A.shape().second &&
                toRowExclusive > fromRowInclusive &&
                toColumnExclusive > fromColumnInclusive
            )

            val newRowNumber = toRowExclusive - fromRowInclusive
            val newColumnNumber = toColumnExclusive - fromColumnInclusive

            val result: D2DoubleMatrix = MatrixCreator.zero(newRowNumber, newColumnNumber)
            for (i in 0 until newRowNumber) {
                for (j in 0 until newColumnNumber) {
                    result.setAt(i, j, A.numberAt(fromRowInclusive + i, fromColumnInclusive + j))
                }
            }
            return result
        }

    }
}
