package com.moneyminder.presentation;

import com.moneyminder.domain.Expense;
import com.moneyminder.presentation.dto.ExpenseDto;
import com.moneyminder.presentation.mapper.ExpenseMapper;
import com.moneyminder.service.ExpenseService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAll() {
        return expenseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getById(@PathVariable Long id) {
        Expense expense = expenseService.findById(id);
        return expense != null ? ResponseEntity.ok(expense) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Expense> create(@Valid @RequestBody ExpenseDto body) {
        Expense toSave = ExpenseMapper.toEntity(body);
        Expense saved = expenseService.save(toSave);
        return ResponseEntity.created(URI.create("/api/expenses/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable Long id, @Valid @RequestBody ExpenseDto body) {
        Expense existing = expenseService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        Expense toSave = ExpenseMapper.toEntity(body);
        toSave.setId(id);
        return ResponseEntity.ok(expenseService.save(toSave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Expense existing = expenseService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        expenseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


