package com.service;

import com.dao.DaoImpl;
import com.pojo.ModeAndView;
import com.pojo.User;

import java.util.List;

public interface UserServiceInterface {
    ModeAndView login(User user);
    ModeAndView register(User user);
}
