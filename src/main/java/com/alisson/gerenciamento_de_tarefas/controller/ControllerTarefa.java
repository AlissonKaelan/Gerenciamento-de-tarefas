package com.alisson.gerenciamento_de_tarefas.controller;


import com.alisson.gerenciamento_de_tarefas.api.Status;
import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.service.TarefasService;
import jakarta.annotation.Nullable;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller("/")
public class ControllerTarefa {
    private final TarefasService tarefasService;
    private Status globalStatus;

    @Autowired
    public ControllerTarefa(final TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }


    @GetMapping("/")
    public ModelAndView home(@ModelAttribute("alertMessage") @Nullable String alertMessage){
        ModelAndView mv = new ModelAndView("index");
        List<TarefaDto> tarefaDtoList = tarefasService.getTarefaListByStatus(globalStatus);
        mv.addObject("tarefaDtoList", tarefaDtoList);
        mv.addObject("alertMessage", alertMessage);
        return mv;
    }

    @GetMapping("/add-new-task")
    public ModelAndView pageNewTask() {
        ModelAndView mv = new ModelAndView("new-task");
        mv.addObject("tarefaDto", new TarefaDto());
        mv.addObject("priorities", tarefasService.getPriorities());
        mv.addObject("statusList", tarefasService.getStatus());
        mv.addObject("alertMessage", "");

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

    @GetMapping("/edit-task/{id}")
    public ModelAndView editTask(@PathVariable ("id")UUID id, RedirectAttributes redirectAttributes){
        TarefaDto tarefaDto = tarefasService.getTarefaById(id);
        redirectAttributes.addFlashAttribute("tarefaDto", tarefaDto);
        return new ModelAndView("redirect:/edit-task");
    }

    @GetMapping("/edit-task")
    public ModelAndView editTaskRedirect (Model model, @ModelAttribute ("tarefaDto") TarefaDto tarefaDto){
        ModelAndView mv = new ModelAndView("new-task");
        mv.addObject("tarefaDto", tarefaDto);
        mv.addObject("priorities", tarefasService.getPriorities());
        mv.addObject("statusList", tarefasService.getStatus());
        mv.addObject("alertMessage", "");
        return mv;
    }

    @DeleteMapping("/delete-task/{id}")
    public ModelAndView deleteTask(@PathVariable UUID id){
        tarefasService.deletTarefa(id);
        List<TarefaDto> tarefaDtoList = tarefasService.getTarefaList();
        ModelAndView mv = new ModelAndView("components/task-card");
        mv.addObject("tarefaDtoList", tarefaDtoList);
        return mv;
    }

    @GetMapping("/task-by-status")
    public ModelAndView getTaskListByStatus(@RequestParam(name = "status", required = false) Status status){
        ModelAndView mv = new ModelAndView("redirect:/");
        globalStatus = status;
        return mv;
    }
}