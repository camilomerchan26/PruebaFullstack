@RunWith(SpringRunner.class)
@SpringBootTest
public class ComercianteServiceTest {

    @Autowired
    private ComercianteService comercianteService;

    @MockBean
    private ComercianteRepository comercianteRepository;

    @Test
    public void testCreateComerciante() {
        Comerciante comerciante = new Comerciante();
        comerciante.setNombre("Test");

        Mockito.when(comercianteRepository.save(comerciante)).thenReturn(comerciante);

        Comerciante created = comercianteService.createComerciante(comerciante);

        assertThat(created.getNombre()).isSameAs(comerciante.getNombre());
        verify(comercianteRepository).save(comerciante);
    }

    @Test
    public void testGetAllComerciantes() {
        List<Comerciante> comerciantes = Arrays.asList(new Comerciante(), new Comerciante());
        Page<Comerciante> page = new PageImpl<>(comerciantes);

        Mockito.when(comercianteRepository.findAll(PageRequest.of(0, 5))).thenReturn(page);

        Page<Comerciante> result = comercianteService.getAllComerciantes(PageRequest.of(0, 5));

        assertThat(result.getContent()).isEqualTo(comerciantes);
    }
}
