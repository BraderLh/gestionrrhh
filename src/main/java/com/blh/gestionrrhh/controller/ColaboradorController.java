package com.blh.gestionrrhh.controller;

import com.blh.gestionrrhh.agreggates.request.RequestColaborador;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.service.ColaboradoresService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class ColaboradorController {
    private final ColaboradoresService colaboradoresService;

    public ColaboradorController(ColaboradoresService colaboradoresService) {
        this.colaboradoresService = colaboradoresService;
    }

    @PostMapping
    public ResponseBase crearColaborador(@RequestBody RequestColaborador requestColaborador) {
        return colaboradoresService.crearColaborador(requestColaborador);
    }

    @DeleteMapping("{id}")
    public ResponseBase eliminarColaborador(@PathVariable int id) {
        return colaboradoresService.eliminarColaboradorPorId(id);
    }

    @GetMapping()
    public ResponseBase listarColaboradores() {
        return colaboradoresService.listarColaboradores();
    }

    @GetMapping("{id}")
    public ResponseBase buscarColaborador(@PathVariable int id) {
        return colaboradoresService.buscarColaboradorPorId(id);
    }

    @PatchMapping("{id}")
    public ResponseBase actualizarColaborador(@PathVariable int id, @RequestBody RequestColaborador requestColaborador) {
        return colaboradoresService.actualizarColaboradorPorId(id, requestColaborador);
    }
}
