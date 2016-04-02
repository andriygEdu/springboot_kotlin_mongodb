package eu.andriydc.edu.modules

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
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
        val module = getOrCreateModule(projectName, moduleName)
        module.updateDate = Date()
        moduleRegistry.save(module)
        return module
    }

    override fun getOrCreateModule(name: String): Module {
        return getOrCreateModule(noneProject.name, name)
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

    override fun getOrCreateModule(projectName: String, name: String): Module {
       return getOrCreateModule(getOrCreateProject(projectName), name)
    }

    fun getOrCreateModule(project: Project, name: String): Module {
        return moduleRegistry.findOneByNameAndProjectNameAllIgnoreCase(name, projectName = project.name) ?:
         run {
            val module = Module(name = name, projectName = project.name)
            moduleRegistry.save(module)
            return module
        }
    }

    override fun getModule(module_name: String, project: Project): Module? {
        return moduleRegistry.findOneByNameAndProjectNameAllIgnoreCase(module_name, projectName = project.name)
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