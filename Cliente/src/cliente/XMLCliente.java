/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 *
 * @author ignaciocardenas
 */
public class XMLCliente {

    private String ruta = "Archivos/";
    //public Vector<Archivo> Varchivo = new Vector<Archivo>();

    public void cargaArchivos(VentanaCliente ventanita) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = builder.build("ListaArchivos.xml");
            Element raiz = doc.getRootElement();
            List listaarchivos = raiz.getChildren("archivo");
            Iterator k = listaarchivos.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                Element estado = e.getChild("estado");
                File f = new File(ruta + nombre.getText()); 
                //Element cantidad = e.getChild("cantidad");
                Element total = e.getChild("total");
                //Archivo archivo = new Archivo(nombre.getText(), estado.getText(), Long.parseLong(cantidad.getText()), Long.parseLong(total.getText()));
                //Varchivo.add(archivo);
                ventanita.agregarfila(nombre.getText(), f.length()/1000, Long.parseLong(total.getText()), estado.getText());
            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return Varchivo;
    }

    public class agregarArchivo extends Element {

        public agregarArchivo(Archivo archivo) {
            super("archivo");
            addContent(new Element("nombre").setText(archivo.getNombre()));
            addContent(new Element("estado").setText(archivo.getEstado()));
            //addContent(new Element("cantidad").setText(archivo.getCantidad().toString()));
            addContent(new Element("total").setText(archivo.getTotal().toString()));

        }
    }

    public void escribirArchivos(VentanaCliente ventanita) {
        int i = 0;
        Element root = new Element("archivos");

        for (i = 0; i < ventanita.getRowCount(); i++) {
            agregarArchivo nuevo = new agregarArchivo(ventanita.getArchivo(i));
            root.addContent(nuevo);
        }
        Document doc = new Document(root);
        try {
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            FileWriter writer = new FileWriter("ListaArchivos.xml");
//              FileWriter writer = new FileWriter("dist/ListaJugadores.xml");
            out.output(doc, writer);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("Problema con I/O al escribir el xml");
        } catch (Exception e) {
            System.out.println("Problema al escribir el xml");
        }
    }
}
