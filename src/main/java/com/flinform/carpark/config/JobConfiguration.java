package com.flinform.carpark.config;

import com.flinform.carpark.entity.CarPark;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

public class JobConfiguration {

//@PreAuthorize("hasRole('ADMIN')")
@Bean
public Job importCarparkJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                            ItemReader<CarPark> reader, ItemProcessor<CarPark, CarPark> processor,
                            ItemWriter<CarPark> writer) {

/*    Step step = stepBuilderFactory.get("step1")
            .<CarPark, CarPark>chunk(10)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .faultTolerant()
            .retryLimit(3)
           // .retry(MyCustomException.class)  // 自定义异常
            .build();

    return jobBuilderFactory.get("importCarparkJob")
            .start(step)
            .build();

 */
    return null;
}

}
