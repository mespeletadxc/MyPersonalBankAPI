package es.netmind.mypersonalbankapi.controladores;

public interface IPrestamosController {
    void mostrarLista(Integer uid);

    void mostrarDetalle(Integer uid, Integer lid);

    void eliminar(Integer uid, Integer lid);

    void add(Integer uid, String[] args);

    void actualizar(Integer uid, Integer lid, String[] args);
}
