package com.fiap.bank.controller;

import com.fiap.bank.bean.dto.*;
import com.fiap.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Account", description = "Operações relacionadas às contas bancárias")
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Criação de conta")
    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        accountService.createAccount(createAccountRequest);
        return ResponseEntity.status(201).body(
                GenericResponse.builder()
                        .message("Conta criada com sucesso")
                        .status(201)
                        .build()
        );
    }

    @Operation(summary = "Listar contas por filtros")
    @GetMapping("/all-by-filter")
    public ResponseEntity<List<AccountResponse>> allByFilter(@RequestParam Map<String, String> params) {
        return ResponseEntity.status(200).body(accountService.getAllByFilter(params));
    }

    @Operation(summary = "Realizar saque")
    @PostMapping("/withdraw")
    public ResponseEntity<GenericResponse> withdraw(@RequestBody TransactionRequest transactionRequest) {
        accountService.withdraw(transactionRequest);
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .message("Saque realizado com sucesso")
                        .status(200)
                        .build()
        );
    }

    @Operation(summary = "Realizar depósito")
    @PostMapping("/deposit")
    public ResponseEntity<GenericResponse> deposit(@RequestBody TransactionRequest transactionRequest) {

        accountService.deposit(transactionRequest);
        return ResponseEntity.ok(
                GenericResponse.builder()
                        .message("Depósito realizado com sucesso")
                        .status(200)
                        .build()
        );
    }
}
