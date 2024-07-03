@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {
    private final ComercianteService comercianteService;

    @Autowired
    public ComercianteController(ComercianteService comercianteService) {
        this.comercianteService = comercianteService;
    }

    @GetMapping
    public ResponseEntity<Page<Comerciante>> getAllComerciantes(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "5") int size) {
        Page<Comerciante> comerciantes = comercianteService.getAllComerciantes(PageRequest.of(page, size));
        return ResponseEntity.ok(comerciantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comerciante> getComercianteById(@PathVariable Long id) {
        return comercianteService.getComercianteById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comerciante> createComerciante(@Valid @RequestBody Comerciante comerciante) {
        Comerciante createdComerciante = comercianteService.createComerciante(comerciante);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComerciante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comerciante> updateComerciante(@PathVariable Long id, @Valid @RequestBody Comerciante comerciante) {
        Comerciante updatedComerciante = comercianteService.updateComerciante(id, comerciante);
        return ResponseEntity.ok(updatedComerciante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComerciante(@PathVariable Long id) {
        comercianteService.deleteComerciante(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateComercianteStatus(@PathVariable Long id, @RequestParam String status) {
        comercianteService.updateComercianteStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}

@RestController
@RequestMapping("/api/establecimientos")
public class EstablecimientoController {
    private final EstablecimientoService establecimientoService;

    @Autowired
    public EstablecimientoController(EstablecimientoService establecimientoService) {
        this.establecimientoService = establecimientoService;
    }

    @GetMapping("/comerciante/{comercianteId}")
    public ResponseEntity<List<Establecimiento>> getEstablecimientosByComerciante(@PathVariable Long comercianteId) {
        return ResponseEntity.ok(establecimientoService.getEstablecimientosByComerciante(comercianteId));
    }

    @PostMapping
    public ResponseEntity<Establecimiento> createEstablecimiento(@Valid @RequestBody Establecimiento establecimiento) {
        Establecimiento createdEstablecimiento = establecimientoService.createEstablecimiento(establecimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEstablecimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Establecimiento> updateEstablecimiento(@PathVariable Long id, @Valid @RequestBody Establecimiento establecimiento) {
        Establecimiento updatedEstablecimiento = establecimientoService.updateEstablecimiento(id, establecimiento);
        return ResponseEntity.ok(updatedEstablecimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstablecimiento(@PathVariable Long id) {
        establecimientoService.deleteEstablecimiento(id);
        return ResponseEntity.noContent().build();
    }
}

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> getAllDepartamentos() {
        return ResponseEntity.ok(departamentoService.getAllDepartamentos());
    }
}
