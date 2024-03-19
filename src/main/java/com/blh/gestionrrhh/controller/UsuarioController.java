package com.blh.gestionrrhh.controller;

import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.service.UsuariosService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UsuarioController {
    private final UsuariosService usuariosService;

    public UsuarioController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/singUp")
    public ResponseBase singUp(@RequestBody Map<String, String> requestMap) {
        return usuariosService.registrar(requestMap);
    }

    @PostMapping("/login")
    public ResponseBase login(@RequestBody Map<String,String> requestMap){
        return usuariosService.iniciar(requestMap);
    }

    @GetMapping
    public ResponseBase getAllUsers(){
        return usuariosService.obtenerUsuarios();
    }

    @GetMapping("/{email}")
    public ResponseBase getUser(@PathVariable String email) {
        return usuariosService.obtenerUsuarioPorEmail(email);
    }

    @DeleteMapping("/{id}")
    public ResponseBase deleteUser(@PathVariable int id) {
        return usuariosService.eliminarUsuarioPorId(id);
    }
}
