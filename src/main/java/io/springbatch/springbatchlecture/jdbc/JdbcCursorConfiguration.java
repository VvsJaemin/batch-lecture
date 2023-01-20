package io.springbatch.springbatchlecture.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor
public class JdbcCursorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private int chaunkSize = 10;

    private DataSource dataSource;


    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }


    @Bean
    @JobScope
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(chaunkSize) //<input, output>
                .reader(customerItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Customer> customerItemReader() {
        return new JdbcCursorItemReaderBuilder<Customer>()
                .name("jdbcCursorItemReader")
                .fetchSize(chaunkSize)
                .sql("select id, firstName, lastName, birthdate from customer where firstName like ? order by lastName, firstName")
                .beanRowMapper(Customer.class)
                .queryArguments("A%")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ItemWriter<Customer> customItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println("item.toString() = " + item.toString());
            }
        };
    }


}

