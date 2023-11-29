package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;

import java.util.List;

public class CuentasDBRepo implements ICuentasRepo{

    @Override
    public List<Cuenta> getAll() {
        return null;
    }

    @Override
    public Cuenta getAccountById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Cuenta addAccount(Cuenta cuenta) throws Exception {
        return null;
    }

    @Override
    public boolean deleteAccount(Cuenta cuenta) throws Exception {
        return false;
    }

    @Override
    public Cuenta updateAccount(Cuenta cuenta) throws Exception {
        return null;
    }

    @Override
    public List<Cuenta> getAccountsByClient(Integer uid) throws Exception {
        return null;
    }

    @Override
    public Cuenta getAccountsByClientAndId(Integer uid, Integer aid) throws Exception {
        return null;
    }
}
