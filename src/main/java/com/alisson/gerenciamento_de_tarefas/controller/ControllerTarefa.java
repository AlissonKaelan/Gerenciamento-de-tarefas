package com.alisson.gerenciamento_de_tarefas.controller;


import com.alisson.gerenciamento_de_tarefas.api.TarefaDto;
import com.alisson.gerenciamento_de_tarefas.service.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/")
public class ControllerTarefa {
    private final TarefasService tarefasService;

    @Autowired
    public ControllerTarefa(final TarefasService tarefasService){
        this.tarefasService = tarefasService;
    }

    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("index");
        List<TarefaDto> tarefaDtoList = tarefasService.getTarefaList();
        mv.addObject("tarefaDtoList", tarefaDtoList);
        return mv;
    }

    @GetMapping("/add-new-task")
    public ModelAndView pageNewTask(){
        ModelAndView mv = new ModelAndView("new-task");
        mv.addObject("tarefaDto", new TarefaDto());
        return mv;
    }

}
