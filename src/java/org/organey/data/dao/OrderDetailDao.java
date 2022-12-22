package org.organey.data.dao;

import java.util.List;

import org.organey.data.model.OrderDetail;

public interface OrderDetailDao {

    public void insert(OrderDetail orderDetail);

    public void update(OrderDetail orderDetail);

    public void delete(int orderDetailId);

    public OrderDetail find(int orderDetailId);

    public List<OrderDetail> findAll();

    public List<OrderDetail> findByOrder(int orderId);

}
