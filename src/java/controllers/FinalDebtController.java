/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DTO.FinalDebtRequest;
import DTO.FinalDebtResponse;
import DTO.GetGroupUsersRequest;
import DTO.GetGroupUsersResponse;
import entities.Grupo;
import entities.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import negocio.FacadeFinalDebtRemote;

/**
 *
 * @author Sid
 */
@Named(value = "finalDebtController")
@SessionScoped
public class FinalDebtController extends BaseController implements Serializable {

    private String groupName = "group";
    private int grupoId;
    private List<Usuario> usuarios;
    private int totalDebt;
    //@EJB
    private FacadeFinalDebtRemote facadeFinalDebt;

    /**
     * Creates a new instance of FinalDebtController
     */
    public FinalDebtController() {
        super();
        facadeFinalDebt = (FacadeFinalDebtRemote) lookup("negocio.FacadeFinalDebtRemote");
        //grupos = facadeFinalDebt.
        
        //HttpServletRequest httpRequest =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //grupoId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("grupo"));
        //grupoId = Integer.parseInt(httpRequest.getParameter("grupo"));
        
        /*Map<String, String> params = FacesContext.getCurrentInstance().
                                        getExternalContext().getRequestParameterMap();
        grupoId = Integer.parseInt(params.get("grupo"));
        */
        
        //----
        
        
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap() != null)
            grupoId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grupo"));
        
        /*FinalDebtRequest request = new FinalDebtRequest();
        request.groupId = grupoId;
        FinalDebtResponse response = facadeFinalDebt.FinalDebtResolution(request);
        
        if (response.Succeeded()) {
            usuarios = response.usuarios;
        }*/
        
        findGroupUsers();
    }
    
    public void findGroupUsers() {
        GetGroupUsersRequest request = new GetGroupUsersRequest();
        request.groupId = grupoId;
        GetGroupUsersResponse response = facadeFinalDebt.GetGroupUsers(request);
        
        if (response.Succeeded()) {
            usuarios = response.usuarios;
        }
    }
    
    
    
    public String finalDebtResolution() {
        FinalDebtRequest request = new FinalDebtRequest();
        request.groupId = grupoId;
        FinalDebtResponse response = facadeFinalDebt.FinalDebtResolution(request);
        totalDebt = response.totalDebt;
        //return "confirmfinaldebt";
        return "finaldebt?grupo="+grupoId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGrupoId() {
        //return grupoId;
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("grupo"))
            grupoId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grupo"));
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(int totalDebt) {
        this.totalDebt = totalDebt;
    }
    
    
    
    
}
