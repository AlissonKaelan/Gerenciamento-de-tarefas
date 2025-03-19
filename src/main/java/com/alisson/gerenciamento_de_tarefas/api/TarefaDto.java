package com.alisson.gerenciamento_de_tarefas.api;

import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.UUID;

public class TarefaDto {
    private UUID id;
    @NotEmpty(message = "o título não deve ser nulo")
    private String title;
    private String createdOn;
    private String updatedOn;
    @NotEmpty(message = "A data da experiência não pode estar vazia")
    private String expireOn;
    private Priority priority;
    private Status status;
    @NotEmpty(message = "descrição é obrigatória")
    private String description;
    private String statusClass;
    private String priorityClass;

    public String getStatusClass() {
        return statusClass;
    }

    public void setStatusClass(String statusClass) {
        this.statusClass = statusClass;
    }

    public String getPriorityClass() {
        return priorityClass;
    }

    public void setPriorityClass(String priorityClass) {
        this.priorityClass = priorityClass;
    }

    public TarefaDto(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getExpireOn() {
        return expireOn;
    }

    public void setExpireOn(String expireOn) {
        this.expireOn = expireOn;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
