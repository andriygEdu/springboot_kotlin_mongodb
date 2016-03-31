package eu.andriydc.edu.modules

import org.springframework.data.repository.Repository

/**
 * User: andriyg
 * Date: 29/02/2016
 * Time: 14:03
 *
 *
 */

interface EnvironmentConfigurationRegistry : Repository<BuildEnvironmentConfiguration, String> {
    fun findOneBy(): BuildEnvironmentConfiguration?

    fun save(buildEnvironmentConfiguration: BuildEnvironmentConfiguration): BuildEnvironmentConfiguration
}