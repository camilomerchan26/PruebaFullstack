@Service
public class ComercianteService {
    private final ComercianteRepository comercianteRepository;

    @Autowired
    public ComercianteService(ComercianteRepository comercianteRepository) {
        this.comercianteRepository = comercianteRepository;
    }

    public Comerciante createComerciante(Comerciante comerciante) {
        // Lógica para crear un comerciante
        return comercianteRepository.save(comerciante);
    }

    public Comerciante updateComerciante(Long id, Comerciante comerciante) {
        // Lógica para actualizar un comerciante
    }

    public void deleteComerciante(Long id) {
        // Lógica para eliminar un comerciante
        comercianteRepository.deleteById(id);
    }

    public Optional<Comerciante> getComercianteById(Long id) {
        return comercianteRepository.findById(id);
    }

    public List<Comerciante> getAllComerciantes() {
        return comercianteRepository.findAll();
    }
}

@Service
public class ReportService {
    private final ComercianteRepository comercianteRepository;

    @Autowired
    public ReportService(ComercianteRepository comercianteRepository) {
        this.comercianteRepository = comercianteRepository;
    }

    public ByteArrayInputStream generateComerciantePdf(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comerciante not found"));

        // Implementar lógica para generar el archivo PDF
    }
}

