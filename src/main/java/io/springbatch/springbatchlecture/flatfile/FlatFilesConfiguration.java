package io.springbatch.springbatchlecture.flatfile;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FlatFilesConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;



    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .next(step2())
                .build();
    }


    @Bean
    @JobScope
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(5) //<input, output>
                .reader(itemReader())
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List items) throws Exception {
                        System.out.println("items = " + items);
                    }
                })
                .build();
    }

    public FlatFileItemReader itemReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("flatFile")
                .resource(new FileSystemResource("D:\\develop\\springbatchlecture\\src\\main\\resources\\customer.txt"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(Customer.class)
                .linesToSkip(1)
                .fixedLength()
                .strict(false)
                .addColumns(new Range(1,5))
                .addColumns(new Range(6,9))
                .addColumns(new Range(10,11))
                .names("name", "year", "age")
                .build();
    }

//    @Bean
//    public ItemReader itemReader() { // delimeted...
//
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("flatFile")
//                .resource(new ClassPathResource("/customer.txt"))
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
//                .targetType(Customer.class)
//                .linesToSkip(1)
//                .delimited().delimiter(",") // 구분자 방식
//                .names("name", "age", "year")
//                .build();
//
//    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(" step 2 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}

