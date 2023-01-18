package io.springbatch.springbatchlecture.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class XMLConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


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
                .<Customer, Customer>chunk(5) //<input, output>
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }
    @Bean
    public ItemReader<? extends Customer> customItemReader() {
        return new StaxEventItemReaderBuilder<Customer>()
                .name("statXml")
                .resource(new ClassPathResource("customer.xml"))
                .addFragmentRootElements("customer")
                .unmarshaller(itemUnmarshaller())
                .build();
    }

    @Bean
    public Unmarshaller itemUnmarshaller() {
        Map<String, Class<?>> aliases = new HashMap<>();
        aliases.put("customer", Customer.class);
        aliases.put("id", Long.class);
        aliases.put("name", String.class);
        aliases.put("age", Integer.class);

        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAliases(aliases);

        return xStreamMarshaller;

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
