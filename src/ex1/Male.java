package ex1;

public class Male extends Candidate{

    private final int height;


    public Male(String fullName,
                int age,
                Profession profession,
                Trait dominantTrait,
                Gender preferredGender,
                int maxAge,
                Profession preferredProfession,
                Trait preferredTrait,
                int height) {
        super(fullName,
                Gender.Male,
                age,
                profession,
                dominantTrait,
                preferredGender,
                maxAge,
                preferredProfession,
                preferredTrait);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Male{" +
                "fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", profession=" + profession +
                ", dominantTrait=" + dominantTrait +
                ", preferredGender=" + preferredGender +
                ", maxAge=" + maxAge +
                ", preferredProfession=" + preferredProfession +
                ", preferredTrait=" + preferredTrait +
                ", height=" + height +
                '}';
    }
}
