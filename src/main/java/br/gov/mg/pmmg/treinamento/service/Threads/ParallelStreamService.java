package br.gov.mg.pmmg.treinamento.service.Threads;

import java.math.BigDecimal;
import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import br.gov.mg.pmmg.treinamento.dto.Order;

@Service
public class ParallelStreamService {

    @PostConstruct
    public void init() {
        BigDecimal result = totalPaidAmount();
        System.out.println("Total Paid Amount: " + result);
    }

    /**
     * Calculates the total amount of paid orders using parallel streams.
     * 
     * @return The total amount of paid orders.
     */
    public BigDecimal totalPaidAmount() {
        List<Order> orders = List.of(
                new Order(1L, "PAID", new BigDecimal("120.50")),
                new Order(2L, "PENDING", new BigDecimal("80.00")),
                new Order(3L, "PAID", new BigDecimal("250.75")),
                new Order(4L, "CANCELLED", new BigDecimal("40.00")),
                new Order(5L, "PAID", new BigDecimal("99.99")));

        BigDecimal totalPaidAmount = orders
                .parallelStream()
                .filter(order -> "PAID".equals(order.getStatus()))
                .map(Order::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPaidAmount;
    }

}
