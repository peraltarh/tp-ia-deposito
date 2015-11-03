package com.deposito;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import controlador.ControladorFabrica;

@ApplicationPath("/rest")
public class JAXRSApplication extends Application{
}
