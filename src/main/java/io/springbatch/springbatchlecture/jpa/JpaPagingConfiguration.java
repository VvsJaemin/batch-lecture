package io.springbatch.springbatchlecture.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
@RequiredArgsConstructor
public class JpaPagingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private int chunkSize = 10;

    private EntityManagerFactory entityManagerFactory;


    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(chunkSize) //<input, output>
                .reader(customerItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Customer> customerItemReader() {
        return new JpaPagingItemReaderBuilder<Customer>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .queryString("select c from Customer c")
                .build();
    }


    @Bean
    public ItemWriter<Customer> customItemWriter() {
        return items -> {
            for (Customer customer : items) {
                System.out.println("customer.getAddress().getLocation() = " + customer.getAddress().getLocation());
            }
        };
    }


}

