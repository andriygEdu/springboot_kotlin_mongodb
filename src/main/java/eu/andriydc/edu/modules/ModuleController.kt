package eu.andriydc.edu.modules

import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/module")
internal class ModuleController
@Autowired constructor(
        val moduleService: ModuleService
) {
    private val logger: Logger by lazy {
        Logger.getLogger(javaClass)
    }

    fun <T> executionTime(name: String = "", unit: () -> T) : T {
        val date = System.currentTimeMillis()
        try {
            return unit()
        } finally {
            val now = java.lang.System.currentTimeMillis()
            logger.debug("$name executed int ${(now - date) / 1000} sec and ${(now - date) % 1000} ms")
        }
    }

    @RequestMapping("/list/", method = arrayOf(RequestMethod.GET))
    @ApiResponses(
            ApiResponse(code = 200, message = "Success", response = Array<Module>::class),
            ApiResponse(code = 401, message = "Unauthorized"),
            ApiResponse(code = 404, message = "Not Found")
    )
    fun currentModulesGet(
    ): Array<Module> {
        return executionTime(name = "get modules") {
            moduleService.modules
        }
    }

    @RequestMapping("/update/{project}/{module}/", method = arrayOf(RequestMethod.POST))
    @ApiResponses(
            ApiResponse(code = 200, message = "Success", response = Module::class),
            ApiResponse(code = 401, message = "Unauthorized"),
            ApiResponse(code = 403, message = "Forbidden"),
            ApiResponse(code = 404, message = "Not Found")
    )
    fun updateModule(
            @PathVariable(value = "project") projectName: String,
            @PathVariable(value = "module") moduleName: String
    ): Module {
        return executionTime(name = "create or update module") {
            moduleService.createOrUpdateModule(projectName, moduleName)
        }
    }

    @RequestMapping("/configuration/set/", method = arrayOf(RequestMethod.POST))
    @ApiResponses(
            ApiResponse(code = 200, message = "Success", response = BuildEnvironmentConfiguration::class),
            ApiResponse(code = 401, message = "Unauthorized"),
            ApiResponse(code = 403, message = "Forbidden"),
            ApiResponse(code = 404, message = "Not Found")
    )
    fun updateConfiguration(
            @RequestParam(value = "emailFrom") emailFrom: String,
            @RequestParam(value = "incrementalNotificationEmail") incrementalNotificationEmail: String
    ): BuildEnvironmentConfiguration {
        return executionTime(name = "create or update configuration") {
            moduleService.setBuildEnvironmentConfiguration(emailFrom, incrementalNotificationEmail)
        }
    }




}