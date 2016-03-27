package eu.andriydc.edu.dal

import org.springframework.data.annotation.Id


data class Customer(
        val firstName: String = "",
        val lastName: String = "",
        @Id val id: String? = null
) {
}