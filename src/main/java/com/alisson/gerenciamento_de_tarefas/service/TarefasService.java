package com.alisson.gerenciamento_de_tarefas.service;
import com.alisson.gerenciamento_de_tarefas.api.Priority;
import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.database.entity.TarefaEntity;
import com.alisson.gerenciamento_de_tarefas.database.repository.TarefaRepository;
import com.alisson.gerenciamento_de_tarefas.exeptions.TarefaNotFoundExeptions;
import com.alisson.gerenciamento_de_tarefas.Convert.TarefaConvert;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                tarefaEntity.setExpireOn(tarefaConvert.convertStringToInstant(tarefaDto.getExpireOn()));
                tarefaEntity.setStatus(tarefaDto.getStatus());
                tarefaEntity.setPriority(tarefaDto.getPriority());

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

    public List<TarefaDto> getTarefaListByStatus(String  stringStatus){
        Status status = tarefaConvert.convertStatus(stringStatus);
        if (status == null){
            return getTarefaList();
        }
        List<TarefaEntity> tarefaEntityList = tarefaRepository.findAllByStatusOrderByCreatedOnDesc(status);
        return tarefaEntityList.stream().map(tarefaConvert::convertTarefaEntityToTarefaDto).collect(Collectors.toList());
    }

    public Page<TarefaDto> getTarefaPaginated(int pageNo, int pageSize, String status){
        List<TarefaDto> tarefaDtoList;
        Page<TarefaDto> page;
        if (status == null || status.isEmpty() || status.equals("all")){
            tarefaDtoList = getTarefaList();
        }else {
            tarefaDtoList = getTarefaListByStatus(status);
            pageNo = 1;
        }
        if(!tarefaDtoList.isEmpty()){
            if (tarefaDtoList.size() < pageSize){
                pageSize = tarefaDtoList.size();
            }
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            int start = (int)pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), tarefaDtoList.size());
            List<TarefaDto> subList = tarefaDtoList.subList(start, end);
            page = new PageImpl<>(subList, pageable, tarefaDtoList.size());
        }else {
            page = Page.empty();
        }
        return page;

    }
}

