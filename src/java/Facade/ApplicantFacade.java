/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Applicant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author juhlm
 */
public class ApplicantFacade {
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
 public Applicant getApplicantFromDatabase(String id){
          
     EntityManager em = getEntityManager();
     Applicant applicant = null;
     
     try {
         applicant = em.find(Applicant.class, id);
     } finally {
         em.close();
     }
 
   return applicant;
 }
 
 public void insertApplicantInDatabase(Applicant applicant){
 
      EntityManager em = emf.createEntityManager();
    
      try {
         em.getTransaction().begin();
         em.persist(applicant);
         em.getTransaction().commit();
     } finally 
      {
          em.close();
     }        
 }
 
 public List<Applicant> getAllApplicants(){
 
     List<Applicant> applicantList;
     EntityManager em = emf.createEntityManager();
     try {
         
         applicantList = em.createQuery("SELECT a FROM Applicant a",Applicant.class).getResultList();
         return applicantList;
     } finally {
         em.close();
     } 
 }         
}
