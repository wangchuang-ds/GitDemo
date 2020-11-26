package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(Integer id);
    void addScore(Integer id, Integer score);
}
