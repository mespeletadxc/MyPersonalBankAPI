package es.netmind.mypersonalbankapi.controladores;

public interface ICuentasController {
    void mostrarLista(Integer uid);

    void mostrarDetalle(Integer uid, Integer aid);

    void eliminar(Integer uid, Integer aid);

    void add(Integer uid, String[] args);

    void actualizar(Integer uid, Integer aid, String[] args);

    void setCuentasRepo(es.netmind.mypersonalbankapi.persistencia.ICuentasRepo cuentasRepo);

    void setClientesRepo(es.netmind.mypersonalbankapi.persistencia.IClientesRepo clientesRepo);
}
