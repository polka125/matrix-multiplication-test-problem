abstract class MatrixCreator {
    companion object {
        fun fromRows(vararg rows: DoubleArray): D2DoubleMatrix {
            //TODO: check rows has same sizes
            val rowNumber = rows.size
            val columnNumber = rows[0].size
            val answer = D2DoubleMatrix(rowNumber, columnNumber)
            for (i in 0..rowNumber-1) {
                for (j in 0..columnNumber-1) {
                    answer.setAt(i, j, rows[i][j])
                }
            }
            return answer
        }
        fun fromColumns(vararg columns: DoubleArray): D2DoubleMatrix {
            TODO()
        }
        fun zero(rows: Int, columns: Int) : D2DoubleMatrix {
            return D2DoubleMatrix(rows, columns)
        }
    }
}
