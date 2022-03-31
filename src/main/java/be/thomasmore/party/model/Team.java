package be.thomasmore.party.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Team {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_generator")
    @SequenceGenerator(name = "team_generator", sequenceName = "team_seq", allocationSize = 1)
    @Id
    private Integer id;
    @Column(length = 100)
    private String teamName;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Character> characters;

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Collection<Character> character) {
        this.characters = character;
    }
}
