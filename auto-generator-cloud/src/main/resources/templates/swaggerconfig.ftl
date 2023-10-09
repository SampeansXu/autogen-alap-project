package ${packageName}.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置, 使用 knife4j
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 分组名称
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("${packageName}")).paths(PathSelectors.any()).build();

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("业务聚合层-API文档").description("业务聚合层-${alias}-API接口文档")
        .contact(new Contact("环球网校-${alias}", "www.hqwx.com", ""))
        .version("1.0").build();
    }
}
