package ex1;

public abstract class Candidate {

    protected String fullName;
    protected Gender gender;
    protected int age;
    protected Profession profession;
    protected Trait dominantTrait;
    protected Gender preferredGender;
    protected int maxAge;
    protected Profession preferredProfession;
    protected Trait preferredTrait;

    public Candidate(String fullName,
                     Gender gender,
                     int age,
                     Profession profession,
                     Trait dominantTrait,
                     Gender preferredGender,
                     int maxAge,
                     Profession preferredProfession,
                     Trait preferredTrait) {
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.profession = profession;
        this.dominantTrait = dominantTrait;
        this.preferredGender = preferredGender;
        this.maxAge = maxAge;
        this.preferredProfession = preferredProfession;
        this.preferredTrait = preferredTrait;
    }

    //getters

    public String getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Profession getProfession() {
        return profession;
    }

    public Trait getDominantTrait() {
        return dominantTrait;
    }

    public Gender getPreferredGender() {
        return preferredGender;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public Profession getPreferredProfession() {
        return preferredProfession;
    }

    public Trait getPreferredTrait() {
        return preferredTrait;
    }

    public static boolean isValidMatch(Candidate c1, Candidate c2){
        return c1.getPreferredGender() == c2.gender && c2.getPreferredGender() == c1.gender;
    }
}
