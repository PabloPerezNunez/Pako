package es.ppn.pako.Negocio;

import org.json.JSONObject;
public class Trabajo {

    private String cliente;
    private String lugar;
    private String concejo;
    private String telefono1;
    private String telefono2;
    private String categoria;
    private String trabajo;
    private String horario;

    private JSONObject json;
    private int id,prioridad;
    private boolean realizado;

    public Trabajo(JSONObject item) {
        try {

            this.json = item;

            this.cliente = item.getString("cliente");
            this.lugar = item.getString("lugar");
            this.concejo = item.getString("poblacion");
            this.telefono1 = item.getString("telefono");
            this.telefono2 = item.getString("telefono2");
            this.categoria =  item.getString("categoria");
            this.trabajo = item.getString("trabajo");
            this.horario = item.getString("horario");

            this.id =  item.getInt("id");

            this.prioridad =  item.getInt("prioridad");
            this.realizado = item.getBoolean("realizado");

        }catch(Exception e){

        }

    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
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
