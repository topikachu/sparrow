package net.topikachu.dyna.repository;

/**
 * Created by 禕 on 2017/1/21.
 */

import net.topikachu.dyna.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}