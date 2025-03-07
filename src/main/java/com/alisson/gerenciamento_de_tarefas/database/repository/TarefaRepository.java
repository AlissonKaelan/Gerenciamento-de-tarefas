package com.alisson.gerenciamento_de_tarefas.database.repository;


import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, UUID> {
    //List<TarefaEntity> findAllByOrderByCreatedOnDesc();
    //
    // List<TarefaEntity> findAllByStatusOrderByCreatedOnDesc(Status status);
}
