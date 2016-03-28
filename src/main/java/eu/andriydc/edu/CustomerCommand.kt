package eu.andriydc.edu

import eu.andriydc.edu.dal.Customer
import eu.andriydc.edu.dal.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by andriy on 28/03/16.
 */
@Service
class CustomerCommand @Autowired constructor(
        @Autowired val repository: CustomerRepository
) {
    fun execute() {
        repository.deleteAll()

        // save a couple of customers
        repository.save(Customer("Alice", "Smith"))
        repository.save(Customer("Bob", "Smith"))

        // fetch all customers
        println("Customers found with findAll():")
        println("-------------------------------")
        for (customer in repository.findAll()) {
            println(customer)
        }
        println()

        // fetch an individual customer
        println("Customer found with findByFirstName('Alice'):")
        println("--------------------------------")
        println(repository.findByFirstName("Alice"))

        println("Customers found with findByLastName('Smith'):")
        println("--------------------------------")
        for (customer in repository.findByLastName("Smith")) {
            println(customer)
        }
    }

}