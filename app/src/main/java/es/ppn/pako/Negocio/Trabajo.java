package es.ppn.pako.Negocio;

import org.json.JSONObject;

/**
 * Created by pp_1_ on 29/11/2016.
 */
public class Trabajo {

    String cliente, lugar, concejo, telefono1, telefono2,categoria, trabajo;
    int id,prioridad;
    boolean realizado;


    public Trabajo(){

    }

    public Trabajo(String cliente, String lugar, String concejo,  String telefono1, String telefono2, String categoria, String trabajo, int id, int prioridad, boolean realizado) {
        this.cliente = cliente;
        this.lugar = lugar;
        this.concejo = concejo;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.categoria = categoria;
        this.trabajo = trabajo;
        this.id = id;
        this.prioridad = prioridad;
        this.realizado = realizado;
    }

    public Trabajo(JSONObject item) {
        try {

            this.cliente = item.getString("cliente");
            this.lugar = item.getString("lugar");
            this.concejo = item.getString("poblacion");
            this.telefono1 = item.getString("telefono");
            this.telefono2 = item.getString("telefono2");
            this.categoria =  item.getString("categoria");
            this.trabajo = item.getString("trabajo");
            this.id =  item.getInt("id");
            this.prioridad =  item.getInt("prioridad");
            this.realizado = item.getBoolean("realizado");

        }catch(Exception e){

        }

    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getConcejo() {
        return concejo;
    }

    public void setConcejo(String concejo) {
        this.concejo = concejo;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}
