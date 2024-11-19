package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        wines.forEach { wine ->
            val handler = WineHandlerFactory.getHandler(wine)
            //update price
            handler.updatePrice(wine)
            //update expiration
            if (WineHandlerFactory.getWineType(wine) != WineType.LEGENDARY) wine.expiresInYears -= 1
            //update price if expired
            if (wine.expiresInYears < 0){
                handler.updatePriceBecauseExpired(wine)
            }
        }
    }
}