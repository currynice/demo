package com.cxy.actuator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:   </br>
 * Date: 2022/4/19 15:05
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@RestController
public class ItemController {





    /**
     * /items
     * @return
     */
    @GetMapping("/items")
    public ResponseEntity<List<MyItem>> items(){
        List<MyItem> responseBody = new ArrayList<>();
        responseBody.add(new MyItem("name1",10));
        responseBody.add(new MyItem("name2",20));

        return ResponseEntity.status(200).body(responseBody);
    }
}
