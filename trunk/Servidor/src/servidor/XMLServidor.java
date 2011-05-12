/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 *clase para manejar la carga del archivo xml en donde se guarda la lista
 * de archivios disponibles
 * @author ignaciocardenas
 */
public class XMLServidor {


/**
 * metodo con el cual se lee el archivo y se obtiene la lista de archivos.
 * @return
 */
    public String cargaArchivos() {
        String listafiles = "";
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = builder.build("ListaArchivos.xml");
            Element raiz = doc.getRootElement();
            List listaarchivos = raiz.getChildren("archivo");
            Iterator k = listaarchivos.iterator();
            while (k.hasNext()) {
                int i = 0;
                Element e = (Element) k.next();
                listafiles = listafiles + e.getText() + "/";
            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listafiles;
    }

}
