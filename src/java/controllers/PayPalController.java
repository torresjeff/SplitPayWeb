/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integracion.LoginPayPalResponse;
import integracion.LoginRequest;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import negocio.FacadeMemberToMemberRemote;

/**
 *
 * @author Sid
 */
@Named(value = "payPalController")
@SessionScoped
public class PayPalController extends BaseController implements Serializable {

    //@EJB
    private FacadeMemberToMemberRemote facadeMemberToMember;
    
    private String cc;
    private String password;
    private String loggeado;
    /**
     * Creates a new instance of PayPalController
     */
    public PayPalController() {
        super();
        facadeMemberToMember = (FacadeMemberToMemberRemote) lookup("negocio.FacadeMemberToMemberRemote");
        loggeado = "Por favor inicie sesion";
    }
    
    public String login() {
        LoginRequest request = new LoginRequest();
        request.setUser(cc);
        request.setPassword(password);
        
        LoginPayPalResponse response = facadeMemberToMember.LoginPayPal(request);
        if (response.getOperationError() == null) {
            loggeado = "Ya inicio sesion";
            //return "pagarpaypal";
        }
        else {
            loggeado = response.getOperationError();
        }
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

    public String getLoggeado() {
        return loggeado;
    }

    public void setLoggeado(String loggeado) {
        this.loggeado = loggeado;
    }
    
    
    
    
    
    
}
