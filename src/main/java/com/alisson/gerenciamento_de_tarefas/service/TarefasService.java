package com.alisson.gerenciamento_de_tarefas.service;
import com.alisson.gerenciamento_de_tarefas.api.Priority;
import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import com.alisson.gerenciamento_de_tarefas.database.repository.TarefaRepository;
import jakarta.annotation.PostConstruct;

import java.time.Instant;

import static java.time.Instant.*;

public class TarefasService {
    private final TarefaRepository tarefaRepository;

    //Constructor
    public TarefasService(final TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    @PostConstruct
    private void generateRandomTask(){
        System.out.println("Iniciando a geração de uma tarefa aleatória...");
        TarefaEntity tarefaEntity = new TarefaEntity();

        tarefaEntity.setTitle("Aulas de Programacao");
        tarefaEntity.setDescription("Hello World " + "Calculadora Simples " + "Contador de Palavras " + "Jogo da Adivinhação " + "Lista de Tarefas " + "Conversor de Unidades");
        tarefaEntity.setStatus(Status.Ready);
        tarefaEntity.setPriority(Priority.High);
        tarefaEntity.setUpdatedOn(now());
        tarefaEntity.setExpireOn(now());
        tarefaEntity.setCreatedOn(now());

        saveTarefa(tarefaEntity);


    }


    public void saveTarefa(final  TarefaEntity tarefaEntity){
        tarefaRepository.save(tarefaEntity);

    }
}

