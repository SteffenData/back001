/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author juhlm
 */
@WebListener
public class DeploymentConfiguration implements ServletContextListener {

    public static String PU_NAME = "PU-Local";

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    Map<String, String> env = System.getenv();
    //If we are running in the OPENSHIFT environment change the pu-name 
    if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
      PU_NAME = "PU_OPENSHIFT";
    }
      try {
      ServletContext context = sce.getServletContext();
      EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
      EntityManager em = emf.createEntityManager();
          
      } catch (Exception e) {
      }
  }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
