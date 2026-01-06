package com.nicodev.MiBazarProyecto.service;

import com.nicodev.MiBazarProyecto.exception.NotFoundException;
import com.nicodev.MiBazarProyecto.model.Cliente;
import com.nicodev.MiBazarProyecto.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepo;

    // POST un cliente.
    @Override
    public void saveCliente(Cliente cliente) {
          clienteRepo.save(cliente);
    }

    // GET lista completa de clientes.
    @Override
    public List<Cliente> getListaClientes() {
        return clienteRepo.findAll();
    }

    // GET un cliente (por id)
    @Override
    public Cliente getCliente(Long cliente_id) {
        return clienteRepo.findById(cliente_id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    // DELETE un cliente (por id)
    @Override
    public void deleteCliente(Long cliente_id) {
        if(!clienteRepo.existsById(cliente_id)){
            throw new NotFoundException("Cliente a eliminar no encontrado");
        }
        clienteRepo.deleteById(cliente_id);
    }

    // PUT/EDIT un cliente (por id)
    @Override
    public void editCliente(Long cliente_id, String nombre_nue, String apellido_nue, Long dni_nue) {
       // busco el Cliente por el ID
        Cliente cliente = this.getCliente(cliente_id);

        // modifico los valores requeridos.
        if (nombre_nue != null) cliente.setNombre(nombre_nue);
        if (apellido_nue != null) cliente.setApellido(apellido_nue);
        if (dni_nue != null) cliente.setDni(dni_nue);

        // guardo los nuevo valores
        this.saveCliente(cliente);
    }


}
