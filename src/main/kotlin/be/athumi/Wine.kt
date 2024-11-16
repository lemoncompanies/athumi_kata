package be.athumi

data class Wine(var name: String, var price: Int, var expiresInYears: Int) {

    fun isConservato(): Boolean =
            name == "Bourdeaux Conservato" || name == "Bourgogne Conservato"

    fun isEventItem(): Boolean =
            name.startsWith("Event")

    fun isLegendaryItem(): Boolean =
            name == "Wine brewed by Alexander the Great"
    fun update() {
        if (!isConservato() && !isEventItem()) {
            if (price > 0 && !isLegendaryItem()) {
                price -= 1
            }
        } else {
            if (price < 100) {
                price += 1
                if (isEventItem()) {
                    if (expiresInYears < 8 && price < 100) {
                        price = price + 1
                    }

                    if (expiresInYears < 3 && price < 100) {
                        price = price + 2
                    }
                }
            }
        }

        if (!isLegendaryItem()) {
            expiresInYears -= 1
        }

        if (expiresInYears < 0) {
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

        if (price < 0) {
            price = 0
        }
    }

}