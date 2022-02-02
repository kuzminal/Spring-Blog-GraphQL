package com.example.blog.config;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GraphQLConfig {
    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError>
                                                            errors) {
                //Клиентские и серверные ошибки можно обработать
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError).toList();

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e)).toList();

                return errors;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching ||
                        error instanceof
                                Throwable);
            }
        };
    }
}
