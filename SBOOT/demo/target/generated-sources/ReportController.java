@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/comerciantes/csv")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> generateComerciantesCsv() {
        String filename = "comerciantes.csv";
        InputStreamResource file = new InputStreamResource(reportService.generateComerciantesCsv());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}

@Service
public class ReportService {
    private final ComercianteRepository comercianteRepository;

    @Autowired
    public ReportService(ComercianteRepository comercianteRepository) {
        this.comercianteRepository = comercianteRepository;
    }

    public ByteArrayInputStream generateComerciantesCsv() {
        List<Comerciante> comerciantes = comercianteRepository.findAllByEstado("Activo");

        // Implementar lógica para generar el archivo CSV
    }
}

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/comerciantes/{id}/pdf")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> generateComerciantePdf(@PathVariable Long id) {
        ByteArrayInputStream bis = reportService.generateComerciantePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=comerciante_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
