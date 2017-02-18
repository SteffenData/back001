/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Applicant;
import Entity.Skills;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author juhlm
 */
public class SkillsFacade {
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
  
public void addSkillToApplicant(Skills skill, String id){

    EntityManager em = emf.createEntityManager();
    
    try {
        Applicant applicant = em.find(Applicant.class, id);
        
        skill.setPerson(applicant);
        applicant.addSkill(skill);
        
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();
             
    } finally{
        em.close();
    }
}

public List<Skills> getAllSkillsForApplicant(String applicantId){

    EntityManager em = emf.createEntityManager();
    List<Skills> skillslist;
    
    try {
               TypedQuery<Skills> s = em.createQuery("select s from Skills s where s.applicant.id =:applicantId", Skills.class);
               s.setParameter("applicantId", applicantId);
               skillslist = s.getResultList();
               
    } finally{
        em.close();
    }
            return skillslist;
}
}
