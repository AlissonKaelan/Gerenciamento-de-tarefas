package com.alisson.gerenciamento_de_tarefas.controller;


import com.alisson.gerenciamento_de_tarefas.service.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class ControllerTarefa {
    private final TarefasService tarefasService;

    @Autowired
    public ControllerTarefa(final TarefasService tarefasService){
        this.tarefasService = tarefasService;
    }

    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
