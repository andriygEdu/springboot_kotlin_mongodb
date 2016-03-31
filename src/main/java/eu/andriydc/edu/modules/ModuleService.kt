package eu.andriydc.edu.modules

/**
 * Created by andriy on 29/02/16.
 */

interface ModuleService {
    val noneProject: Project
    val noneModule: Module
    fun getOrCreateProject(name: String): Project
    fun getOrCreateModule(name: String): Module
    fun getModule(module_name: String): Module?

    val modules: Array<Module>

    fun createOrUpdateModule(projectName: String, moduleName: String): Module

    val buildEnvironmentConfiguration: BuildEnvironmentConfiguration
    open fun setBuildEnvironmentConfiguration(emailSender: String, incrementalNotificationEmail: String): BuildEnvironmentConfiguration
}