package com.alisson.gerenciamento_de_tarefas.service;
import com.alisson.gerenciamento_de_tarefas.api.Priority;
import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import com.alisson.gerenciamento_de_tarefas.database.repository.TarefaRepository;
import com.alisson.gerenciamento_de_tarefas.mapper.TarefaConvert;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


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

    @PostConstruct
    private void generateRandomTask(){

        TarefaDto tarefaDto = new TarefaDto();
        tarefaDto.setTitle("Aulas de Piano");
        tarefaDto.setDescription("Aula dia 24 " + "Professor Jubuleu" + "14hrs" + "Praca 4");
        tarefaDto.setStatus(Status.Progress);
        tarefaDto.setPriority(Priority.Low);
        tarefaDto.setUpdatedOn(now());
        tarefaDto.setExpireOn(now());
        tarefaDto.setCreatedOn(now());

        saveTarefa(tarefaDto);


    }


    public void saveTarefa(final TarefaDto tarefaDto){
        try{
            final TarefaEntity tarefaEntity = tarefaConvert.convertTarefaEntity(tarefaDto);
            tarefaRepository.save(tarefaEntity);
        } catch (RuntimeException re) {
            throw re;
        }

    }
}

