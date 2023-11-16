package com.backend.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manual")
public class WebController {

    @GetMapping() 
    public String index(){
        return "index-manual";
    }
    
}
