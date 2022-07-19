package ex1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MatchingApp {

    public static void main(String[] args) {
        List<Candidate> candidates = readCandidatesFromFile();

        List<Match> matches = makeMatches(candidates);

        matches.sort(Collections.reverseOrder(Comparator.comparingInt(Match::calcScore)));

        matches.forEach(System.out::println);
    }

    /**
     * Make the matches
     * @param candidates all candidates
     * @return list of matches
     */
    private static List<Match> makeMatches(List<Candidate> candidates) {
        List<Match> matches = new ArrayList<>();
        candidates.forEach(c1 -> candidates.stream()
                .filter(c2 -> c2 != c1)
                .filter(c2 -> Candidate.isValidMatch(c1, c2))
                .filter(c2 -> !matches.contains(new Match(c1, c2)))
                .forEach(c2 -> matches.add(new Match(c1, c2))));
        return matches;
    }

    /**
     * Read candidates.txt and creates candidates/
     * @return list of candidates
     */
    private static List<Candidate> readCandidatesFromFile() {
        List<Candidate> candidates = new ArrayList<>();
        File file = new File("candidates.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            br.readLine(); //read the header
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitedLine = line.split(",");
                candidates.add(buildCandidate(splitedLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    /**
     * build candidate according the parameters in the line.
     * @param splitedLine
     * @return candidate
     */
    private static Candidate buildCandidate(String[] splitedLine) {
        //read all properties.
        String fullName = splitedLine[0];
        Gender gender = stringToEnum(Gender.class, splitedLine[1]);
        int age = Integer.parseInt(splitedLine[2]);
        Profession profession = stringToEnum(Profession.class, splitedLine[3]);
        Trait dominantTrait = stringToEnum(Trait.class, splitedLine[4]);
        Gender preferredGender = stringToEnum(Gender.class, splitedLine[5]);
        int maxAge = Integer.parseInt(splitedLine[6]);
        Profession preferredProfession = stringToEnum(Profession.class, splitedLine[7]);
        Trait preferredTrait = stringToEnum(Trait.class, splitedLine[8]);

        Candidate candidate = null;

        //create male/female according the gender.
        switch (gender){
            case Male -> {
                int height = Integer.parseInt(splitedLine[9]);
                candidate = new Male(fullName, age, profession, dominantTrait, preferredGender, maxAge, preferredProfession, preferredTrait, height);
            }
            case Female -> {
                HairColor hairColor = stringToEnum(HairColor.class, splitedLine[9]);
                candidate = new Female(fullName, age, profession, dominantTrait, preferredGender, maxAge, preferredProfession, preferredTrait, hairColor);
            }
        }
        return candidate;
    }

    /**
     * @param enumClass the type to look at
     * @param value string representing the enum value
     * @param <T> enum value
     * @return the value from the enum the string represent
     */
    public static <T extends Enum<T>> T stringToEnum(Class<T> enumClass, String value) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if(value.equals(enumValue.toString())){
                return enumValue;
            }
        }
        // we can assume the input is legal, so we will never get here.
        return null;
    }
}
