//package io.springbatch.springbatchlecture.job;
//
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomTasklet implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//
//        System.out.println("step1 was executed");
//
//        return RepeatStatus.FINISHED;
//    }
//}
