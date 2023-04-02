package com.tutorial.crud.service;

import com.tutorial.crud.swagger.entity.TipoCliente;
import com.tutorial.crud.repository.TipoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class TipoClienteServiceImpl implements TipoClienteService {

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    //@Autowired
    //private TipoClienteDtoRepository tipoClienteDtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoCliente> findAll() {
        return tipoClienteRepository.findAll();
    }

    @Override
    public TipoCliente findById(Long id) {
        return tipoClienteRepository.findById(id).orElse(null);
    }

    @Override
    public TipoCliente save(TipoCliente tipoCliente) {
        return tipoClienteRepository.save(tipoCliente);
    }

    /*@Override
    public TipoClienteDto save(TipoClienteDto tipoCliente) {
        return tipoClienteDtoRepository.save(tipoCliente);
    }*/

    /*@Override
    public List<TipoClienteDto> getAllTipoClientes() {
        List<TipoCliente> tipoClienteList = tipoClienteRepository.findAll();
        List<TipoClienteDto> tipoClienteDTOList = new ArrayList<>();
        for (TipoCliente tipoCliente : tipoClienteList) {
            TipoClienteDto tipoClienteDTO = new TipoClienteDto();
            tipoClienteDTO.setTipoId(tipoCliente.getTipoId());
            tipoClienteDTO.setNombre(tipoCliente.getNombre());
            tipoClienteDTOList.add(tipoClienteDTO);
        }
        return tipoClienteDTOList;
    }*/

    /*@Override
    public TipoClienteDto getTipoClienteById(Long tipoClienteId) {
        Optional<TipoCliente> tipoClienteOptional = tipoClienteRepository.findById(tipoClienteId);
        if (tipoClienteOptional.isPresent()) {
            TipoClienteDto tipoClienteDTO = new TipoClienteDto();
            TipoCliente tipoCliente = tipoClienteOptional.get();
            tipoClienteDTO.setTipoId(tipoCliente.getTipoId());
            tipoClienteDTO.setNombre(tipoCliente.getNombre());
            return tipoClienteDTO;
        }
        return null;
    }*/


    @Override
    public TipoCliente update(Long id, TipoCliente tipoCliente) {
        Optional<TipoCliente> tipoClienteEncontrado = tipoClienteRepository.findById(id);
        if (tipoClienteEncontrado.isPresent()) {
            TipoCliente tipoClienteActualizado = tipoClienteEncontrado.get();
            tipoClienteActualizado.setNombre(tipoCliente.getNombre());


            return tipoClienteRepository.save(tipoClienteActualizado);
        } else {
            throw new IllegalArgumentException("Tipo de Cliente no encontrado");
        }
    }



    @Override
    public void delete(Long id) {
        tipoClienteRepository.deleteById(id);
    }
}
