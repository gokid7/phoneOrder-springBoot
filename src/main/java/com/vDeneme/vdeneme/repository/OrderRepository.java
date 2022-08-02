package com.vDeneme.vdeneme.repository;

import com.vDeneme.vdeneme.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByGsmNumber(String gsmNumber);

}
