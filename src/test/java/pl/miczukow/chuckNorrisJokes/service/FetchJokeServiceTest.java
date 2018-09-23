package pl.miczukow.chuckNorrisJokes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.miczukow.chuckNorrisJokes.dto.Joke;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class FetchJokeServiceTest {

    @TestConfiguration
    static class FetchJsonServiceTestContextConfiguration {

        @Bean
        public FetchJokeService fetchJson() {
            return new FetchJokeService();
        }
    }

    @Autowired
    FetchJokeService fetchJokeService;

    @Test
    public void shouldGetJokeObjectFilledWithDataFromJson() {
        Optional<Joke> joke = fetchJokeService.getJoke();

        assertTrue(joke.isPresent());
        assertNotNull(joke.get());
    }
}