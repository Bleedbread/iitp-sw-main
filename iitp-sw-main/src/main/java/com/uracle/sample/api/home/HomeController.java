package com.uracle.sample.api.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/map")
    public Map<String, String>map() {
        Map<String, String> hw = new HashMap<>();
        hw.put("Hello", "World");

        return hw;
    }

    @GetMapping("/map/list")
    public List<Map<String, String>> mapList() {

        List<Map<String, String>> hwList = new ArrayList<>();

        Map<String, String> hw1 = new HashMap<>();
        hw1.put("key1", "value1");

        Map<String, String> hw2 = new HashMap<>();
        hw1.put("key2", "value2");

        hwList.add(hw1);
        hwList.add(hw2);

        return hwList;
    }

    @GetMapping("/v1")
    public Home v1() {
        Home home = new Home();
        home.setKey("Hello");
        home.setValue("world");

        return home;
    }
}
