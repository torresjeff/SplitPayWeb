/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DTO.GetGroupsRequest;
import entities.Grupo;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import negocio.FacadeIndexRemote;

/**
 *
 * @author Sid
 */
@Named(value = "indexController")
@SessionScoped
public class IndexController extends BaseController implements Serializable {

    //@EJB
    private FacadeIndexRemote facadeIndex;
    private List<Grupo> grupos;
    private int groupId;
    private Grupo grupoSeleccionado;
    
    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
        super();
        facadeIndex = (FacadeIndexRemote) lookup("negocio.FacadeIndexRemote");
        
        GetGroupsRequest request = new GetGroupsRequest();
        request.userId = 2;
        grupos = facadeIndex.GetGroups(request).grupos;
    }
    
    public String sleccionarGrupo() {
        /*GetGroupsRequest request = new GetGroupsRequest();
        request.userId = 2;
        grupos = facadeIndex.GetGroups(request).grupos;*/
        //grupoSeleccionado = (Grupo)dataTable.getRowData();
        groupId = grupoSeleccionado.getId().intValue();
        
        return "finaldebt";
        
   }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Grupo getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }
    
    
    
    
    
}
