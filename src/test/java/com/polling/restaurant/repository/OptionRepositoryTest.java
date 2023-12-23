package com.polling.restaurant.repository;

import com.polling.restaurant.entity.Options;
import com.polling.restaurant.repository.OptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OptionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OptionRepository optionRepository;

    @Test
    void testFindAllByUserName() {
        // Arrange
        Options option1 = new Options("Option1", "User1", null);
        Options option2 = new Options("Option2", "User1", null);
        Options option3 = new Options("Option3", "User2", null);

        entityManager.persistAndFlush(option1);
        entityManager.persistAndFlush(option2);
        entityManager.persistAndFlush(option3);

        // Act
        List<Options> optionsList = optionRepository.findAllByUserName("User1");

        // Assert
        assertEquals(2, optionsList.size());
        assertTrue(optionsList.stream().allMatch(option -> option.getUserName().equals("User1")));
    }
}
