interface multiplyStrategy {
    companion object {
        //should I transfer this method to MatrixUtil class ?
        fun isCompatibleShapes(first: Pair<Int, Int>, second: Pair<Int, Int>): Boolean {
            return first.second == second.first
        }
    }
    public fun multiply(L: D2DoubleMatrix, R: D2DoubleMatrix) : D2DoubleMatrix
}