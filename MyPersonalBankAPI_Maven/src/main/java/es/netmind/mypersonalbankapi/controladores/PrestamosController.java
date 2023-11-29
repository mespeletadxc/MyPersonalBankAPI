package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.PrestamoException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepo;
import es.netmind.mypersonalbankapi.persistencia.PrestamosInMemoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamosController implements IPrestamosController {
    @Autowired
    private IPrestamosRepo prestamosRepo;
     @Autowired
    private IClientesRepo clientesRepo;

    @Override
    public void mostrarLista(Integer uid) {
        System.out.println("\nLista de prestamos del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Prestamo> prestamos = prestamosRepo.getLoansByClient(uid);
            if (prestamos != null && prestamos.size() > 0) System.out.println(prestamos);
            else System.out.println("El cliente no tiene prestamos!");
        } catch (PrestamoException e) {
            System.out.println("Cliente NO encontrado 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }

    @Override
    public void mostrarDetalle(Integer uid, Integer lid) {
        System.out.println("\nDetalle de prestamo: " + lid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Prestamo prestamo = prestamosRepo.getLoansByClientAndId(uid, lid);
            System.out.println(prestamo);
        } catch (PrestamoException e) {
            System.out.println("Prestamo NO encontrado para el cliente 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }

    @Override
    public void eliminar(Integer uid, Integer lid) {
        System.out.println("\nBorrando prestamo: " + lid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Prestamo pr = prestamosRepo.getLoansByClientAndId(uid, lid);
            boolean borrado = prestamosRepo.deleteLoan(pr);
            if (borrado) {
                Cliente cl = clientesRepo.getClientById(uid);
                cl.delisgarPrestamo(pr);
                System.out.println("Préstamo borrada 🙂!!");
                mostrarLista(uid);
            } else System.out.println("Préstamo NO borrado 😞!! Consulte con su oficina.");
        } catch (PrestamoException e) {
            System.out.println("Préstamo NO encontrado 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    @Override
    public void add(Integer uid, String[] args) {
        System.out.println("uid: " + uid);
        for (String arg : args) {
            System.out.println(arg);
        }
        // TODO
    }

    @Override
    public void actualizar(Integer uid, Integer lid, String[] args) {
        System.out.println("uid: " + uid);
        System.out.println("lid: " + lid);
        for (String arg : args) {
            System.out.println(arg);
        }
        // TODO
    }
}
