//package io.springbatch.springbatchlecture.batchstatus;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class BatchStatusExitStatusConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
////    @Bean
////    public Job stepJob() {
////        return jobBuilderFactory.get("batchJob").start(step1()).next(step2()).build();
////    }
//
//    @Bean
//    public Job batchJob() {
//        return this.jobBuilderFactory.get("batchJob")
//                .start(step1())
//                .on("FAILED")
//                .to(step2())
//                .end()
//                .build();
//
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println(" step 1 has executed");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println(" step 2 has executed");
//                stepContribution.setExitStatus(ExitStatus.FAILED);
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println(" step 3 has executed");
//                stepContribution.setExitStatus(ExitStatus.FAILED);
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//    @Bean
//    public Step step4() {
//        return stepBuilderFactory.get("step4").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println(" step 4 has executed");
//                stepContribution.setExitStatus(ExitStatus.FAILED);
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//}
//
