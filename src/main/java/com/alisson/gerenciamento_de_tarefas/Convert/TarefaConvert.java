package com.alisson.gerenciamento_de_tarefas.Convert;

import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import org.springframework.stereotype.Component;


@Component
public class TarefaConvert {
    public TarefaEntity convertTarefaDtoToTarefaEntity(final TarefaDto tarefaDto){
            TarefaEntity tarefaEntity = new TarefaEntity();
            tarefaEntity.setTitle(tarefaDto.getTitle());
            tarefaEntity.setStatus(tarefaDto.getStatus());
            tarefaEntity.setDescription(tarefaDto.getDescription());
            tarefaEntity.setUpdatedOn(tarefaDto.getUpdatedOn());
            tarefaEntity.setExpireOn(tarefaDto.getExpireOn());
            tarefaEntity.setCreatedOn(tarefaDto.getCreatedOn());
            return tarefaEntity;
    }

    public TarefaDto convertTarefaEntityToTarefaDto(final TarefaEntity tarefaEntity){
            TarefaDto tarefaDto = new TarefaDto();
            tarefaDto.setId(tarefaDto.getId());
            tarefaDto.setTitle(tarefaDto.getTitle());
            tarefaDto.setPriority(tarefaDto.getPriority());
            tarefaDto.setDescription(tarefaDto.getDescription());
            tarefaDto.setStatus(tarefaDto.getStatus());
            tarefaDto.setUpdatedOn(tarefaDto.getUpdatedOn());
            tarefaDto.setExpireOn(tarefaDto.getExpireOn());
            tarefaDto.setCreatedOn(tarefaDto.getCreatedOn());
            return tarefaDto;

    }
}
