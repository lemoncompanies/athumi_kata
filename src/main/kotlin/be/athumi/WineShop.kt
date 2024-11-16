package be.athumi

class WineShop(var items: List<Wine>) {
    fun next() {
        // Wine Shop logic
        items.forEach { item ->
            if (item.name != "Bourdeaux Conservato" && item.name != "Bourgogne Conservato" && !item.name.startsWith("Event")) {
                if (item.price > 0 && item.name != "Wine brewed by Alexander the Great") {
                    item.price = item.price - 1
                }
            } else {
                if (item.price < 100) {
                    item.price = item.price + 1

                    if (item.name.startsWith("Event")) {
                        if (item.expiresInYears < 8 && item.price < 100) {
                            item.price = item.price + 1
                        }

                        if (item.expiresInYears < 3 && item.price < 100) {
                            item.price = item.price + 2
                        }
                    }
                }
            }

            if (item.name != "Wine brewed by Alexander the Great") {
                item.expiresInYears = item.expiresInYears - 1
            } else if (item.price < 0) {
                item.price = 0
            }

            if (item.expiresInYears < 0) {
                if (!item.name.contains("Conservato")) {
                    if (!item.name.contains("Event")) {
                        if (item.price > 0 && item.name != "Wine brewed by Alexander the Great") {
                            item.price = item.price - 1
                        }
                    } else {
                        item.price = 0
                    }
                } else {
                    if (item.price < 100) {
                        item.price = item.price + 1
                    }
                }
            }

            if (item.price < 0) {
                item.price = 0
            }
        }
    }
}