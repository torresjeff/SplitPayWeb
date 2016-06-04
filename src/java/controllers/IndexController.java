/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DTO.GetGroupsRequest;
import DTO.MailMessage;
import entities.Grupo;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import negocio.FacadeIndexRemote;

/**
 *
 * @author Sid
 */
@Named(value = "indexController")
@SessionScoped
public class IndexController extends BaseController implements Serializable {

    @Resource(mappedName = "jms/queueCorreos")
    private Queue queueCorreos;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

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
        
        //sendJMSMessageToQueueCorreos();
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

    private void sendJMSMessageToQueueCorreos(MailMessage messageData) {
        context.createProducer().send(queueCorreos, messageData);
    }
    
    
    
    
    
}
