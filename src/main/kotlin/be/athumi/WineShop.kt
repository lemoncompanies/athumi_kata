package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        wines.forEach { wine -> wine.updatePriceAndExpiration() }
    }
}