package com.dor.ws.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 02.05.2014.
 */
@Controller
@RequestMapping("/smartraspy")
public class SensorsViewController {

    @RequestMapping(value = "/web", method = RequestMethod.GET)
    public String getSensorsView(ModelMap model) {
        model.addAttribute("message", "some message");
        return "sensors";
    }
}
