/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Rating;
import Entity.Skills;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author juhlm
 */
public class RatingFacade {
    
       
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void insertRatingToSkill(Rating rating,String id)
    {
         EntityManager em = emf.createEntityManager();
         
         try {
             
         Skills skill = em.find(Skills.class, id);
         rating.setSkill(skill);
         skill.addRating(rating);
         
         em.getTransaction().begin();
         em.persist(rating);
         em.getTransaction().commit();
         
            
        } finally {
             em.close();
        }
    }
    
    public List<Rating> getRatingForOneSkill(String skillId){
    
        EntityManager em = emf.createEntityManager();
        List<Rating> ratingList = new ArrayList();
        
          try {
               TypedQuery<Rating> r = em.createQuery("select r from Rating r where r.skill.id =:skillId", Rating.class);
               r.setParameter("skillId", skillId);
               ratingList = r.getResultList();
               
    } finally{
        em.close();
    }
        return ratingList;
    }
}
