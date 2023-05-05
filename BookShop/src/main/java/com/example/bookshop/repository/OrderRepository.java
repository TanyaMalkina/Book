package com.example.bookshop.repository;

import com.example.bookshop.models.Order;
import com.example.bookshop.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    List<Order> findByPerson(Person person);
}
