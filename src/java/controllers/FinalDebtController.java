/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import negocio.FacadeFinalDebtRemote;

/**
 *
 * @author Sid
 */
@Named(value = "finalDebtController")
@RequestScoped
public class FinalDebtController extends BaseController {

    private String groupName = "group name";
    
    //@EJB
    private FacadeFinalDebtRemote facadeFinalDebt;

    /**
     * Creates a new instance of FinalDebtController
     */
    public FinalDebtController() {
        super();
        facadeFinalDebt = (FacadeFinalDebtRemote) lookup("negocio.FacadeFinalDebtRemote");
    }
    
    public String llamarFinalDebt() {
        groupName = facadeFinalDebt.Hello();
        
        return "";
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    
}
