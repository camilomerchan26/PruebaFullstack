@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {
    private final ComercianteService comercianteService;

    @Autowired
    public ComercianteController(ComercianteService comercianteService) {
        this.comercianteService = comercianteService;
    }

    @PostMapping
    public ResponseEntity<Comerciante> createComerciante(@RequestBody Comerciante comerciante) {
        Comerciante createdComerciante = comercianteService.createComerciante(comerciante);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComerciante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comerciante> updateComerciante(@PathVariable Long id, @RequestBody Comerciante comerciante) {
        Comerciante updatedComerciante = comercianteService.updateComerciante(id, comerciante);
        return ResponseEntity.ok(updatedComerciante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComerciante(@PathVariable Long id) {
        comercianteService.deleteComerciante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comerciante> getComercianteById(@PathVariable Long id) {
        return comercianteService.getComercianteById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Comerciante>> getAllComerciantes() {
        return ResponseEntity.ok(comercianteService.getAllComerciantes());
    }
}
