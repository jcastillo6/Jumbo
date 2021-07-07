package com.jcastillo.jumbo.sandbox.locator.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jcastillo.jumbo.sandbox.locator.controller.StoreController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() { 
    	return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage(StoreController.class.getPackageName()))              
          .paths(PathSelectors.any())
          .build().apiInfo(getApiInfo());                                           
    }
    
    private ApiInfo getApiInfo() {
    	return new ApiInfoBuilder()
            .title("Jumbo app")
            .description("This api was made to support the frontend app")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("", "", "castillo.guerra@gmial.com"))
            .build();
    }
	

}
