package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * *  ?")
    public void processTimeoutOrder(){
        log.info("处理超时订单:{}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

        List<Orders> list = orderMapper.getBystatusAndOrderTime(Orders.PENDING_PAYMENT, time);

        if(list != null && list.size() > 0){
            for (Orders orders : list) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelTime(LocalDateTime.now());
                orders.setCancelReason("订单超时未支付，系统自动取消");
                orderMapper.update(orders);
            }
        }

    }

    /**
     * 一直处于派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder(){
        log.info("处理一直处于派送中的订单:{}", LocalDateTime.now());
        List<Orders> list = orderMapper.getBystatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusHours(-1));
        if(list != null && list.size() > 0){
            for (Orders orders : list) {
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }
    }
}
