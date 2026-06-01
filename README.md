# Pelota Yara  - REST API para Fútbol 

**API Backend moderna construida con Spring Boot y arquitectura hexagonal para gestionar partidos de fútbol en vivo, ligas, equipos y eventos en tiempo real.**

---

##  Descripción del Proyecto

Pelota Yara es una API REST robusta diseñada para servir como backend de una plataforma integral de fútbol paraguayo. Proporciona:

-  **Vista de partidos en vivo** con actualización en tiempo real
-  **Tabla de posiciones** y gestión de ligas
-  **Catálogo dinámico de equipos** por competencia
-  **Resultados por fecha** con eventos detallados
-  **Sincronización en tiempo real** con API externa de fútbol

---

##  Arquitectura

El proyecto implementa **Arquitectura Hexagonal (Ports & Adapters)** garantizando:

```
┌─────────────────────────────────────────────────────────────────┐
│                   LAYER PRESENTATION (REST)                      │
│  Controllers (@RestController) - PartidoController, etc.         │
└────────────────┬────────────────────────────────────────────────┘
                 │ DTOs, Mappers
┌────────────────▼────────────────────────────────────────────────┐
│              LAYER APPLICATION (Use Cases)                       │
│  Services (PartidoServiceImpl, EquipoServiceImpl, etc.)           │
└────────────────┬────────────────────────────────────────────────┘
                 │ Domain Models, Business Logic
┌────────────────▼────────────────────────────────────────────────┐
│             LAYER DOMAIN (Business Rules)                        │
│  Domain Models, Aggregates, Value Objects                        │
│  State Machine (EstadoPartido), Event Handlers                   │
└────────────────┬────────────────────────────────────────────────┘
                 │ Interfaces, Contracts
┌────────────────▼────────────────────────────────────────────────┐
│          LAYER INFRASTRUCTURE (Adapters)                         │
│  Persistence (JPA), External APIs, Schedulers                    │
└─────────────────────────────────────────────────────────────────┘
```

### Separación de Responsabilidades
- **Domain**: Lógica pura del negocio sin dependencias externas
- **Application**: Orquestación de use cases y transformaciones
- **Infrastructure**: Adaptadores para BD, APIs externas, HTTP
- **Presentation**: Exposición de endpoints REST

---

##  Patrones Implementados

### 1. **Hexagonal Architecture (Ports & Adapters)**
Independencia del framework y facilidad para testear. La lógica de negocio está completamente desacoplada de detalles técnicos.

```
application/
├── in/          # Puertos de entrada (interfaces usadas por los adaptadores)
│   ├── PartidoService
│   ├── EquipoService
│   └── DataApiProvider (puerto)
└── out/         # Puertos de salida (interfaces implementadas en infrastructure)
    ├── PartidoRepository
    └── JugadorRepository

infrastructure/
├── adapter/in/
│   ├── rest/    # Adaptadores REST
│   └── scheduler/ # Schedulers
└── adapter/out/
    ├── persistence/ # JPA
    └── api/        # External API Consumer
```

### . **State Machine Pattern**
Gestión de estados de partidos con transiciones explícitas y comportamientos específicos por estado:

```java
interface EstadoPartido {
    void ejecutar(Partido partido);
    EstadoPartido siguienteEstado(Partido partido);
    void onEnter(Partido partido);
    void onExit(Partido partido);
}


class PrimerTiempo extends AbstractEstadoPartido { ... }
class Descanso extends AbstractEstadoPartido { ... }
class SegundoTiempo extends AbstractEstadoPartido { ... }
class Finalizado extends AbstractEstadoPartido { ... }
```

### . **Repository**
Abstracción de la persistencia a través de interfaces:

```java
interface PartidoRepository {
    Partido savePartido(Partido partido);
    Optional<Partido> encontrarPartido(Long id);
    List<Partido> listarTodosLosPartidos();
}
```

### . **Service Locator + Dependency Injection**
Inyección de dependencias a través de constructores en todas las clases de servicio:

```java
@Service
public class PartidoServiceImpl implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final EquipoService equipoService;
    private final DataApiProvider fixtureProvider;
    
    public PartidoServiceImpl(PartidoRepository repo, EquipoService equipo, 
                             DataApiProvider provider) {
        this.partidoRepository = repo;
        this.equipoService = equipo;
        this.fixtureProvider = provider;
    }
}
```

### . **DTO (Data Transfer Object)**
Separación entre objetos de dominio y transferencia de datos:

```java
record PartidoResponseDto(
    Long id,
    String equipoLocal,
    String equipoVisitante,
    Integer golLocal,
    Integer golVisitante,
    StatusPartido status
) {
    public static PartidoResponseDto fromDomainExistent(Partido partido) { ... }
}
```

### . **Cache**
Caché en memoria para partidos en vivo con actualización scheduled:

```java
@Service
public class PartidoServiceImpl implements PartidoService {
    private final Map<Long, Partido> cachePartidos = new ConcurrentHashMap<>();
    
    public List<Partido> partidosEnVivo() {
        return cachePartidos.values().stream().toList();
    }
}
```

### . **Scheduled**
Ejecución periódica de sincronización con API externa:

```java
@Scheduled(fixedRate = 60000)
public void llamarApiFootballVivo() {
    List<Partido> partidos = partidoService.encontrarTodosLosPartidosEnVivo();
    partidos.forEach(Partido::ejecutar);
    broadcastUseCase.broadcast(partidosDto);
}
```

### 9. **Server-Sent Events (SSE)**
Streaming en tiempo real de eventos:

```java
@GetMapping(value = "/partidos/stream", 
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public SseEmitter stream() {
    return registrarUseCase.registrar();
}
```

### 10. **Mapper**
Transformación entre diferentes capas:

```java
@Component
public class PartidoMapper {
    public Partido toNewDomain(FixtureData fixtureData, 
                               Equipo local, Equipo visitante) { ... }
    public void actualizarDesdeFixture(FixtureData fixture, Partido partido) { ... }
}
```

---

##  Funcionalidades Principales

### Gestión de Partidos
-  **Crear partidos** entre equipos
-  **Listar todos los partidos** registrados
-  **Obtener partidos en vivo** (en progreso)
-  **Stream en tiempo real** de actualizaciones (SSE)
-  **Sincronización automática** con API de fútbol

### Gestión de Equipos
-  **Cargar equipos** desde API externa (football-data.org)
-  **Listar equipos** por liga
-  **Gestión de logotipos**

### Gestión de Ligas
-  **Crear ligas** automáticamente
-  **Cache de ligas** para optimizar queries

### Gestión de Eventos
-  **Generar eventos** automáticos durante partidos
-  **Mapeo de eventos** desde API externa
-  **Clasificación por tipo** (gol, tarjeta, sustitución)

### Gestión de Jugadores
-  **Cargar jugadores** desde API externa
-  **Listar jugadores por equipo**

---

## Stack Tecnológico

### Framework & Runtime
- **Spring Boot 4.0.3** - Framework principal
- **Java 21** - Lenguaje
- **Maven 3.x** - Build automation

### Persistencia
- **Spring Data JPA** - ORM
- **PostgreSQL** - Base de datos (producción)
- **H2 Database** - Base de datos (testing)

### API & Comunicación
- **Spring Web (REST)** - Endpoints REST
- **Spring WebSocket** - Comunicación bidireccional
- **RestTemplate** - Cliente HTTP
- **Server-Sent Events (SSE)** - Streaming en tiempo real

### Validación & Documentación
- **Spring Validation** - Bean Validation
- **SpringDoc OpenAPI 3.0.3** - Swagger/OpenAPI
- **Lombok** - Reducción de boilerplate

### Testing
- **JUnit 5** - Framework de testing
- **Mockito** - Mocking
- **Spring Boot Test** - Contexto de test

### DevOps & Deployment
- **Docker** - Containerización
- **docker-compose** - Orquestación local
- **Spring Boot Maven Plugin** - Packging ejecutable

---

##  Estructura del Proyecto

```
src/main/java/com/forum/api/
│
├── ApiApplication.java                 # Entry point
│
├── domain/                              # ★ CAPA DE DOMINIO (Lógica pura)
│   ├── model/
│   │   ├── Equipo.java
│   │   ├── Liga.java
│   │   ├── Jugador.java
│   │   ├── evento/
│   │   │   ├── EventoDelPartido.java
│   │   │   ├── TipoEvento.java
│   │   │   └── StoreEvent.java
│   │   └── partido/
│   │       ├── Partido.java
│   │       └── StatusPartido.enum
│   ├── estado/                          # State Machine
│   │   ├── EstadoPartido.interface
│   │   ├── AbstractEstadoPartido.java
│   │   ├── PrimerTiempo.java
│   │   ├── Descanso.java
│   │   ├── SegundoTiempo.java
│   │   └── Finalizado.java
│   ├── service/
│   │   └── EventoHandlerFactory.java     # Factory Pattern
│   └── exception/
│       ├── PartidoNotFoundException.java
│       └── EventoNotFoundException.java
│
├── application/                         # ★ CAPA DE APLICACIÓN (Use Cases)
│   ├── in/
│   │   ├── PartidoService.interface
│   │   ├── EquipoService.interface
│   │   ├── JugadorService.interface
│   │   ├── LigaService.interface
│   │   ├── DataApiProvider.interface    # Puerto entrada
│   │   ├── SSeRegistrarUseCase.interface
│   │   ├── SSeBroadcastUseCase.interface
│   │   ├── command/
│   │   │   └── CrearPartidoCommand.java
│   │   └── dto/
│   │       ├── PartidoResponseDto.java
│   │       ├── EquipoResponseDto.java
│   │       ├── FixtureData.java
│   │       └── ... (más DTOs)
│   ├── out/
│   │   ├── PartidoRepository.interface  # Puerto salida
│   │   ├── EquipoRepository.interface
│   │   └── JugadorRepository.interface
│   └── service/
│       ├── PartidoServiceImpl.java       # Lógica de negocio
│       ├── EquipoServiceImpl.java
│       ├── PartidoMapper.java
│       └── ... (más servicios)
│
└── infra/                               # ★ CAPA DE INFRAESTRUCTURA (Adaptadores)
    └── adapter/
        ├── in/
        │   ├── rest/
        │   │   ├── PartidoController.java
        │   │   ├── EquipoController.java
        │   │   ├── JugadorController.java
        │   │   └── dto/ (Request/Response DTOs)
        │   ├── scheduler/
        │   │   ├── ApiCallScheduler.java  
        │   │   └── MatchTaskSchedule.java
        │   └── stream/
        │       └── SSeService.java        
        │
        └── out/
            ├── persistence/
            │   ├── entities/
            │   │   ├── PartidoJpaEntity.java
            │   │   ├── EquipoEntityJpa.java
            │   │   ├── JugadorEntityJpa.java
            │   │   ├── EventoDelPartidoJpaEntity.java
            │   │   └── LigaJpaEntity.java
            │   └── repository/
            │       ├── PartidoRepositoryImpl.java
            │       └── ... (más implementaciones)
            │
            ├── api/
            │   ├── ApiFootballConsumer.java  
            │   └── dto/ (DTO externos)
            │
            └── config/
                └── SwaggerConfig.java      
```

---

##  Flujo de Datos

### Crear un Partido

```
POST /api/partidos
    ↓
PartidoController.agregarNuevoPartido()
    ↓
PartidoServiceImpl.guardarPartido(CrearPartidoCommand)
    ↓
EquipoService.encontrarEquipoPorId() [2 veces]
    ↓
Partido.createFromLocal() [Factory Method]
    ↓
PartidoRepository.savePartido()
    ↓
PartidoJpaEntity.fromDomain()
    ↓
Database (PostgreSQL)
    ↓
Response: PartidoResponseDto (201 Created)
```

### Actualización en Tiempo Real

```
@Scheduled(fixedRate = 60000)
ApiCallScheduler.llamarApiFootballVivo()
    ↓
PartidoService.encontrarTodosLosPartidosEnVivo()
    ↓
DataApiProvider.proveerDatosFixture() [API externa]
    ↓
PartidoServiceImpl.guardarOActualizarPartido() [para c/fixture]
    ↓
Partido.ejecutar() [State Machine]
    ↓
SSeBroadcastUseCase.broadcast() [a todos los clientes]
    ↓
Clientes GET /api/partidos/stream reciben evento
```

---

##  Testing

**tests unitarios**:

```java
@ExtendWith(MockitoExtension.class)
@DisplayName("PartidoServiceImpl - Suite de Tests Unitarios")
class PartidoServiceImplTest {
    
    @Nested
    @DisplayName("Tests para encontrarTodosLosPartidosEnVivo()")
    class EncontrarTodosLosPartidosEnVivoTests {
        @Test
        void testEncontrarTodosLosPartidosEnVivo_ConPartidos_Exitoso() { ... }
    }
}
```

---

## Docker & Deployment

### Ejecutar localmente con Docker Compose

```bash
docker-compose up -d
```

**Servicios incluidos:**
- **API**: Puerto 8080
- **PostgreSQL**: Puerto 5432

### Dockerfile

```dockerfile
FROM openjdk:21-slim
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

---

##  Endpoints Principales

| Método | Endpoint | Descripción | Response |
|--------|----------|-------------|----------|
| `GET` | `/api/partidos` | Listar todos los partidos | `200` |
| `GET` | `/api/partidos/envivo` | Partidos en vivo | `200` |
| `GET` | `/api/partidos/stream` | Stream SSE tiempo real | `200` |
| `POST` | `/api/partidos` | Crear nuevo partido | `201` |
| `POST` | `/api/partidos/actualizar` | Actualizar partido | `200` |
| `DELETE` | `/api/partidos/{id}` | Borrar partido | `204` |
| `GET` | `/api/equipos` | Listar equipos | `200` |
| `POST` | `/api/equipos` | Cargar equipos desde API | `200` |
| `POST` | `/api/jugadores` | Crear jugador | `201` |

**Documentación Interactiva:** http://localhost:8080/swagger-ui.html

---

##  Características Destacadas

###  Real-time Updates (SSE)
```java
@GetMapping(value = "/partidos/stream", 
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public SseEmitter stream() {
    return registrarUseCase.registrar();
}
```

**Uso Cliente:**
```javascript
const eventSource = new EventSource('/api/partidos/stream');
eventSource.addEventListener('partidos', (event) => {
    console.log('Actualización:', JSON.parse(event.data));
});
```

###  Sincronización Automática
Scheduled task que cada 60 segundos:
1. Obtiene fixtures activos de API externa
2. Actualiza marcadores y estados
3. Genera eventos si hay cambios
4. Broadcast a clientes conectados

### State Machine para Partidos
Estados: `PROGRAMADO` → `PRIMER_TIEMPO` → `MEDIO_TIEMPO` → `SEGUNDO_TIEMPO` → `FINALIZADO`

Cada estado tiene comportamiento y transiciones definidas.

---

##  Recursos Utilizados

- **API Externa**: [Football-Data.org API](https://www.football-data.org/)
- **Spring Boot Docs**: [spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
- **Hexagonal Architecture**: [alistair.cockburn.us](https://alistair.cockburn.us/hexagonal-architecture/)

---

##  Patrones y Conceptos Aplicados

| Patrón | Ubicación | Beneficio |
|--------|-----------|-----------|
| **Hexagonal Architecture** | Estructura completa | Desacoplamiento, testabilidad |
| **State Machine** | `domain.estado.*` | Transiciones explícitas |
| **Factory** | `EventoHandlerFactory` | Extensibilidad |
| **Repository** | `application.out.*` | Abstracción de persistencia |
| **DTO** | `*ResponseDto`, `*RequestDto` | Separación de capas |
| **Mapper** | `PartidoMapper`, etc. | Transformación entre modelos |
| **Dependency Injection** | `@Autowired`, constructores | Flexibilidad, testing |
| **Cache** | `Map<Long, Partido>` | Optimización |
| **Scheduled Tasks** | `ApiCallScheduler` | Sincronización periódica |
| **SSE** | `SSeService` | Real-time communication |

---

##  Próximas Mejoras

- [ ] Agregar autenticación y autorización (JWT)
- [ ] Circuit breaker para API externa (Resilience4j)
- [ ] Tests de integración E2E

---

##  Autor

**Esteban Rojas**  
GitHub: [@EstebanRojasss](https://github.com/EstebanRojasss)

---

## 📞 Contacto & Soporte

Para reportar bugs o sugerir features, crear un [Issue](https://github.com/EstebanRojasss/pelota-yara/issues).

---

<p align="center">
  <strong>Built with using Spring Boot & Hexagonal Architecture</strong>
</p>
