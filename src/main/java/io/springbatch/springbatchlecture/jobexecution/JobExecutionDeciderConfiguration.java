//package io.springbatch.springbatchlecture.jobexecution;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.flow.JobExecutionDecider;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobExecutionDeciderConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job batchJob() {
//        return this.jobBuilderFactory.get("batchJob")
//                .incrementer(new RunIdIncrementer())
//                .start(step())
//                .next(decider())
//                .from(decider()).on("ODD").to(oddStep())
//                .from(decider()).on("EVEN").to(evenStep())
//                .end()
//                .build();
//
//    }
//    @Bean
//    public JobExecutionDecider decider() {
//        return new CustomDecider();
//    }
//
//    @Bean
//    public Step step() {
//        return stepBuilderFactory.get("startStep").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("This is the start tasklet");
//                stepContribution.setExitStatus(ExitStatus.FAILED);
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//    @Bean
//    public Step evenStep() {
//        return stepBuilderFactory.get("evenStep").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println(" evenStep 2 has executed");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//    @Bean
//    public Step oddStep() {
//        return stepBuilderFactory.get("oddStep").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println(" oddStep has executed");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//}
//
