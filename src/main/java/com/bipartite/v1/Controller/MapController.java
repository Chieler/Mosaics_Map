package com.bipartite.v1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    @GetMapping("/")
    public String ShowMap(){
        return  "forward:/index.html"; 
    }
}
