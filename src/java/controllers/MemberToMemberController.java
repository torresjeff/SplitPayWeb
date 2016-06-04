/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import negocio.FacadeMemberToMemberRemote;

/**
 *
 * @author Sid
 */
@Named(value = "memberToMemberController")
@SessionScoped
public class MemberToMemberController extends BaseController implements Serializable{

    //@EJB
    private FacadeMemberToMemberRemote facadeMemberToMember;

    /**
     * Creates a new instance of MemberToMemberController
     */
    public MemberToMemberController() {
        super();
        facadeMemberToMember = (FacadeMemberToMemberRemote) lookup("negocio.FacadeMemberToMemberRemote");
    }
    
    public String pagar() {
        
        return "paypal";
    }
    
}
