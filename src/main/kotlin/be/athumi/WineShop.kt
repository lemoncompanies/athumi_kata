package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        wines.forEach { wine ->
            if (!isConservato(wine) && !isEventItem(wine)) {
                if (wine.price > 0 && !isLegendaryItem(wine)) {
                    wine.price -= 1
                }
            } else {
                if (wine.price < 100) {
                    wine.price += 1
                    if (isEventItem(wine)) {
                        if (wine.expiresInYears < 8 && wine.price < 100) {
                            wine.price = wine.price + 1
                        }

                        if (wine.expiresInYears < 3 && wine.price < 100) {
                            wine.price = wine.price + 2
                        }
                    }
                }
            }

            if (!isLegendaryItem(wine)) {
                wine.expiresInYears -= 1
            }

            if (wine.expiresInYears < 0) {
                if (!isConservato(wine)) {
                    if (!isEventItem(wine) && wine.price > 0 && !isLegendaryItem(wine)) {
                        wine.price -= 1
                    } else if (isEventItem(wine)) {
                        wine.price = 0
                    }
                } else if (wine.price < 100) {
                    wine.price += 1
                }
            }

            if (wine.price < 0) {
                wine.price = 0
            }
        }
    }

    fun isConservato(wine: Wine): Boolean =
            wine.name == "Bourdeaux Conservato" || wine.name == "Bourgogne Conservato"

    fun isEventItem(wine: Wine): Boolean =
            wine.name.startsWith("Event")

    fun isLegendaryItem(wine: Wine): Boolean =
            wine.name == "Wine brewed by Alexander the Great"
}