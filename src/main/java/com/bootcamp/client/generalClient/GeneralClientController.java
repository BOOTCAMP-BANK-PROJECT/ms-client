package com.bootcamp.client.generalClient;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
@Tag(name = "Enterprise Passive Product Pyme Current Account Type", description = "Manage Enterprise Passive Product Pyme Current Accounts type")
@CrossOrigin( value = { "*" })
@RequiredArgsConstructor
public class GeneralClientController {

    @GetMapping
    public String getAll() {
        return "Hola, Mundo!";
    }

}