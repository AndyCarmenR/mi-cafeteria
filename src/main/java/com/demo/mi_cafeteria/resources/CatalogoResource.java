package com.demo.mi_cafeteria.resources;

import com.demo.mi_cafeteria.model.dto.*;
import com.demo.mi_cafeteria.model.entity.DescuentoArticulo;
import com.demo.mi_cafeteria.services.CatalogService;
import com.demo.mi_cafeteria.utils.BadRequestException;
import com.demo.mi_cafeteria.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogoResource {

    @Autowired
    private CatalogService catalogService;


    @GetMapping("/tipo-pago")
    public ResponseEntity<List<TipoPagoDto>>getAllTipoPago(){
        return ResponseEntity.ok(catalogService.getAllTipoPago());
    }

    @GetMapping("/articulos-venta")
    public ResponseEntity<List<ArticuloVentaDto>>getAllArticuloVenta(){
        return ResponseEntity.ok(catalogService.getAllArticulosVenta());
    }

    @GetMapping("/tipo-articulo")
    public ResponseEntity<List<TipoArticuloDto>>getAllTipoArticulo(){
        return ResponseEntity.ok(catalogService.getAllTipoArticulo());
    }

    @PostMapping("/tipo-articulo")
    public ResponseEntity<TipoArticuloDto>saveTipoArticulo(@RequestBody TipoArticuloDto tipoArticuloDto){
        return ResponseEntity.ok(catalogService.saveTipoArticulo(tipoArticuloDto));
    }

    @PostMapping("/tipo-pago")
    public ResponseEntity<TipoPagoDto>saveTipoPago(@RequestBody TipoPagoDto tipoPagoDto)  {
        return ResponseEntity.ok(catalogService.saveTipoPago(tipoPagoDto));
    }

    @PostMapping("/articulos-venta")
    public ResponseEntity<ArticuloVentaDto>saveArticuloVenta(@RequestBody ArticuloVentaDto articuloVentaDto){
        return ResponseEntity.ok(catalogService.saveArticuloVenta(articuloVentaDto));
    }

    @PostMapping("/descuento")
    public ResponseEntity<DescuentoArticuloDto>saveDescuento(@RequestBody DescuentoArticuloDto descuento){
        return ResponseEntity.ok(catalogService.saveDescuento(descuento));
    }
    @GetMapping("/descuento")
    public ResponseEntity<List<DescuentoArticuloDto>>getAllDescuentos(){
        return ResponseEntity.ok(catalogService.getAllDescuentoArticulo());
    }

    @PostMapping("/extras")
    public ResponseEntity<ExtrasDto> saveExtras(@RequestBody ExtrasDto extrasDto){
        return ResponseEntity.ok(catalogService.saveExtra(extrasDto));
    }
    @GetMapping("/extras")
    public ResponseEntity<List<ExtrasDto>>getAllExtras(){
        return ResponseEntity.ok(catalogService.getAllExtras());
    }
}
