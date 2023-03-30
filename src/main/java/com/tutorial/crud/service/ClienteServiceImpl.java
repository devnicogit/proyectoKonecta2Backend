package com.tutorial.crud.service;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.TipoClienteDto;
import com.tutorial.crud.entity.Cliente;
import com.tutorial.crud.entity.PlanPostpago;
import com.tutorial.crud.entity.TipoCliente;
import com.tutorial.crud.repository.ClienteRepository;
import com.tutorial.crud.repository.PlanPostpagoRepository;
import com.tutorial.crud.repository.TipoClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PlanPostpagoRepository planPostpagoRepository;

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /*@Override
    public ClienteDto save(ClienteDto clienteDto) {
        TipoCliente tipoCliente = tipoClienteRepository.findById(clienteDto.getTipoCliente())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de cliente no encontrado"));

        PlanPostpago planPostpago = planPostpagoRepository.findById(clienteDto.getPlanPostpago())
                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado"));

        Cliente cliente = new Cliente(clienteDto.getNombre(), clienteDto.getApellido(),
                clienteDto.getTelefono(), clienteDto.getDireccion(), tipoCliente, planPostpago);

        cliente = clienteRepository.save(cliente);

        return new ClienteDto(cliente);
    }*/

    /*@Override
    public ClienteDto getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return convertToDTO(cliente);
    }

    @Override
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ClienteDto createCliente(ClienteDto clienteDto) {
        Cliente cliente = convertToEntity(clienteDto);
        cliente = clienteRepository.save(cliente);
        return convertToDTO(cliente);
    }*/

    private ClienteDto convertToDTO(Cliente cliente) {
        ClienteDto clienteDTO = new ClienteDto();
        clienteDTO.setClienteId(cliente.getClienteId());
        clienteDTO.setDni(cliente.getDni());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setDireccion(cliente.getDireccion());
        //clienteDTO.setTelefono(cliente.getTelefono());
        //clienteDTO.setTipoCliente(cliente.getTipoCliente().getTipoId());
        //clienteDTO.setPlanPostpago(cliente.getPlanPostpago().getPlanId());
        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteDto clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(clienteDTO.getClienteId());
        cliente.setDni(clienteDTO.getDni());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDireccion(clienteDTO.getDireccion());
        //cliente.setTelefono(clienteDTO.getTelefono());
        /*TipoClienteDto tipoClienteDTO = null;
        TipoCliente tipoCliente = null;
        if (clienteDTO.getTipoCliente() != null) {
            tipoClienteDTO = convertToTipoClienteDTO(clienteDTO.get);
            tipoCliente = new TipoCliente();
            tipoCliente.setTipoId(tipoClienteDTO.getTipoId());
            tipoCliente.setNombre(tipoClienteDTO.getNombre());
        }*/

        /*TipoCliente tipoCliente = new TipoCliente();
        tipoCliente.setTipoId(clienteDTO.getTipoCliente());
        cliente.setTipoCliente(tipoCliente);*/

        /*PlanPostpago planPostpago = new PlanPostpago();
        planPostpago.setPlanId(clienteDTO.getPlanPostpago());
        cliente.setPlanPostpago(planPostpago);*/

        return cliente;
    }

    private TipoClienteDto convertToTipoClienteDTO(TipoCliente tipoCliente) {
        TipoClienteDto tipoClienteDTO = new TipoClienteDto();
        tipoClienteDTO.setTipoId(tipoCliente.getTipoId());
        tipoClienteDTO.setNombre(tipoCliente.getNombre());
        return tipoClienteDTO;
    }


    @Override
    public Cliente update(Long id, Cliente cliente) {
        logger.debug("Iniciando método update");
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
        if (clienteEncontrado.isPresent()) {
            Cliente clienteActualizado = clienteEncontrado.get();
            clienteActualizado.setDni(cliente.getDni());
            clienteActualizado.setNombre(cliente.getNombre());
            clienteActualizado.setApellido(cliente.getApellido());
            //clienteActualizado.setTelefono(cliente.getTelefono());
            clienteActualizado.setDireccion(cliente.getDireccion());
            /*clienteActualizado.setTipoCliente(clienteActualizado.getTipoCliente());
            clienteActualizado.setPlanPostpago(clienteActualizado.getPlanPostpago());*/

            /*TipoCliente tipoCliente = cliente.getTipoCliente();
            if (tipoCliente != null) {
                Optional<TipoCliente> tipoClienteEncontrado = tipoClienteRepository.findById(tipoCliente.getTipoId());
                if (tipoClienteEncontrado.isPresent()) {
                    clienteActualizado.setTipoCliente(tipoClienteEncontrado.get());
                } else {
                    throw new IllegalArgumentException("Tipo de cliente no encontrado");
                }
            }*/

            /*PlanPostpago plan = cliente.getPlanPostpago();
            if (plan != null) {
                Optional<PlanPostpago> planEncontrado = planPostpagoRepository.findById(plan.getPlanId());
                if (planEncontrado.isPresent()) {
                    clienteActualizado.setPlanPostpago(planEncontrado.get());
                } else {
                    throw new IllegalArgumentException("Plan no encontrado");
                }
            }*/

            return clienteRepository.save(clienteActualizado);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
    }


    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
