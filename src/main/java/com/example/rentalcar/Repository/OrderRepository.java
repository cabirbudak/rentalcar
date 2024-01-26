package com.example.rentalcar.Repository;

import com.example.rentalcar.Entity.Order;
import com.example.rentalcar.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);
}
