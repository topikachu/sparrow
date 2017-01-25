package net.topikachu.dyna;

import net.topikachu.dyna.entity.Customer;
import net.topikachu.dyna.repository.CustomerRepository;
import net.topikachu.dyna.service.Dyna2Pool;
import net.topikachu.dyna.service.DynaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DynajpaApplication {
    private static final Logger log = LoggerFactory.getLogger(DynajpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DynajpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(CustomerRepository repository, DynaService dynaService, Dyna2Pool dynaPool) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");

            dynaPool.gen1();
            dynaService.dyna();
//            dynaPool.gen2();
//            dynaService.dyna2();
        };
    }


}
