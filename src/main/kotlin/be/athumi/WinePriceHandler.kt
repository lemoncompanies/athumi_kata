package be.athumi

interface WinePriceHandler {
    fun updatePrice(wine: Wine)
    fun updatePriceBecauseExpired(wine: Wine)

    //price manipulation
    fun adjustPrice(wine: Wine, amount: Int) {
        wine.price = (wine.price + amount).coerceAtLeast(0)
    }
}