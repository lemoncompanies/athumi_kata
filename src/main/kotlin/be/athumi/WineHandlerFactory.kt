package be.athumi

object WineHandlerFactory {
    fun getHandler(wine: Wine): WinePriceHandler {
        return when (getWineType(wine)) {
            WineType.CONSERVATO -> ConservatoPriceHandler
            WineType.EVENT -> EventPriceHandler
            WineType.LEGENDARY -> LegendaryPriceHandler
            WineType.ECO_BREWED -> EcoBrewedPriceHandler
            WineType.STANDARD -> StandardPriceHandler
        }
    }
    fun getWineType(wine: Wine): WineType = when {
        wine.name.contains("Conservato") -> WineType.CONSERVATO
        wine.name.startsWith("Event") -> WineType.EVENT
        wine.name == "Wine brewed by Alexander the Great" -> WineType.LEGENDARY
        wine.name.startsWith("Eco") -> WineType.ECO_BREWED
        else -> WineType.STANDARD
    }

}