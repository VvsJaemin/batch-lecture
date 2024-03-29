//package io.springbatch.springbatchlecture.itemstream;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class ItemStreamConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("batchJob")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//
//    @Bean
//    @JobScope
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<String, String>chunk(5) //<input, output>
//                .reader(itemReader())
//                .writer(itemWriter())
//                .build();
//    }
//
//    @Bean
//    public ItemWriter<? super String> itemWriter() {
//        return new CustomItemWriter();
//    }
//
//    public CustomItemStreamReader itemReader() {
//        List<String> items = new ArrayList<>(10);
//        for (int i = 0; i <= 10; i++) {
//            items.add(String.valueOf(i));
//        }
//        return new CustomItemStreamReader(items);
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println(" step 2 has executed");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//}
//
