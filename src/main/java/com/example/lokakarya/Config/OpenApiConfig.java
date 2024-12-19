package com.example.lokakarya.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(final ViewControllerRegistry registry){
        registry.addRedirectViewController("/", "/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui", "/swagger-ui.html");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")))
                .info(new Info()
                        .title("Loka Karya API")
                        .description("Kelompok 4 API")
                        .version("v1.0.0")
                        .termsOfService("terms of service")
                        .license(new License()
                            .name("Apache License Versi 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Banu & Raihan")
                                .email("abcd@gmail.com"))
                );

    }

    @Bean
    GroupedOpenApi allApis(){
        return GroupedOpenApi.builder()
                .group("*")
                .pathsToMatch("/**")
                .build();
    }

//    @Bean
//    GroupedOpenApi allEmployeesApis(){
//        return GroupedOpenApi.builder()
//                .group("employee")
//                .pathsToMatch("/employee/**")
//                .build();
//    }
//
//    @Bean
//    GroupedOpenApi allDepartmentApis(){
//        return GroupedOpenApi.builder()
//                .group("Department")
//                .pathsToMatch("/department/**")
//                .build();
//    }
//
//    @Bean
//    GroupedOpenApi allJobApis(){
//        return GroupedOpenApi.builder()
//                .group("Job")
//                .pathsToMatch("/job/**")
//                .build();
//    }
//
//    @Bean
//    GroupedOpenApi allJobHistoryApis(){
//        return GroupedOpenApi.builder()
//                .group("Job History")
//                .pathsToMatch("/job-history/**")
//                .build();
//    }

}
