package com.moneyminder.presentation;

import com.moneyminder.domain.Income;
import com.moneyminder.presentation.dto.IncomeDto;
import com.moneyminder.presentation.mapper.IncomeMapper;
import com.moneyminder.service.IncomeService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public List<Income> getAll() {
        return incomeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getById(@PathVariable Long id) {
        Income income = incomeService.findById(id);
        return income != null ? ResponseEntity.ok(income) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Income> create(@Valid @RequestBody IncomeDto body) {
        Income toSave = IncomeMapper.toEntity(body);
        Income saved = incomeService.save(toSave);
        return ResponseEntity.created(URI.create("/api/incomes/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> update(@PathVariable Long id, @Valid @RequestBody IncomeDto body) {
        Income existing = incomeService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        Income toSave = IncomeMapper.toEntity(body);
        toSave.setId(id);
        return ResponseEntity.ok(incomeService.save(toSave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Income existing = incomeService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        incomeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


