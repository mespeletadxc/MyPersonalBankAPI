package es.netmind.mypersonalbankapi.controladores;

public interface IClientesController {
    void mostrarLista() throws Exception;

    void mostrarDetalle(Integer uid);

    void add(String[] args);

    void eliminar(Integer uid);

    void actualizar(Integer uid, String[] args);

    void evaluarPrestamo(Integer uid, Double cantidad);

    void setClientesRepo(es.netmind.mypersonalbankapi.persistencia.IClientesRepo clientesRepo);

    void setCuentasRepo(es.netmind.mypersonalbankapi.persistencia.ICuentasRepo cuentasRepo);

    void setPrestamosRepo(es.netmind.mypersonalbankapi.persistencia.IPrestamosRepo prestamosRepo);
}
