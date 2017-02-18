/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author juhlm
 */
@Entity
public class Skills implements Serializable {
    


    @Id @GeneratedValue(strategy= GenerationType.TABLE)
    private String id;
    private String skillName;
    private String description;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Applicant applicant;
    @OneToMany(mappedBy = "skill")
    private List<Rating> ratings = new ArrayList();


    public Skills() {
    }

    public Skills(String skillName, String description) {
        this.skillName = skillName;
        this.description = description;
      
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Applicant getPerson() {
        return applicant;
    }

    public void setPerson(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public void addRating(Rating rating){
        ratings.add(rating);
    }
}
