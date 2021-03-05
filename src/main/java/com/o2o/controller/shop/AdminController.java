package com.o2o.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "shop", method = { RequestMethod.GET,
		RequestMethod.POST })
public class AdminController {


	@RequestMapping(value = "/shopedit", method = RequestMethod.GET)
	private String shopEdit() {
		return "shop/shopedit";
	}

	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	private String shopList() {
		return "shop/shoplist";
	}

	@RequestMapping(value = "/shopmanagement", method = RequestMethod.GET)
	private String shopManagement() {
		return "shop/shopmanagement";
	}

}
