package com.tutorial.crud.service;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.TipoClienteDto;
import com.tutorial.crud.repository.TelefonoRepository;
import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.swagger.entity.Telefono;
import com.tutorial.crud.swagger.entity.TipoCliente;
import com.tutorial.crud.repository.ClienteRepository;
import com.tutorial.crud.repository.PlanPostpagoRepository;
import com.tutorial.crud.repository.TipoClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PlanPostpagoRepository planPostpagoRepository;

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @Autowired
    private TelefonoRepository telefonoRepository;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private TipoClienteService tipoClienteService;

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


    @Transactional
    public Cliente createCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setDni(clienteDto.getDni());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setDireccion(clienteDto.getDireccion());

        Set<TipoCliente> tiposCliente = new HashSet<>();
        for (Long tipoId : clienteDto.getTipoClienteIds()) {
            Optional<TipoCliente> tipoClienteOptional = tipoClienteRepository.findById(tipoId);
            tipoClienteOptional.ifPresent(tiposCliente::add);
        }
        cliente.setTipoCliente(tiposCliente);

        Cliente savedCliente = clienteRepository.save(cliente);
        updateClienteTipoCliente(savedCliente, tiposCliente);

        return savedCliente;
    }

    private void updateClienteTipoCliente(Cliente cliente, Set<TipoCliente> tiposCliente) {
        /*cliente.getTipoCliente().clear();
        for (TipoCliente tipoCliente : tiposCliente) {
            cliente.addTipoCliente(tipoCliente);
        }
        clienteRepository.save(cliente);*/

        Set<TipoCliente> tiposClienteActuales = cliente.getTipoCliente();
        for (TipoCliente tipoCliente : tiposCliente) {
            if (!tiposClienteActuales.contains(tipoCliente)) {
                cliente.addTipoCliente(tipoCliente);
            }
        }
        clienteRepository.save(cliente);
    }

    public void actualizarTiposCliente(Cliente cliente) {
        Set<TipoCliente> tiposCliente = new HashSet<>();
        for (Telefono telefono : telefonoService.findByCliente(cliente)) {
            if (telefono.getPlan() instanceof PlanPostpago) {
                tiposCliente.add(tipoClienteService.findByNombre("Postpago"));
            } else {
                tiposCliente.add(tipoClienteService.findByNombre("Prepago"));
            }
        }
        cliente.setTipoCliente(tiposCliente);
        clienteRepository.save(cliente);
    }

    public void actualizarTiposClienteSiNecesario(Cliente cliente, Telefono telefonoOriginal) {
        Set<TipoCliente> tiposCliente = new HashSet<>();
        for (Telefono telefono : telefonoService.findByCliente(cliente)) {
            if (telefono.getPlan() instanceof PlanPostpago) {
                tiposCliente.add(tipoClienteService.findByNombre("Postpago"));
            } else {
                tiposCliente.add(tipoClienteService.findByNombre("Prepago"));
            }
        }
        cliente.setTipoCliente(tiposCliente);
        if (!tiposCliente.equals(cliente.getTipoCliente()) && telefonoOriginal.getPlan() instanceof PlanPostpago) {
            updateClienteTipoCliente(cliente, tiposCliente);
        }
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
    public Cliente update(Long id, ClienteDto clienteDto) {


            Cliente clienteEncontrado = clienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se encontró el cliente con el ID " + id));

            clienteEncontrado.setDni(clienteDto.getDni());
            clienteEncontrado.setNombre(clienteDto.getNombre());
            clienteEncontrado.setApellido(clienteDto.getApellido());
            clienteEncontrado.setDireccion(clienteDto.getDireccion());

            // Actualizar los tipos de cliente asociados al cliente
            Set<TipoCliente> tiposCliente = new HashSet<>();
            for (Long tipoClienteId : clienteDto.getTipoClienteIds()) {
                TipoCliente tipoCliente = tipoClienteRepository.findById(tipoClienteId)
                        .orElseThrow(() -> new RuntimeException("No se encontró el tipo de cliente con el ID " + tipoClienteId));
                tiposCliente.add(tipoCliente);
            }
            clienteEncontrado.setTipoCliente(tiposCliente);

            return clienteRepository.save(clienteEncontrado);

    }

    @Override
    public List<Telefono> findByCliente(Cliente cliente) {
        return telefonoRepository.findByCliente(cliente);
    }


    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
