package com.service;

import com.pojo.ModeAndView;
import com.pojo.Seller;

public interface SellerServiceInterface {
    ModeAndView login(Seller seller);
    ModeAndView register(Seller seller);
}
