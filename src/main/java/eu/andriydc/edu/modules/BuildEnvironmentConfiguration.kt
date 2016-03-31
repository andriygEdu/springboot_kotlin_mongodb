package eu.andriydc.edu.modules

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version

/**
 * User: andriyg
 * Date: 22/03/2016
 * Time: 15:33
 */
class BuildEnvironmentConfiguration(
        @Id
        var code: String? = null,
        var incrementalNotificationEmail: String = "",
        var emailSender: String = "",
        @Version()
        var version: Int = 0
)
