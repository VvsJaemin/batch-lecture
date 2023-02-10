package io.springbatch.springbatchlecture.classifier;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor3 implements ItemProcessor<ProcessorInfo, ProcessorInfo> {
    @Override
    public ProcessorInfo process(ProcessorInfo item) throws Exception {

        System.out.println("CustomItemProcessor.process3");

        return item;
    }
}
