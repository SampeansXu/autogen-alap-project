package com.example.demo.api.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/demo")
@Api(value = "接口", tags = "接口")
public class DemoController{
    @ApiOperation(value = "Get业务接口", notes = "Get业务接口", httpMethod = "GET")
    @GetMapping("/search")
    public Object searching(Object query) {
        return true;
    }

}
