package com.deposito;

import java.net.HttpURLConnection;
import java.net.URL;

public class JAXRSCliente {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/FabricaWeb/rest/service/hola");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        if(urlConnection.getResponseCode() != 200) {
            throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
        }

    }
	
}
