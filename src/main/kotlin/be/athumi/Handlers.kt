package be.athumi

object ConservatoPriceHandler : WinePriceHandler {
    private const val MAX_PRICE = 100

    override fun updatePrice(wine: Wine) {
        if (wine.price < MAX_PRICE) adjustPrice(wine, 1)
    }

    override fun updatePriceBecauseExpired(wine: Wine) {
        if (wine.price < MAX_PRICE) adjustPrice(wine, 1)
    }
}

object EventPriceHandler : WinePriceHandler {
    private const val MAX_PRICE = 100
    override fun updatePrice(wine: Wine) {
        if (wine.price < MAX_PRICE) adjustPrice(wine,1)
        if (wine.price < MAX_PRICE) {
            if (wine.expiresInYears < 8) adjustPrice(wine,1)
            if (wine.expiresInYears < 3) adjustPrice(wine, 2)
        }
    }

    override fun updatePriceBecauseExpired(wine: Wine) {
        wine.price = 0
    }
}

object LegendaryPriceHandler : WinePriceHandler {
    override fun updatePrice(wine: Wine) {
        // Legendary wines never change in price

        // Price can never be below 0
        wine.price = wine.price.coerceAtLeast(0)
    }

    override fun updatePriceBecauseExpired(wine: Wine) {
        // Legendary wines never change in price
    }
}

object StandardPriceHandler : WinePriceHandler {
    override fun updatePrice(wine: Wine) {
        adjustPrice(wine, -1)
    }

    override fun updatePriceBecauseExpired(wine: Wine) {
        adjustPrice(wine, -1)
    }
}

object EcoBrewedPriceHandler : WinePriceHandler {
    override fun updatePrice(wine: Wine) {
        adjustPrice(wine, -2)
    }

    override fun updatePriceBecauseExpired(wine: Wine) {
        adjustPrice(wine, -1)
    }
}