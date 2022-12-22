package org.organey.data.dao;

import org.organey.data.impl.CategoryDaoImpl;
import org.organey.data.impl.OrderDaoImpl;
import org.organey.data.impl.OrderDetailDaoImpl;
import org.organey.data.impl.ProductDaoImpl;
import org.organey.data.impl.UserDaoImpl;

public class Database extends DatabaseDao {
	
	@Override
    public UserDao getUserDao() {
        // TODO Auto-generated method stub
        return new UserDaoImpl();
    }

    @Override
    public ProductDao getProductDao() {
        return new ProductDaoImpl();
    }
    @Override
    public CategoryDao getCategoryDao() {
        return new CategoryDaoImpl();
    }

    @Override
    public OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }

    @Override
    public OrderDetailDao getOrderDetailDao() {
        return new OrderDetailDaoImpl();
    }


}
