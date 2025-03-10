package com.alisson.gerenciamento_de_tarefas.service;

import com.alisson.gerenciamento_de_tarefas.api.Priority;
import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import com.alisson.gerenciamento_de_tarefas.database.repository.TarefaRepository;
import com.alisson.gerenciamento_de_tarefas.mapper.TarefaConvert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    public void testGetTarefaById(){
        UUID id = UUID.randomUUID();
        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(id);

        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefaEntity));

        TarefaDto tarefaDto = new TarefaDto();
        when(tarefaConvert.convertTarefaEntityTOTarefaDto(tarefaEntity)).thenReturn(tarefaDto);

        TarefaDto tarefaDtoResult = tarefasService.getTarefaById(id);

        assertNotNull(tarefaDtoResult);
        assertEquals(tarefaDto, tarefaDtoResult);

        verify(tarefaRepository, times(1)).findById(id);
        verify(tarefaConvert,times(1)).convertTarefaEntityTOTarefaDto(tarefaEntity);
    }

    @Test
    public void testDeleteTarefa(){
        UUID id = UUID.randomUUID();
        tarefasService.deletTarefa(id);
        verify(tarefaRepository,times(1)).deleteById(id);
    }

    @Test
    public void testUpdateTarefa(){
        TarefaDto tarefaDto = new TarefaDto();
        tarefaDto.setId(UUID.randomUUID());
        tarefaDto.setTitle("Titulo update");
        Instant date = Instant.now();
        tarefaDto.setStatus(Status.Progress);
        tarefaDto.setDescription("Update description");
        tarefaDto.setPriority(Priority.High);

        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(tarefaDto.getId());
        when(tarefaRepository.findById(tarefaDto.getId())).thenReturn(Optional.of(tarefaEntity));

        tarefasService.updateTarefa(tarefaDto);

        verify(tarefaRepository,times(1)).findById(tarefaDto.getId());
        verify(tarefaRepository,times(1)).save(tarefaEntity);

        assertEquals("Titulo update", tarefaEntity.getTitle());
        assertEquals("Update description", tarefaEntity.getDescription());
        assertEquals(Status.Progress, tarefaEntity.getStatus());
        assertEquals(Priority.High, tarefaEntity.getPriority());

    }

    @Test
    public void testGetTarefaList(){
        List<TarefaEntity> tarefaEntityList = Arrays.asList(new TarefaEntity(), new TarefaEntity(), new TarefaEntity());
        when(tarefaRepository.findAllByOrderByCreatedOnDesc()).thenReturn(tarefaEntityList);

        TarefaDto tarefaDto = new TarefaDto();
        TarefaEntity tarefaEntity = new TarefaEntity();
        when(tarefaConvert.convertTarefaEntityTOTarefaDto(tarefaEntity)).thenReturn(tarefaDto);

        List<TarefaDto> tarefaDtoList = tarefasService.getTarefaList();

        assertNotNull(tarefaDtoList);
        assertEquals(3, tarefaDtoList.size());
        verify(tarefaRepository, times(1)).findAllByOrderByCreatedOnDesc();
        verify(tarefaConvert, times(3)).convertTarefaEntityTOTarefaDto((TarefaEntity) any());


    }

}
