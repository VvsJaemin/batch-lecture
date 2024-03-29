package io.springbatch.springbatchlecture.jpa;//package io.springbatch.springbatchlecture.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;


@Configuration
@RequiredArgsConstructor
public class JpaItemWriter {

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
    @JobScope
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer2>chunk(chunkSize) //<input, output>
                .reader(customerItemReader())
                .processor(customItemProcessor())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemProcessor<? super Customer, ? extends Customer2> customItemProcessor() {

        return new CustomerItemProcessor();
    }

    @Bean
    public ItemReader<Customer> customerItemReader() {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("firstname", "A%");

        return new JpaCursorItemReaderBuilder<Customer>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select c from Customer c where firstname like :firstname")
                .parameterValues(paramMap)
                .build();
    }

    @Bean
    public ItemWriter<Customer2> customItemWriter() {
        return new JpaItemWriterBuilder<Customer2>()
                .usePersist(true)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


}

