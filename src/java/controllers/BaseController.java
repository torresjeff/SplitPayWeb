/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Sid
 */
public class BaseController {
    private InitialContext context;
    private final String APP_SERVER_IP = "127.0.0.1";
    
    public BaseController() {
        
        //TODO: usar jndi.properties file para cargar los datos
        //Ver: http://wiki.netbeans.org/CreatingEJB3UsingNetbeansAndGlassfish
        try {
            Properties properties = new Properties();
            
            properties.put("org.omg.CORBA.ORBInitialHost", APP_SERVER_IP);
            properties.put("org.omg.CORBA.ORBInitialPort", "3700");
            //properties.load(new FileInputStream("jndi.properties"));
            //properties.load(BaseController.class.getResourceAsStream("/web/WEB-INF/jndi.properties"));
            context = new InitialContext(properties);
            
        } catch (NamingException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    protected Object lookup(String ejbName) {
        try {
            return context.lookup(ejbName);
        } catch (NamingException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
