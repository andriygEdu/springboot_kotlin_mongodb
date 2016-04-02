package eu.andriydc.edu.modules

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.util.*
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
        var projectName: String = "",
        var updateDate: Date = Date(),
        @Version
        var version: Int = 1
) {
}
