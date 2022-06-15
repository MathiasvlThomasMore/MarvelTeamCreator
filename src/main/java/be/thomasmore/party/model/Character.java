package be.thomasmore.party.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Character {
    @Id
    private Integer id;
    private String characterName;
    private String powerType;
    @Column(length = 2000)
    private String bio;
    private boolean hero;
    private boolean villain;

    @ManyToMany(mappedBy = "characters",fetch = FetchType.LAZY)
    private Collection<Team> teams;

    public Character() {
    }

    public Character(int id, String name, String powerType, boolean hero,boolean villain) {
        this.id = id;
        this.characterName = name;
        this.powerType = powerType;
        this.hero = hero;
        this.villain = villain;
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

    public boolean isHero() {
        return hero;
    }

    public void setHero(boolean hero) {
        this.hero = hero;
    }

    public boolean isVillain() {
        return villain;
    }

    public void setVillain(boolean villain) {
        this.villain = villain;
    }
}
