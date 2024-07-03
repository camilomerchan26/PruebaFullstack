@Entity
public class Comerciante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String departamento;
    private String municipio;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    private String estado;
    private LocalDateTime fechaActualizacion;
    private String usuario;

    // Getters and setters
}

@Entity
public class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private BigDecimal ingresos;
    private Integer numeroEmpleados;
    private LocalDateTime fechaActualizacion;
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "comerciante_id")
    private Comerciante comerciante;

    // Getters and setters
}

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementar la lógica para cargar el usuario desde la base de datos
        return new User("user", new BCryptPasswordEncoder().encode("password"), new ArrayList<>());
    }
}
