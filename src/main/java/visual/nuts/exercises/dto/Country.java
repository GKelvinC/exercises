package visual.nuts.exercises.dto;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;


@Getter @Setter
public class Country {

    String country;

    List<String> languages;

    public Country(JSONObject countryJSONObject) {
        this.country = countryJSONObject.get("country").toString();
        List<String> languagesJSONString = (List<String>) countryJSONObject.get("languages");
        this.languages = languagesJSONString.stream().map(String::toString).collect(Collectors.toList());
    }
}
