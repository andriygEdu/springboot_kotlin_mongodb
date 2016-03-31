package eu.andriydc.edu.modules

import org.springframework.data.annotation.Id
import javax.persistence.*

/**
 * User: andriyg
 * Date: 29/02/2016
 * Time: 13:53
 *
 *
 */

data class Module(
        @Id
        var code: String? = null,
        var name: String = "",
        var projectName: String = ""
) {
}
