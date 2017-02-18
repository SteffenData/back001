/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Applicant;
import Entity.Rating;
import Entity.Skills;
import java.util.ArrayList;
import java.util.List;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

/**
 *
 * @author juhlm
 */
public class Tester {
    
    public static void main(String[] args) {
        
        ApplicantFacade test = new ApplicantFacade();
        SkillsFacade skillFacade = new SkillsFacade();
 
        RatingFacade rf = new RatingFacade();
        
        Rating r = new Rating(5, "damm good");
        
        rf.insertRatingToSkill(r, "951");
        
        System.out.println("dd" + rf.getRatingForOneSkill("951").toString());
     
        }
    }

