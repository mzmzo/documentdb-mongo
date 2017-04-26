package database.mongodb;
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import database.mongodb.entity.Customer;
import database.mongodb.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository repository;

    Customer dave, oliver, carter;

    public void findBySpringData() {
        List<Customer> result = repository.findAll();
        System.out.println();
        for (Customer customer : result) {
            System.out.println(customer);
        }
        System.out.println();
    }

    @Test
    public void addBySpringData() {
        repository.deleteAll();
        dave = repository.save(new Customer("Dave", "Matthews"));
        oliver = repository.save(new Customer("Oliver August", "Matthews"));
        carter = repository.save(new Customer("Carter", "Beauford"));
        findBySpringData();
    }

    @Test
    public void updateBySpringData() {
        dave = repository.findByFirstName("Dave");
        dave.setLastName("Smith");
        repository.save(dave);
        findBySpringData();
    }

    @Test
    public void delBySpringData() {
        findBySpringData();
        Customer customer = repository.findByFirstName("Dave");
        if (customer != null) {
            repository.delete(customer);
        }
        findBySpringData();
    }
}
