class D2DoubleMatrix(private val rowNumber: Int, private val columnNumber: Int) {
    private var entryArray = DoubleArray(rowNumber * columnNumber)

    fun getEntryArray() : DoubleArray {
        return entryArray.clone()
    }

    fun shape(): Pair<Int, Int> {
        return Pair<Int, Int>(rowNumber, columnNumber)
    }

    fun numberAt(rowPos: Int, columnPos: Int) : Double {
        assert(rowPos in 0 until rowNumber)
        assert(columnPos in 0 until columnNumber)
        return entryArray[rowPos * columnNumber + columnPos]
    }

    fun setAt(rowPos: Int, columnPos: Int, value: Double) {
        assert(rowPos in 0 until rowNumber)
        assert(columnPos in 0 until columnNumber)
        entryArray[rowPos * columnNumber + columnPos] = value
    }

    fun multiplyTo(to : D2DoubleMatrix) : D2DoubleMatrix{
        val multiplier: multiplyStrategy = DummyMultiplier()
        return multiplier.multiply(this, to)
    }

    override fun toString(): String {
        var result = ""
        result += "["
        for (i in 0 until shape().first) {
            result += if(i == 0) "[" else " ["
            for (j in 0 until shape().second) {
                val elem = numberAt(i, j)
                result += if (j == shape().second - 1) "$elem" else "$elem, "
            }
            result += if (i == shape().first - 1) "]]" else "],\n"
        }

        return result
    }
}



