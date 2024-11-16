package be.athumi

import org.approvaltests.Approvals
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WineShopTest {

    @Test
    fun `a shop without wine is no shop, is it`() {
        val shop = WineShop(listOf(Wine("name", 0, 0)))

        assertThat(shop).isNotNull

        shop.next()

        assertThat(shop).isNotNull
    }
    @Test
    fun `verify different wine price and expiration combinations`() {

        // Generate all combinations
        val wines = generateWines()
        val shop = WineShop(wines)

        //do next step
        shop.next()

        // check the results
        Approvals.verifyAll("Wine", printResult(wines))
    }

    private fun generateWines(): List<Wine> {
        // List of wine names
        val wineNames = listOf(
                "Standard Wine",
                "Another Standard Wine",
                "Eco Brilliant Wine",
                "Bourdeaux Conservato",
                "Wine brewed by Alexander the Great",
                "Event Wine",
        )

        // List of wine prices
        val winePrices = listOf(
                0, 20, 99, 150, -1
        )

        // List of wine expiration years
        val wineExpirationYears = listOf(
                0,1,5,8,10
        )

        // Create all possible combinations of wine name, price, and expiration year
        return wineNames.flatMap { name ->
            winePrices.flatMap { price ->
                wineExpirationYears.map { expirationYear ->
                    Wine(name, price, expirationYear)
                }
            }
        }
    }


    @Test
    fun `verify inventory print`() {
        val wines = listOf(
                Wine(name = "Standard Wine", price = 20, expiresInYears = 10),
                Wine(name = "Bourdeaux Conservato", price = 0, expiresInYears = 2),
                Wine(name = "Another Standard Wine", price = 7, expiresInYears = 5),
                Wine(name = "Wine brewed by Alexander the Great", price = 150, expiresInYears = 0),
                Wine(name = "Wine brewed by Alexander the Great", price = 80, expiresInYears = 10),
                Wine(name = "Event Wine", price = 20, expiresInYears = 15),
                Wine(name = "Event Wine", price = 49, expiresInYears = 10),
                Wine(name = "Event Wine", price = 49, expiresInYears = 5),
                Wine(name = "Eco Brilliant Wine", price = 6, expiresInYears = 3)
        )

        val shop = WineShop(wines)
        // 3 years
        repeat(3) {
            shop.next()
        }

        // check the results
        Approvals.verifyAll("Wine", printResult(wines))
    }

    fun printResult(wines : List<Wine>) : List<String>{
        val results = mutableListOf<String>()
        wines.forEach { wine ->
            results.add("Wine(name=${wine.name}, price=${wine.price}, expiresInYears=${wine.expiresInYears})\n")
        }
        return results
    }

}
