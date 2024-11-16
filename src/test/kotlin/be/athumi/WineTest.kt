package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WineTest {
    @Test
    fun `tasting or testing wine`() {
        assertThat(Wine("name", 0, 0)).isNotNull
    }

    @Test
    fun `Wine initialisation test`() {
        val wine = Wine(name = "Standard Wine", price = 10, expiresInYears = 5)

        assertEquals(wine.name, "Standard Wine")
        assertEquals(wine.price, 10)
        assertEquals(wine.expiresInYears, 5)
    }
}