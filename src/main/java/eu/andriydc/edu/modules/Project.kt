package eu.andriydc.edu.modules

import org.springframework.data.annotation.Id

/**
 * User: andriyg
 * Date: 29/02/2016
 * Time: 13:53
 *
 *
 */

data class Project(
        @Id
        var code: String? = null,
        var name: String = ""
) {
}
