package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.CuentaException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.CuentasInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.netmind.mypersonalbankapi.controladores.ClientesController;

import java.util.List;

@Service
@Setter
public class CuentasController implements ICuentasController {

    @Autowired
    private ICuentasRepo cuentasRepo;
    @Autowired
    private IClientesRepo clientesRepo;
//    public void setClientesRepo(IClientesRepo clientesRepo) {
//        ClientesController.setClientesRepo(clientesRepo);
//    }
    //private IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();

    @Override
    public void mostrarLista(Integer uid) {
        System.out.println("\nLista de cuentas del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Cuenta> cuentas = cuentasRepo.getAccountsByClient(uid);
            if (cuentas != null && cuentas.size() > 0) System.out.println(cuentas);
            else System.out.println("El cliente no tiene cuentas!");
        } catch (Exception e) {
            System.out.println("Cliente NO encontrado 😞!");
        }
    }

    @Override
    public void mostrarDetalle(Integer uid, Integer aid) {
        System.out.println("\nDetalle de cuenta: " + aid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cuenta cuenta = cuentasRepo.getAccountsByClientAndId(uid, aid);
            System.out.println(cuenta);
        } catch (Exception e) {
            System.out.println("Cuenta NO encontrada para el cliente 😞!");
        }
    }

    @Override
    public void eliminar(Integer uid, Integer aid) {
        System.out.println("\nBorrando cuenta: " + aid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cuenta cu = cuentasRepo.getAccountsByClientAndId(uid, aid);
            boolean borrado = cuentasRepo.deleteAccount(cu);
            if (borrado) {
                Cliente cl = clientesRepo.getClientById(uid);
                cl.delisgarCuenta(cu);
                System.out.println("Cuenta borrada 🙂!!");
                mostrarLista(uid);
            } else System.out.println("Cuenta NO borrada 😞!! Consulte con su oficina.");
        } catch (CuentaException e) {
            System.out.println("Cuenta NO encontrado 😞! \nCode: " + e.getCode());
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
    }

    @Override
    public void actualizar(Integer uid, Integer aid, String[] args) {
        System.out.println("uid: " + uid);
        System.out.println("aid: " + aid);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
