package com.alisson.gerenciamento_de_tarefas.Convert;

import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


@Component
public class TarefaConvert {
    public TarefaEntity convertTarefaDtoToTarefaEntity(final TarefaDto tarefaDto){
            TarefaEntity tarefaEntity = new TarefaEntity();
            tarefaEntity.setTitle(tarefaDto.getTitle());
            tarefaEntity.setStatus(tarefaDto.getStatus());
            tarefaEntity.setDescription(tarefaDto.getDescription());
            tarefaEntity.setExpireOn(convertStringToInstant(tarefaDto.getExpireOn()));
            tarefaEntity.setPriority(tarefaDto.getPriority());
            return tarefaEntity;
    }

    public TarefaDto convertTarefaEntityToTarefaDto(final TarefaEntity tarefaEntity){
            TarefaDto tarefaDto = new TarefaDto();
            tarefaDto.setId(tarefaEntity.getId());
            tarefaDto.setTitle(tarefaEntity.getTitle());
            tarefaDto.setPriority(tarefaEntity.getPriority());
            tarefaDto.setDescription(tarefaEntity.getDescription());
            tarefaDto.setStatus(tarefaEntity.getStatus());
            tarefaDto.setUpdatedOn(convertInstantToString(tarefaEntity.getUpdatedOn()));
            tarefaDto.setExpireOn(convertInstantToString(tarefaEntity.getExpireOn()));
            tarefaDto.setCreatedOn(convertInstantToString(tarefaEntity.getCreatedOn()));
            return tarefaDto;

    }

    private String convertInstantToString(final Instant dateInstant){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(Date.from(dateInstant));
    }

    public Instant convertStringToInstant(final String dateString){
            try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(dateString);
                    return date.toInstant();
            }catch (ParseException pe){
                throw new IllegalArgumentException("Error during data parse" + pe.getMessage());
            }
    }
}
