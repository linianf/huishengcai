package com.hsh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController extends ABaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
}
