/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.google.gson.Gson;
import controlador.CtrlApiRedSocial;
import entidades.JSONResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author USUARIO
 */
@Path("redSocial")
@Produces(MediaType.APPLICATION_JSON)
public class ApiRedSocialRest {
    
    @Context
    private UriInfo context;
    private final ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public void createRegistro(@Suspended
    final AsyncResponse asyncResponse, @QueryParam(value = "redSocial")
    final String redSocial, @QueryParam(value = "informacion")
    final String informacion){
        
        executorService.submit(() -> {
            asyncResponse.resume(doCreateRegistro(redSocial, informacion));
        });
    }

    private String doCreateRegistro(String redSocial, String informacion) {
        CtrlApiRedSocial ctrl = new CtrlApiRedSocial();
        
        JSONResponse json = ctrl.createRegistro(redSocial, informacion);
        Gson gson = new Gson();
        return gson.toJson(json);
    }
    
    @GET
    @Path("/prueba")
    @Produces(MediaType.APPLICATION_JSON)
    public String test( @QueryParam(value = "test") String test ){
        return "Hola " + test;
    }
    
    @GET
    @Path("/searchLast")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchLast( @QueryParam(value = "redSocial") String redSocial ){
        CtrlApiRedSocial ctrl = new CtrlApiRedSocial();
        JSONResponse json = ctrl.getLastInformacion(redSocial);
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
