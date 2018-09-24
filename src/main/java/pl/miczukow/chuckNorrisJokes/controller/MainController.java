package pl.miczukow.chuckNorrisJokes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.miczukow.chuckNorrisJokes.dto.Joke;
import pl.miczukow.chuckNorrisJokes.service.FetchJokeService;

import java.util.Optional;

@Controller
public class MainController {

    private FetchJokeService fetchJokeService;

    public MainController(FetchJokeService fetchJokeService) {
        this.fetchJokeService = fetchJokeService;
    }

    @GetMapping("/")
    public String displayJoke(final Model model) {
        Optional<Joke> joke = fetchJokeService.getJoke();
        if (joke.isPresent()) {
            model.addAttribute("joke", joke.get());
            model.addAttribute("count", fetchJokeService.getNumberOfJokes());
            return "/index";
        } else
            return "/error";
    }
}
