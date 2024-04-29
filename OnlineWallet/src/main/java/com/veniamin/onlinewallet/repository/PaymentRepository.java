package com.veniamin.onlinewallet.repository;

import com.veniamin.onlinewallet.entity.Order;
import com.veniamin.onlinewallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PaymentRepository extends JpaRepository<Order, Long> {
    ArrayList<User> findAllBySender(User user);
}
