package com.nicodev.MiBazarProyecto.service;

import com.nicodev.MiBazarProyecto.model.Cliente;

import java.util.List;

public interface IClienteService {

    // POST un cliente.
    public void saveCliente(Cliente cliente);

    // GET lista completa de clientes.
    public List<Cliente> getListaClientes();

    // GET un cliente (por id)
    public Cliente getCliente(Long cliente_id);

    // DELETE un cliente (por id)
    public void deleteCliente(Long cliente_id);

    // PUT/EDIT un cliente (por id)
    public void editCliente(Long cliente_id, String nombre_nue, String apellido_nue, Long dni_nue);

}
