package com.alisson.gerenciamento_de_tarefas.service;
import com.alisson.gerenciamento_de_tarefas.api.Priority;
import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import com.alisson.gerenciamento_de_tarefas.database.repository.TarefaRepository;
import jakarta.annotation.PostConstruct;

import java.time.Instant;

public class TarefasService {
    private final TarefaRepository tarefaRepository;

    //Constructor
    public TarefasService(final TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    @PostConstruct
    private void generateRandomTask(){
        System.out.println("Iniciando a geração de uma tarefa aleatória...");
        TarefaEntity tarefa = new TarefaEntity();

        tarefa.setTitle("Aulas de Programacao");
        tarefa.setDescription("Hello World " + "Calculadora Simples " + "Contador de Palavras " + "Jogo da Adivinhação " + "Lista de Tarefas " + "Conversor de Unidades");
        tarefa.setStatus(Status.Ready);
        tarefa.setPriority(Priority.High);
        tarefa.setUpdatedOn(Instant.now());
        tarefa.setExpireOn(Instant.now());
        tarefa.setCreatedOn(Instant.now());

        saveTarefa(tarefa);


    }


    public void saveTarefa(final  TarefaEntity tarefa){
        tarefaRepository.save(tarefa);
    }
}

