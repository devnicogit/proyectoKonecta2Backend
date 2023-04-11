package com.tutorial.crud.service;

import com.tutorial.crud.repository.ClienteRepository;
import com.tutorial.crud.repository.PlanPostpagoRepository;
import com.tutorial.crud.repository.TelefonoRepository;
import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.swagger.entity.Telefono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefonoServiceImpl implements TelefonoService{

    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private TelefonoRepository telefonoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PlanPostpagoRepository planPostpagoRepository;

    @Override
    public List<Telefono> findAll() {
        return telefonoRepository.findAll();
    }

    @Override
    public Telefono findById(Long id) {
        return telefonoRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Telefono> findByIds(Long id) {
        return telefonoRepository.findById(id);
    }

    @Override
    public Telefono save(Telefono telefono) {
        return telefonoRepository.save(telefono);
    }



    /*@Override
    public Telefono update(Long id, Telefono telefono) {
        logger.debug("Iniciando método update");
        Optional<Telefono> telefonoEncontrado = telefonoRepository.findById(id);
        if (telefonoEncontrado.isPresent()) {
            Telefono telefonoActualizado = telefonoEncontrado.get();
            telefonoActualizado.setNumero(telefono.getNumero());

            PlanPostpago plan = telefono.getPlan();
            if (plan != null) {
                Optional<PlanPostpago> planEncontrado = planPostpagoRepository.findById(plan.getPlanId());
                if (planEncontrado.isPresent()) {
                    telefonoActualizado.setPlan(planEncontrado.get());
                } else {
                    throw new IllegalArgumentException("Plan no encontrado");
                }
            }

            Cliente cliente= telefono.getCliente();
            if (cliente != null) {
                Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getClienteId());
                if (clienteEncontrado.isPresent()) {
                    telefonoActualizado.setCliente(clienteEncontrado.get());
                } else {
                    throw new IllegalArgumentException("Cliente no encontrado");
                }
            }


            return telefonoRepository.save(telefonoActualizado);
        } else {
            throw new IllegalArgumentException("Teléfono no encontrado");
        }
    }*/

    @Override
    public Telefono update(Long id, Telefono telefono) {
        Telefono existingTelefono = telefonoRepository.findById(id).orElse(null);
        if (existingTelefono == null) {
            return null;
        }
        existingTelefono.setNumero(telefono.getNumero());
        existingTelefono.setPlan(telefono.getPlan());
        existingTelefono.setCliente(telefono.getCliente());
        Telefono updatedTelefono = telefonoRepository.save(existingTelefono);
        return updatedTelefono;
    }

    @Override
    public List<Telefono> findByCliente(Cliente cliente) {
        return telefonoRepository.findByCliente(cliente);
    }



    @Override
    public void delete(Long id) {
        telefonoRepository.deleteById(id);
    }
}
