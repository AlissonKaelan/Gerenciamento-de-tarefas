package com.alisson.gerenciamento_de_tarefas.service;

import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import com.alisson.gerenciamento_de_tarefas.database.repository.TarefaRepository;
import com.alisson.gerenciamento_de_tarefas.mapper.TarefaConvert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TarefaServiceTest {
    @Mock
    private TarefaRepository tarefaRepository;
    @Mock
    private TarefaConvert tarefaConvert;
    @InjectMocks
    private TarefasService tarefasService;

    public TarefaServiceTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTarefaTest() {
        final TarefaDto tarefaDto = new TarefaDto();
        final TarefaEntity tarefaEntity = new TarefaEntity();

        when(tarefaConvert.convertTarefaDtoToTarefaEntity(tarefaDto)).thenReturn(tarefaEntity);
        tarefasService.saveTarefa(tarefaDto);

        verify(tarefaConvert, times(1)).convertTarefaDtoToTarefaEntity(tarefaDto);
        verify(tarefaRepository, times(1)).save(tarefaEntity);

    }
}
