import org.junit.jupiter.api.Assertions.*

internal class MatrixCreatorTest {
    @org.junit.jupiter.api.Test
    fun sliceTest() {
        var mt = MatrixCreator.fromRows(
            doubleArrayOf(1.0, 2.0, 3.0, 4.0),
            doubleArrayOf(5.0, 6.0, 7.0, 8.0),
            doubleArrayOf(9.0, 10.0, 11.0, 12.0)
        )
        var mt1314 = MatrixCreator.fromRows(
            doubleArrayOf(6.0, 7.0, 8.0),
            doubleArrayOf(10.0, 11.0, 12.0)
        )
        var mt0302 = MatrixCreator.fromRows(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(5.0, 6.0),
            doubleArrayOf(9.0, 10.0)
        )

        assertEquals(MatrixCreator.slice(mt, 1, 3, 1, 4), mt1314)

        assertEquals(MatrixCreator.slice(mt, 0, 3, 0, 2), mt0302)

        assertThrows(AssertionError::class.java) {
                MatrixCreator.slice(mt, 0, 4, 0, 1)
        }
    }
}