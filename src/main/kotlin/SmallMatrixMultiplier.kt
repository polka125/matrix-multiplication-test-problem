class SmallMatrixMultiplier : multiplyStrategy {
    companion object {
        val long = 10000
    }
    override fun multiply(L: D2DoubleMatrix, R: D2DoubleMatrix): D2DoubleMatrix {
        if (L.shape().second >= long) {
            return DummyCacheFriendly().multiply(L, R)
        }
        return DummyMultiplier().multiply(L, R)
    }
}