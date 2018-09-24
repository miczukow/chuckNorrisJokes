package pl.miczukow.chuckNorrisJokes.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Service;
import pl.miczukow.chuckNorrisJokes.dto.Joke;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FetchJokeService {

    private static final String SOURCE = "https://api.chucknorris.io/jokes/random";
    private static final Logger LOG = Logger.getLogger(FetchJokeService.class.getName());
    private Set<String> jokeIds = new HashSet<>();

    public Optional<Joke> getJoke() {
        Joke joke = null;
        boolean repeat = false;
        int repeatCount = 0;

//        LOG.setUseParentHandlers(false);
        do {
            try {
                joke = new Gson().fromJson(getJsonFromSource(), Joke.class);
                repeat = jokeIds.add(joke.getId());
            } catch (IOException e) {
                LOG.log(Level.INFO, "IOException caught: ", e);
                repeatCount++;
            }
        } while (!repeat && repeatCount < 3);

        return Optional.ofNullable(joke);
    }

    private String getJsonFromSource() throws IOException {
        URL url = new URL(SOURCE);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "");
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
        String result = in.readLine();

        in.close();
        connection.disconnect();
        return result;
    }

    public int getNumberOfJokes() {
        return jokeIds.size();
    }
}
