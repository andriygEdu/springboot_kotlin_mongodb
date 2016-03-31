package eu.andriydc.edu.modules

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * Created by andriy on 29/02/16.
 */

@Service
internal class ModuleServiceImpl
@Autowired
constructor(
        val moduleRegistry: ModuleRegistry,
        val projectRegistry: ProjectRegistry,
        val environmentConfigurationRegistry: EnvironmentConfigurationRegistry
) : ModuleService {
    override val modules: Array<Module>
        get() = moduleRegistry.findAll().toTypedArray()

    override fun createOrUpdateModule(projectName: String, moduleName: String): Module {
        throw UnsupportedOperationException()
    }

    override val noneProject: Project by lazy {
        getOrCreateProject("none")
    }

    override val noneModule: Module by lazy {
        getOrCreateModule("none")
    }

    override fun getOrCreateProject(name: String): Project {
        var project = projectRegistry.findOneByNameIgnoreCase(name)
        if (project == null) {
            project = Project(name = name)
            projectRegistry.save(project)
            return project
        } else {
            return project
        }
    }

    override fun getOrCreateModule(name: String): Module {
        var module = moduleRegistry.findOneByNameIgnoreCase(name)
        if (module != null) {
            return module
        } else {
            module = Module(name = name, projectName = noneProject.name)
            moduleRegistry.save(module)
            return module
        }
    }

    override fun getModule(module_name: String): Module? {
        return moduleRegistry.findOneByNameIgnoreCase(module_name)
    }

    private val logger: Logger by lazy {
        LoggerFactory.getLogger(javaClass)
    }

    override val buildEnvironmentConfiguration: BuildEnvironmentConfiguration
        get() = environmentConfigurationRegistry.findOneBy() ?:
                environmentConfigurationRegistry.save(BuildEnvironmentConfiguration())

    override fun setBuildEnvironmentConfiguration(
            emailSender: String, incrementalNotificationEmail: String
    ): BuildEnvironmentConfiguration {
        val conf = buildEnvironmentConfiguration
        conf.emailSender = emailSender
        conf.incrementalNotificationEmail = incrementalNotificationEmail
        return environmentConfigurationRegistry.save(conf)
    }

    @PostConstruct
    fun onStart() {
        logger.info("Empty module: $noneModule")
    }
}