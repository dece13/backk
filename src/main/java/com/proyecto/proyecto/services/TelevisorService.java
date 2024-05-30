package com.proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.dto.TelevisorDTO;
import com.proyecto.proyecto.entity.Televisor;
import com.proyecto.proyecto.repository.TelevisorRepository;

@Service
public class TelevisorService {
    private final TelevisorRepository arrendatarioRepository;
    private final ModelMapper modelMapper;
    
    @Autowired
    public TelevisorService(TelevisorRepository arrendatarioRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.arrendatarioRepository = arrendatarioRepository;
    }
    
    public TelevisorDTO get(long id){
        Optional<Televisor> arrendatarioOpt = arrendatarioRepository.findById(id);
        TelevisorDTO arrendatarioDTO = null;
        if(arrendatarioOpt.isPresent()){
            Televisor arrendatario = arrendatarioOpt.get();
            arrendatarioDTO = modelMapper.map(arrendatario, TelevisorDTO.class);
        }
        return arrendatarioDTO;
    }
    
    public List<TelevisorDTO> getAll(){
       List<Televisor> arrendatarios = (List<Televisor>) arrendatarioRepository.findAll();
       return arrendatarios.stream()
                           .map(arrendatario -> modelMapper.map(arrendatario, TelevisorDTO.class))
                           .collect(Collectors.toList());
    }
    
    public TelevisorDTO save(TelevisorDTO arrendatarioDTO){
        Televisor arrendatario = modelMapper.map(arrendatarioDTO, Televisor.class);
        arrendatario = arrendatarioRepository.save(arrendatario);
        return modelMapper.map(arrendatario, TelevisorDTO.class);
    }
    
    public TelevisorDTO update(TelevisorDTO arrendatarioDTO){
        Televisor arrendatario = modelMapper.map(arrendatarioDTO, Televisor.class);
        arrendatario = arrendatarioRepository.save(arrendatario);
        return modelMapper.map(arrendatario, TelevisorDTO.class);
    }
    
    public void delete(Long id){
        arrendatarioRepository.deleteById(id);
    }
}
