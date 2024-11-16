package be.athumi

data class Wine(var name: String, var price: Int, var expiresInYears: Int) {

    fun isConservato(): Boolean =
            name == "Bourdeaux Conservato" || name == "Bourgogne Conservato"

    fun isEventItem(): Boolean =
            name.startsWith("Event")

    fun isLegendaryItem(): Boolean =
            name == "Wine brewed by Alexander the Great"

    fun isEcoBrewed() : Boolean =
            name == "Eco Brilliant Wine"

    fun handleEventItemPrice() {
        if (price < 100) {
            if (expiresInYears < 8) price += 1
            if (expiresInYears < 3) price += 2
        }
    }

    fun handleExpiredItem() {
        if (!isConservato()) {
            if (!isEventItem() && price > 0 && !isLegendaryItem()) {
                price -= 1
            } else if (isEventItem()) {
                price = 0
            }
        } else if (price < 100) {
            price += 1
        }
    }

    fun update() {
        when {
            !isConservato() && !isEventItem() -> {
                if (!isLegendaryItem() && price > 0) {
                    price -= if (isEcoBrewed()) 2 else 1
                }
            }
            price < 100 -> {
                price += 1
                if (isEventItem()) handleEventItemPrice()
            }
        }

        if (!isLegendaryItem()) expiresInYears -= 1

        if (expiresInYears < 0) handleExpiredItem()

        price = price.coerceAtLeast(0)
    }
}