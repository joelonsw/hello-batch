package com.example.hellobatch.job.validatedparam;

import com.example.hellobatch.job.validatedparam.validator.FileParamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


/*
 * run : --spring.batch.job.names=validatedParamJob -fileName=test.csv
 */

@Configuration
@RequiredArgsConstructor
public class ValidatedParamJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    // 파라미터로 들어오는 파일 형식이 csv가 맞는지 여기서 tasklet까지 가지 않고 Job단에서 검증할 수 있음 => validator()
    @Bean
    public Job validatedParamJob(Step validatedParamStep) {
        return jobBuilderFactory.get("validatedParamJob")
                .incrementer(new RunIdIncrementer())
                .validator(new FileParamValidator())
                .validator(multipleValidators())
                .start(validatedParamStep)
                .build();
    }

    // 여러개의 validator를 합성하여 사용할 수 있음
    private CompositeJobParametersValidator multipleValidators() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        validator.setValidators(Arrays.asList(new FileParamValidator()));
        return validator;
    }

    // 읽고 쓰고 할게 없다 (ItemReader, ItemWriter 할게 없다!) => tasklet() 쓰셔요
    @JobScope
    @Bean
    public Step validatedParamStep(Tasklet validatedParamTasklet) {
        return stepBuilderFactory.get("validatedParamStep")
                .tasklet(validatedParamTasklet)
                .build();
    }

    @StepScope
    @Bean
    public Tasklet validatedParamTasklet(@Value("#{jobParameters['fileName']}") String fileName) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("fileName = " + fileName);
                System.out.println("validatedParam!");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
