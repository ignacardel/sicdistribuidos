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
 *
 * @author ignaciocardenas
 */
public class XMLServidor {

   public ArrayList<String> cargaIPs() {
        ArrayList<String> listaips = new ArrayList<String>();
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = builder.build("ListaIPs.xml");
            Element raiz = doc.getRootElement();
            List listaarchivos = raiz.getChildren("ip");
            Iterator k = listaarchivos.iterator();
            while (k.hasNext()) {
                int i = 0;
                Element e = (Element) k.next();
                listaips.add(e.getText());
            }

        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaips;
    }

//    public class agregarIP extends Element {
//
//        public agregarIP(String ip) {
//            super("ips");
//            if (ip != null) {
//                addContent(new Element("ip").setText(ip));
//            } else {
//                addContent(new Element("ip").setText("null"));
//            }
//        }
//    }

    public void escribirIPs(String ip0, String ip1, String ip2) {
        int i = 0;
        Element root = new Element("ips");

        //agregarIP nuevo = new agregarIP(ip0);
        if (ip0 != null) {
            root.addContent(new Element("ip").setText(ip0));
        } else {
            root.addContent(new Element("ip").setText("null"));
        }

        if (ip1 != null) {
            root.addContent(new Element("ip").setText(ip1));
        } else {
            root.addContent(new Element("ip").setText("null"));
        }

        if (ip2 != null) {
            root.addContent(new Element("ip").setText(ip2));
        } else {
            root.addContent(new Element("ip").setText("null"));
        }

        Document doc = new Document(root);
        try {
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            FileWriter writer = new FileWriter("ListaIPs.xml");
            out.output(doc, writer);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("Problema con I/O al escribir el xml");
        } catch (Exception e) {
            System.out.println("Problema al escribir el xml");
        }
    }
}
