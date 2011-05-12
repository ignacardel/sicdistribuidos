package servidor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cotu
 */
public class Ips {

   private String ip0 = "null";
   private String ip1 = "null";
   private String ip2 = "null";


    public String getIp0() {
        return ip0;
    }

    public void setIp0(String ip0) {
        this.ip0 = ip0;
        System.out.println("setee ip0: "+this.ip0);
    }

    public String getIp1() {
        return ip1;
    }

    public void setIp1(String ip1) {
        this.ip1 = ip1;
        System.out.println("setee ip1: "+this.ip1);
    }

    public String getIp2() {
        return ip2;
    }

    public void setIp2(String ip2) {
        this.ip2 = ip2;
        System.out.println("setee ip2: "+this.ip2);
    }

    public void imprimirips(){
        System.out.println("Tabla");
        System.out.println("Ip0: "+ip0);
        System.out.println("Ip1: "+ip1);
        System.out.println("Ip2: "+ip2);
    }

    public String getips(){
        return this.ip0+"/"+this.ip1+"/"+this.ip2;
    }
    public void setallips(String ip0, String ip1, String ip2 )
    {
    this.ip0= ip0;
    this.ip1= ip1;
    this.ip2= ip2;
    }
}
