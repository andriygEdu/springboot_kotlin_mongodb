package eu.andriydc.edu.modules

import org.springframework.data.repository.Repository

/**
 * User: andriyg
 * Date: 29/02/2016
 * Time: 14:03
 *
 *
 */

interface ProjectRegistry : Repository<Project, String> {
    fun findOneByNameIgnoreCase(name: String): Project?

    fun save(project: Project)
}