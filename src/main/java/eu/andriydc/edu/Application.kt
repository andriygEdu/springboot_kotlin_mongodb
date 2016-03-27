package eu.andriydc.edu

import eu.andriydc.edu.dal.Customer
import eu.andriydc.edu.dal.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Application : CommandLineRunner {
    @Autowired var repository: CustomerRepository? = null

    override fun run(vararg args: String) {

        val repository = repository!!
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

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

}