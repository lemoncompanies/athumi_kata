package be.athumi

data class Wine(var name: String, var price: Int, var expiresInYears: Int) {


    private val MAX_PRICE = 100
    private val MIN_PRICE = 0

    fun getWineType(): WineType = when {
        name.contains("Conservato") -> WineType.CONSERVATO
        name.startsWith("Event") -> WineType.EVENT
        name == "Wine brewed by Alexander the Great" -> WineType.LEGENDARY
        name.startsWith("Eco") -> WineType.ECO_BREWED
        else -> WineType.STANDARD
    }

    private fun adjustPrice(amount: Int) {
        price += amount
    }

    private fun handleStandardWinePrice() {
        adjustPrice(-1)
    }

    private fun handleEcoBrewed() {
        adjustPrice(-2)
    }

    private fun handleConservatoPrice() {
        if (price < MAX_PRICE) adjustPrice(1)
    }

    private fun handleEventItemPrice() {
        if (price < MAX_PRICE) adjustPrice(1)
        if (price < MAX_PRICE) {
            if (expiresInYears < 8) adjustPrice(1)
            if (expiresInYears < 3) adjustPrice(2)
        }
    }

    private fun handleExpiredItemPrice() {
        when (getWineType()) {
            WineType.STANDARD, WineType.ECO_BREWED -> adjustPrice(-1)
            WineType.EVENT -> price = MIN_PRICE
            WineType.CONSERVATO -> handleConservatoPrice()
            else -> {
            // No action needed for other types
            }
        }
    }

    private fun adjustExpiration() {
        if (getWineType() != WineType.LEGENDARY) expiresInYears -= 1
    }

    fun updatePriceAndExpiration() {
        when (getWineType()) {
            WineType.STANDARD -> handleStandardWinePrice()
            WineType.CONSERVATO -> handleConservatoPrice()
            WineType.EVENT -> handleEventItemPrice()
            WineType.ECO_BREWED -> handleEcoBrewed()
            else -> {
                // No action for Legendary
            }
        }

        adjustExpiration()

        if (expiresInYears < 0) handleExpiredItemPrice()

        price = price.coerceAtLeast(MIN_PRICE)
    }
}

enum class WineType {
    CONSERVATO, EVENT, LEGENDARY, STANDARD, ECO_BREWED
}