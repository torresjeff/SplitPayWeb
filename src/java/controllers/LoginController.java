/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DTO.LoginSplitPayRequest;
import DTO.LoginSplitPayResponse;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import negocio.FacadeLoginRemote;

/**
 *
 * @author Sid
 */
@Named(value = "loginController")
@RequestScoped
public class LoginController extends BaseController implements Serializable {

    //@EJB
    private FacadeLoginRemote facadeLogin;
    
    
    private String inicieSesion = "Por favor inicie sesión";
    private String cc;
    private String password;
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        super();
        facadeLogin = (FacadeLoginRemote) lookup("negocio.FacadeLoginRemote");
    }
    
    public String login() {
        LoginSplitPayRequest request = new LoginSplitPayRequest();
        request.cc = cc;
        request.password = password;
        
        LoginSplitPayResponse response = facadeLogin.Login(request);
        if (response.operationError == null) {
            inicieSesion = "Ya inicio sesion";
            return "index";
        }
        inicieSesion = response.operationError;
        return "";
        
        
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInicieSesion() {
        return inicieSesion;
    }

    public void setInicieSesion(String inicieSesion) {
        this.inicieSesion = inicieSesion;
    }
    
    
    
    
    
}
