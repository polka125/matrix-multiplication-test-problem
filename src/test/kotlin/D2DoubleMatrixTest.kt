import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D2DoubleMatrixTest {

    @org.junit.jupiter.api.Test
    fun multiplyTo() {
        val A = MatrixCreator.fromRows(doubleArrayOf(1.0, 2.0, 3.0))
        val B = MatrixCreator.fromRows(
            doubleArrayOf(1.0),
            doubleArrayOf(2.0),
            doubleArrayOf(3.0)
        )
        assertEquals(A.multiplyTo(B).getEntryArray()[0], 1.0 * 1.0 + 2.0 * 2.0 + 3.0 * 3.0)
    }
}