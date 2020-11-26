package cn.tedu.sp04.order.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp04.feign.ItemClient;
import cn.tedu.sp04.feign.UserClient;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.service.OrderService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ItemClient itemService;
    @Autowired
    private UserClient userService;

    @Override
    public Order getOrder(String orderId) {
        //调用user-service获取用户信息
        JsonResult<User> user = userService.getUser(7);

        //调用item-service获取商品信息
        JsonResult<List<Item>> items = itemService.getItems(orderId);


        Order order = new Order();
        order.setId(orderId);
        order.setUser(user.getData());
        order.setItems(items.getData());
        return order;
    }

    @Override
    public void addOrder(Order order) {
        //调用item-service减少商品库存
        itemService.decreaseNumber(order.getItems());

        //TODO: 调用user-service增加用户积分
        userService.addScore(7, 100);

        log.info("保存订单："+order);
    }
}
