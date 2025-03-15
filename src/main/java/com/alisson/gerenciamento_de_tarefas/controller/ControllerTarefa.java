package com.alisson.gerenciamento_de_tarefas.controller;


import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.service.TarefasService;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller("/")
public class ControllerTarefa {
    private final TarefasService tarefasService;

    @Autowired
    public ControllerTarefa(final TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }


    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        List<TarefaDto> tarefaDtoList = tarefasService.getTarefaList();
        mv.addObject("tarefaDtoList", tarefaDtoList);
        return mv;
    }

    @GetMapping("/add-new-task")
    public ModelAndView pageNewTask() {
        ModelAndView mv = new ModelAndView("new-task");
        mv.addObject("tarefaDto", new TarefaDto());
        mv.addObject("priorities", tarefasService.getPriorities());
        mv.addObject("statusList", tarefasService.getStatus());
        return mv;
    }

    @PostMapping("/add-or-update-task")
    public ModelAndView addOrUpgradeTask(final @Valid TarefaDto tarefaDto,
                                         final BindingResult bindResult,
                                         final RedirectAttributes redirectAttributes) {
        if (bindResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("new-task");
            mv.addObject("taskDto", tarefaDto);
            mv.addObject("priorities", tarefasService.getPriorities());
            mv.addObject("statusList", tarefasService.getStatus());
            mv.addObject("alertMessage", "Error, please fill the form correctly");
            return mv;
        } if(tarefaDto.getId() == null){
            tarefasService.saveTarefa(tarefaDto);
            redirectAttributes.addFlashAttribute("alertMessage", "New task was been successfully saved");
        } else {
            tarefasService.updateTarefa(tarefaDto);
            redirectAttributes.addFlashAttribute("alertMessage", "Task was been successfully updated");
        }


        return new ModelAndView("redirect:/");


    }

}