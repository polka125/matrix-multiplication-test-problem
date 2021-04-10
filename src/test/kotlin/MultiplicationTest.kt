import jdk.jfr.Timestamp
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class MultiplicationTest {

    @org.junit.jupiter.api.Test
    fun multiplyToTestRowToColumn() {
        val A = MatrixCreator.fromRows(doubleArrayOf(1.0, 2.0, 3.0))
        val B = MatrixCreator.fromRows(
            doubleArrayOf(1.0),
            doubleArrayOf(2.0),
            doubleArrayOf(3.0)
        )
        val result = MatrixCreator.fromRows(doubleArrayOf(1.0 * 1.0 + 2.0 * 2.0 + 3.0 * 3.0))
        assertEquals(A.multiplyTo(B), result)
    }

    @org.junit.jupiter.api.Test
    fun multiplyToTestLargeSquareRandomMatrixSquared() {

        //val startTime = System.currentTimeMillis();

        val A = MatrixCreator.randomElementUniformMatrix(2000, 2000, 0.0, 1.0)

        //val middleTime = System.currentTimeMillis()
        //println((middleTime - startTime) / 1000.0)
        assertNotEquals(A.multiplyTo(A), A)

    }

    @org.junit.jupiter.api.Test
    fun strassenMultiplyToTestLargeSquareRandomMatrixSquared() {

        //val startTime = System.currentTimeMillis();

        val A = MatrixCreator.randomElementUniformMatrix(2000, 2000, 0.0, 1.0)

        //val middleTime = System.currentTimeMillis()
        //println((middleTime - startTime) / 1000.0)
        assertNotEquals(Strassen().multiply(A, A), A)

    }

}