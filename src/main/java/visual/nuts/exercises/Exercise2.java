package visual.nuts.exercises;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import visual.nuts.exercises.dto.Country;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Exercise2 {

    public static void main(String[] args){
        JSONArray countriesJSONArray = getJSONDataFromFile("./src/main/resources/data.json");
        List<Country> countries = parseDataToCountryObj(countriesJSONArray);

        log.info("Countries in the world: ");
        logAnswer1(countries);

        log.info("The country with the most official languages, where they officially speak German (de):");
        logAnswer2(countries);

        log.info("Counts all the official languages spoken in the listed countries:");
        logAnswer3(countries);

        log.info("The country with the highest number of official languages : ");
        logAnswer4(countries);

        log.info("The most common official language(s), of all countries.: ");
        logAnswer5(countries);
    }

    private static void logAnswer1(List<Country> countries){
        log.info(countries.size()+"\n");
    }
    private static void logAnswer2(List<Country> countries){
        Optional<Country> answer2Find = countries.stream()
                .filter(country -> country.getLanguages().stream()
                        .filter("de"::equals)
                        .findAny().orElse(null) != null
                ).max(Comparator.comparing(c -> c.getLanguages().size()));

        String answer2 = answer2Find.isPresent() ? answer2Find.get().getCountry() : "Not found countries with official language DE";
        log.info(answer2+"\n");
    }

    private static void logAnswer3(List<Country> countries){
        countries.forEach(country -> {
            if(country.getLanguages().size() > 0){
                log.info("-------"+country.getCountry()+" speak "+country.getLanguages().size()+" languages");
            }
        });
        log.info("\n");
    }

    private static void logAnswer4(List<Country> countries){
        Optional<Country> answer4Find = countries.stream().max(Comparator.comparing(c -> c.getLanguages().size()));
        String answer4 = answer4Find.isPresent() ? answer4Find.get().getCountry() : "List is empty";
        log.info(answer4+"\n");
    }

    private static void logAnswer5(List<Country> countries){
        Map<Object, Long> allLangsCounts = countries.stream()
                .filter(it -> Objects.nonNull(it.getLanguages()))
                .flatMap(country -> country.getLanguages().stream())
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));

        Map.Entry<Object, Long> mostCount = allLangsCounts.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        log.info("The most common is : '"+ mostCount.getKey()+"' with "+mostCount.getValue()+" occurrences \n");
    }

    private static JSONArray getJSONDataFromFile(String path){
        try(FileReader fr = new FileReader(path)) {
            JSONParser parser=new JSONParser();
            return (JSONArray) parser.parse(fr);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Country> parseDataToCountryObj(JSONArray objCountries) {
        List<JSONObject> countriesJSONObject = (List<JSONObject>) objCountries;
        return countriesJSONObject.stream().map(Country::new).collect(Collectors.toList());
    }
}
