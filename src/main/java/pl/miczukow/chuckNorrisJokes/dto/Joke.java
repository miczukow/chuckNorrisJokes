package pl.miczukow.chuckNorrisJokes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@NoArgsConstructor
@Setter
@Getter
public class Joke {
    String category;
    URL icon_url;
    String id;
    URL url;
    String value;

    @Override
    public String toString() {
        return "Joke{" +
                "category='" + category + '\'' +
                ", icon_url=" + icon_url +
                ", id='" + id + '\'' +
                ", url=" + url +
                ", value='" + value + '\'' +
                '}';
    }
}
