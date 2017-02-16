/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.OneToMany;



/**
 *
 * @author juhlm
 */
@Entity
public class Skills implements Serializable {
    


    @Id @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;
    private String skillName;
    private String description;
    @ManyToOne
    private Person person;
    @OneToMany(mappedBy = "skill")
    private List<Rating> ratings;


    public Skills() {
    }

    public Skills(Long id, String skillName, String description, Person person, List<Rating> ratings) {
        this.id = id;
        this.skillName = skillName;
        this.description = description;
        this.person = person;
        this.ratings = ratings;
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
 
}
