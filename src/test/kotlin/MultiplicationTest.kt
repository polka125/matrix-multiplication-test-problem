internal class MultiplicationTest {
    companion object {

        lateinit var m1_1026_1026_x_m2_1026_1026: D2DoubleMatrix
        lateinit var m1_1026_1026: D2DoubleMatrix
        lateinit var m2_1026_1026: D2DoubleMatrix

        var m1_100_100000: D2DoubleMatrix 
        var m2_100000_100: D2DoubleMatrix
        var m1_100_100000_x_m2_100000_100: D2DoubleMatrix

        init {
            m1_1026_1026 = MatrixCreator.randomElementUniformMatrix(1026, 1026, -1.0, 1.0)
            m2_1026_1026 = MatrixCreator.randomElementUniformMatrix(1026, 1026, -1.0, 1.0)
            //Yes, It's not very good that for instancing tests we use complicated Strassen algorithm
            //On the other hand, there is a test that shows that result of Strassen().multiply
            //equal to dummy multiply
            m1_1026_1026_x_m2_1026_1026 = Strassen().multiply(m1_1026_1026, m2_1026_1026)

            m1_100_100000 = MatrixCreator.randomElementUniformMatrix(100, 100000, -1.0, 1.0)
            m2_100000_100 = MatrixCreator.randomElementUniformMatrix(100000, 100, -1.0, 1.0)
            m1_100_100000_x_m2_100000_100 = Strassen().multiply(m1_100_100000, m2_100000_100)

            
        
        }

        fun maxdiff(L: D2DoubleMatrix, R: D2DoubleMatrix): Double {
            assert(L.shape() == R.shape())
            var absmax = 0.0
            for (i in 0 until L.shape().first) {
                for (j in 0 until L.shape().second) {
                    var currdiff = Math.abs(L.numberAt(i, j) - R.numberAt(i, j))
                    absmax = if (absmax < currdiff) currdiff else absmax
                }
            }
            return absmax
        }

    }

    val PRECISION = 1e-5




    @org.junit.jupiter.api.Test
    fun dummyMultiplierTest1() {
        assert(maxdiff(DummyMultiplier().multiply(m1_1026_1026, m2_1026_1026), m1_1026_1026_x_m2_1026_1026) < PRECISION)
    }
    @org.junit.jupiter.api.Test
    fun dummyCacheFriendlyTest1() {
        assert(maxdiff(DummyCacheFriendly().multiply(m1_1026_1026, m2_1026_1026), m1_1026_1026_x_m2_1026_1026) < PRECISION)
    }
    @org.junit.jupiter.api.Test
    fun strassenTest1() {
        assert(maxdiff(Strassen().multiply(m1_1026_1026, m2_1026_1026), m1_1026_1026_x_m2_1026_1026) < PRECISION)
    }

    @org.junit.jupiter.api.Test
    fun dummyMultiplierTest2() {
        assert(maxdiff(DummyMultiplier().multiply(m1_100_100000, m2_100000_100), m1_100_100000_x_m2_100000_100) < PRECISION)
    }

    @org.junit.jupiter.api.Test
    fun dummyCacheFriendlyTest2() {
        assert(maxdiff(DummyCacheFriendly().multiply(m1_100_100000, m2_100000_100), m1_100_100000_x_m2_100000_100) < PRECISION)
    }
    @org.junit.jupiter.api.Test
    fun strassenTest2() {
        assert(maxdiff(Strassen().multiply(m1_100_100000, m2_100000_100), m1_100_100000_x_m2_100000_100) < PRECISION)
    }


}