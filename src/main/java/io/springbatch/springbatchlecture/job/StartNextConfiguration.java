//package io.springbatch.springbatchlecture.job;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class StartNextConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job batchJob() {
//        return jobBuilderFactory.get("batchJob")
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("step1 was executed ");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(((contribution, chunkContext) -> {
//                    System.out.println("step2 was executed ");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//
//
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet(((contribution, chunkContext) -> {
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//
//
//}
