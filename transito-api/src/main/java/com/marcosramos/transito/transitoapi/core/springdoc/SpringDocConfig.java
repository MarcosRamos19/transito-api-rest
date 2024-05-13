package com.marcosramos.transito.transitoapi.core.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("API de Transito")
                        .version("v1")
                        .description("REST API de um sistema simples de transito")
                );
    }
    @Bean
    public OpenApiCustomizer openApiCustomizer(){
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach((httpMethod, operation) -> {
                               ApiResponses responses = operation.getResponses();
                               switch (httpMethod){
                                   case GET:
                                       responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                       responses.addApiResponse("406", new ApiResponse().description("Recurso não possui reprsentação que poderia ser aceita pelo consumidor"));
                                       responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                       break;
                                   case POST:
                                       responses.addApiResponse("400", new ApiResponse().description("Requisição inválida"));
                                       responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                       break;
                                   case PUT:
                                       responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                       responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                       responses.addApiResponse("400", new ApiResponse().description("Requisição inválida"));
                                       break;
                                   case DELETE:
                                       responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                       responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                       break;
                                   default:
                                       responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
                                       break;
                               }

                            })
                    );

        };
    }


}
