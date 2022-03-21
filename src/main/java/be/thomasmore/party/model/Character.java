package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Character {
    @Id
    private Integer id;
    private String characterName;
    private String powerType;
    private String bio;
    private Boolean hero;

    public Character() {
    }

    public Character(int id, String name, String powerType, Boolean hero) {
        this.id = id;
        this.characterName = name;
        this.powerType = powerType;
        this.hero = hero;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public Boolean getHero() {
        return hero;
    }

    public void setHero(Boolean hero) {
        this.hero = hero;
    }
}
