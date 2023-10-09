package ${packageName};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/${demoName?lower_case}")
@Api(value = "接口", tags = "接口")
public class ${demoName}Controller{
    @ApiOperation(value = "Get业务接口", notes = "Get业务接口", httpMethod = "GET")
    @GetMapping("/search")
    public Object search(Object query) {
        return true;
    }

}
