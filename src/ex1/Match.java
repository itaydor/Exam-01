package ex1;

import java.util.Objects;

public record Match(Candidate candidate1, Candidate candidate2) {

    public int calcScore(){
        int score = 0;
        if(candidate1.getPreferredProfession() == candidate2.profession && candidate2.getPreferredProfession() == candidate1.profession)
            score++;
        if(candidate1.getPreferredTrait() == candidate2.dominantTrait && candidate2.getPreferredTrait() == candidate1.dominantTrait)
            score++;
        if(candidate1.getMaxAge() >= candidate2.age && candidate2.getMaxAge() >= candidate1.age)
            score++;
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match match)) return false;
        return (Objects.equals(candidate1, match.candidate1) && Objects.equals(candidate2, match.candidate2)) ||
                (Objects.equals(candidate1, match.candidate2) && Objects.equals(candidate2, match.candidate1));
    }

    @Override
    public String toString() {
        return "Match{" +
                candidate1.fullName +
                " and " + candidate2.fullName +
                ", score = " + calcScore() +
                '}';
    }
}
