package com.alisson.gerenciamento_de_tarefas.service;
import com.alisson.gerenciamento_de_tarefas.api.Priority;
import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import com.alisson.gerenciamento_de_tarefas.database.repository.TarefaRepository;
import com.alisson.gerenciamento_de_tarefas.exeptions.TarefaNotFoundExeptions;
import com.alisson.gerenciamento_de_tarefas.Convert.TarefaConvert;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.Instant.*;
@Service
public class TarefasService {
    private final TarefaRepository tarefaRepository;

    private final TarefaConvert tarefaConvert;

    //Constructor
    public TarefasService(final TarefaRepository tarefaRepository, final TarefaConvert tarefaConvert){
        this.tarefaRepository = tarefaRepository;
        this.tarefaConvert = tarefaConvert;
    }

    /**@PostConstruct
    private void generateRandomTask(){

        TarefaDto tarefaDto = new TarefaDto();
        tarefaDto.setTitle("Tete 1");
        tarefaDto.setDescription("Day 23 " + "Sala 9" + "14hrs" + "Caneta azul ou preta");
        tarefaDto.setStatus(Status.Progress);
        tarefaDto.setPriority(Priority.High);
        tarefaDto.setUpdatedOn(now());
        tarefaDto.setExpireOn(now());
        tarefaDto.setCreatedOn(now());

        saveTarefa(tarefaDto);


    }**/


    public void saveTarefa(final TarefaDto tarefaDto){
        try{
            final TarefaEntity tarefaEntity = tarefaConvert.convertTarefaDtoToTarefaEntity(tarefaDto);
            tarefaRepository.save(tarefaEntity);
        } catch (RuntimeException re) {
            throw re;
        }

    }

    public TarefaDto getTarefaById(final UUID id){
        final Optional<TarefaEntity> optionalTarefaEntity = tarefaRepository.findById(id);
        if (optionalTarefaEntity.isPresent()){
            return tarefaConvert.convertTarefaEntityToTarefaDto(optionalTarefaEntity.get());
        }else {
                throw new TarefaNotFoundExeptions("Tarefa com Id: "+ id + " não encontrado");
        }
    }

    public void deletTarefa(final UUID id){
        tarefaRepository.deleteById(id);
    }

    public void updateTarefa(final TarefaDto tarefaDto){

        try {


            final Optional<TarefaEntity> optionalTarefaEntity = tarefaRepository.findById(tarefaDto.getId());
            if (optionalTarefaEntity.isPresent()) {
                TarefaEntity tarefaEntity = optionalTarefaEntity.get();
                tarefaEntity.setDescription(tarefaDto.getDescription());
                tarefaEntity.setTitle(tarefaDto.getTitle());
                //tarefaEntity.setUpdatedOn(tarefaDto.getUpdatedOn());
                //tarefaEntity.setExpireOn(tarefaDto.getExpireOn());
                tarefaEntity.setStatus(tarefaDto.getStatus());
                tarefaEntity.setPriority(tarefaDto.getPriority());
                //tarefaEntity.setCreatedOn(tarefaDto.getCreatedOn());

                tarefaRepository.save(tarefaEntity);
            } else {
                throw new TarefaNotFoundExeptions("Tarefa com Id: " + tarefaDto.getDescription() + " não encontrado");
            }
        }catch (final RuntimeException re){
            throw re;
        }
    }

    public List<TarefaDto> getTarefaList(){
        return tarefaRepository.findAllByOrderByCreatedOnDesc()
                .stream()
                .map(tarefaConvert::convertTarefaEntityToTarefaDto)
                .collect(Collectors.toList());
    }

    public List<String> getPriorities(){
        return Arrays.asList(Priority.Low.toString(), Priority.High.toString(), Priority.Normal.toString());
    }

    public List<String> getStatus() {
        return Arrays.asList(Status.Done.toString(), Status.Ready.toString(), Status.Progress.toString());
    }
}

