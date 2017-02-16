/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author juhlm
 */
@Entity
public class Rating implements Serializable {
    
    @Id @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;
    private int score;
    private String comment;
    @ManyToOne
    private Skills skill;

    public Rating() {
    }

    public Rating(long id, int score, String comment, Skills skill) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.skill = skill;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Skills getSkill() {
        return skill;
    }

    public void setSkill(Skills skill) {
        this.skill = skill;
    }
    
    
}
