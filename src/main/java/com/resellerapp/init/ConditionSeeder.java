package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionType;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConditionSeeder implements CommandLineRunner {

   private final ConditionRepository conditionRepository;

    public ConditionSeeder(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.conditionRepository.count() == 0) {
            List<Condition> conditions = Arrays.stream(ConditionType.values())
                    .map(Condition::new)
                    .collect(Collectors.toList());
            this.conditionRepository.saveAll(conditions);
        }
    }
}
