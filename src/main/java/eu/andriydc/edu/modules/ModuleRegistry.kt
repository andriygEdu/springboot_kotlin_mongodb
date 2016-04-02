package eu.andriydc.edu.modules

import org.springframework.data.repository.Repository

/**
 * User: andriyg
 * Date: 29/02/2016
 * Time: 14:03
 *
 *
 */

interface ModuleRegistry : Repository<Module, String> {
    fun findOneByNameAndProjectNameAllIgnoreCase(name: String, projectName: String): Module?

    fun save(module: Module): Module

    fun findAll(): List<Module>
}