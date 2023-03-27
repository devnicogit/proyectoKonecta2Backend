package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class PlanPostpagoDto {

    @NotBlank
    private Long planId;
    @NotBlank
    private String nombrePlan;
    @NotBlank
    private BigDecimal costoMensual;


    public PlanPostpagoDto() {
    }
    public PlanPostpagoDto(@NotBlank Long planId) {
        this.planId = planId;
    }

    public PlanPostpagoDto(@NotBlank Long planId, @NotBlank String nombrePlan, @NotBlank BigDecimal costoMensual) {
        this.planId = planId;
        this.nombrePlan = nombrePlan;
        this.costoMensual = costoMensual;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public BigDecimal getCostoMensual() {
        return costoMensual;
    }

    public void setCostoMensual(BigDecimal costoMensual) {
        this.costoMensual = costoMensual;
    }
}
