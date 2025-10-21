package com.nicodev.MiBazarProyecto.controller;

import com.nicodev.MiBazarProyecto.model.Cliente;
import com.nicodev.MiBazarProyecto.model.Venta;
import com.nicodev.MiBazarProyecto.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService clienteServ;

    // POST un cliente.
    @PostMapping("clientes/crear")
    public String saveCliente(@RequestBody Cliente cliente) {
        clienteServ.saveCliente(cliente);
        return "Cliente creado correctamente";
    }

    // GET lista completa de clientes.
    @GetMapping("clientes")
    public List<Cliente> getListaClientes() {
        return clienteServ.getListaClientes();
    }

    // GET un cliente (por id)
    @GetMapping("clientes/{cliente_id}")
    public Cliente getCliente(@PathVariable Long cliente_id) {
        return clienteServ.getCliente(cliente_id);
    }

    // DELETE un cliente (por id)
    @DeleteMapping("clientes/eliminar/{cliente_id}")
    public String deleteCliente(@PathVariable Long cliente_id) {
        clienteServ.deleteCliente(cliente_id);
        return "Cliente eliminado correctamente";
    }

    // PUT/EDIT un cliente (por id)




}
