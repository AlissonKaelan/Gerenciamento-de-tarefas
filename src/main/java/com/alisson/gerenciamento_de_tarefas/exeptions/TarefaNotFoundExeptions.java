package com.alisson.gerenciamento_de_tarefas.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TarefaNotFoundExeptions extends RuntimeException{
    public TarefaNotFoundExeptions(String message){
        super(message);
    }
}
