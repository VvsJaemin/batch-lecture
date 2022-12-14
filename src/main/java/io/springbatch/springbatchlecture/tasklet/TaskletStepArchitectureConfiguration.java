//package io.springbatch.springbatchlecture.tasklet;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.StepExecutionListener;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class TaskletStepArchitectureConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job batchJob() {
//        return this.jobBuilderFactory.get("batchJob")
//                .incrementer(new RunIdIncrementer())
//                .start(step1())
//                .next(step2())
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//
//                    }
//
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        return null;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("contribution + \", chunkContext = \" + chunkContext = " + contribution + ", chunkContext = " + chunkContext);
//
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .allowStartIfComplete(true)
//                .build();
//
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("contribution + \", chunkContext = \" + chunkContext = " + contribution + ", chunkContext = " + chunkContext);
//                        return RepeatStatus.FINISHED;
//
//                    }
//                })
//                .startLimit(3)
//                .build();
//
//    }
//}
