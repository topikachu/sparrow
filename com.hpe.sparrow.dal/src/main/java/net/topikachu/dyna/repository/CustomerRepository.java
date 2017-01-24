package net.topikachu.dyna.repository;

/**
 * Created by 禕 on 2017/1/21.
 */
import java.util.List;

import net.topikachu.dyna.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}