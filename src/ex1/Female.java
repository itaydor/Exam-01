package ex1;

public class Female extends Candidate{

    private final HairColor hairColor;

    public Female(String fullName,
                  int age,
                  Profession profession,
                  Trait dominantTrait,
                  Gender preferredGender,
                  int maxAge,
                  Profession preferredProfession,
                  Trait preferredTrait,
                  HairColor hairColor) {
        super(fullName,
                Gender.Female,
                age,
                profession,
                dominantTrait,
                preferredGender,
                maxAge,
                preferredProfession,
                preferredTrait);
        this.hairColor = hairColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    @Override
    public String toString() {
        return "Female{" +
                "fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", profession=" + profession +
                ", dominantTrait=" + dominantTrait +
                ", preferredGender=" + preferredGender +
                ", maxAge=" + maxAge +
                ", preferredProfession=" + preferredProfession +
                ", preferredTrait=" + preferredTrait +
                ", hairColor=" + hairColor +
                '}';
    }
}
