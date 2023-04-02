package com.tutorial.crud.controller;


import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.service.PlanPostpagoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/planpostpago")
@CrossOrigin(origins = "*")
public class PlanPostpagoController {

    @Autowired
    private PlanPostpagoService planPostpagoService;

    @ApiOperation("Muestra una lista de productos")
    @GetMapping("/lista")
    public ResponseEntity<List<PlanPostpago>> list(){
        List<PlanPostpago> list = planPostpagoService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanPostpago> getPlanPostpagoById(@PathVariable Long id) {
        PlanPostpago planPostpago = planPostpagoService.findById(id);
        return ResponseEntity.ok(planPostpago);
    }

    @PostMapping("/create")
    public ResponseEntity<PlanPostpago> createPlanPostpago(@Valid @RequestBody PlanPostpago planPostpago) {
        PlanPostpago createdPlanPostpago = planPostpagoService.save(planPostpago);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanPostpago);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlanPostpago> updatePlanPostpago(@PathVariable Long id, @Valid @RequestBody PlanPostpago planPostpago) {
        PlanPostpago updatedPlanPostpago = planPostpagoService.update(id,planPostpago);
        return ResponseEntity.ok(updatedPlanPostpago);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlanPostpago(@PathVariable Long id) {
        planPostpagoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
