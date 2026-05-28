package com.forum.api;
import com.forum.api.application.in.*;
import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.LigaDataDto;
import com.forum.api.application.in.dto.StatusPartidoFixture;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.application.out.PartidoRepository;
import com.forum.api.application.service.PartidoContextService;
import com.forum.api.application.service.PartidoMapper;
import com.forum.api.application.service.PartidoServiceImpl;
import com.forum.api.domain.exception.PartidoNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.Liga;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    @DisplayName("PartidoServiceImpl - Suite de Tests Unitarios")
    class PartidoServiceImplTest {

        @Mock
        private PartidoRepository partidoRepository;

        @Mock
        private EquipoService equipoService;

        @Mock
        private DataApiProvider fixtureProvider;

        @Mock
        private PartidoMapper partidoMapper;

        @Mock
        private LigaService ligaService;

        @Mock
        private EventoDelPartidoService eventoDelPartidoService;

        @Mock
        private JugadorService jugadorService;

        @Mock
        private PartidoContextService partidoContextService;

        @InjectMocks
        private PartidoServiceImpl partidoService;

        private Equipo equipoLocal;
        private Equipo equipoVisitante;
        private Liga liga;
        private Partido partido;
        private CrearPartidoCommand crearPartidoCommand;
        TeamDataDto local1;
        TeamDataDto local2;
        TeamDataDto local3;
        TeamDataDto visit1;
        TeamDataDto visit2;
        TeamDataDto visit3;
        LigaDataDto liga1;
        LigaDataDto liga2 ;
        LigaDataDto liga3;

        @BeforeEach
        void setUp() {
            equipoLocal = crearEquipo(1L, "Cerro Porteño", 123L);
            equipoVisitante = crearEquipo(2L, "Olimpia", 4234L);
            liga = crearLiga(1L, "Paraguaya", "Paraguay");
            partido = crearPartido(1L, equipoLocal, equipoVisitante, 0, 0, StatusPartido.NO_INICIADO);
            crearPartidoCommand = new CrearPartidoCommand(1L, 2L);
             local1 = crearTeamDataDto(1L);
             local2 = crearTeamDataDto(2L);
             local3 = crearTeamDataDto(3L);
            visit1 = crearTeamDataDto(3L);
            visit2 = crearTeamDataDto(3L);
            visit3 = crearTeamDataDto(3L);
            liga1 = crearLigaDataDto(1L);
            liga2 = crearLigaDataDto(1L);
            liga3 = crearLigaDataDto(1L);

        }

        @Nested
        @DisplayName("Tests para guardarPartido()")
        class GuardarPartidoTests {

            @Test
            @DisplayName("Debe guardar un partido exitosamente con equipos válidos")
            void testGuardarPartido_ConEquiposValidos_ExitosaMe() {

                when(equipoService.encontrarEquipoPorId(1L)).thenReturn(equipoLocal);
                when(equipoService.encontrarEquipoPorId(2L)).thenReturn(equipoVisitante);
                when(partidoRepository.savePartido(any(Partido.class))).thenReturn(partido);

                Partido resultado = partidoService.guardarPartido(crearPartidoCommand);

                assertNotNull(resultado);
                assertEquals(equipoLocal, resultado.getEquipoLocal());
                assertEquals(equipoVisitante, resultado.getEquipoVisitante());
                assertEquals(StatusPartido.NO_INICIADO, resultado.getStatus());
                verify(equipoService, times(1)).encontrarEquipoPorId(1L);
                verify(equipoService, times(1)).encontrarEquipoPorId(2L);
                verify(partidoRepository, times(1)).savePartido(any(Partido.class));
            }

            @Test
            @DisplayName("Debe lanzar excepción cuando el equipo local no existe")
            void testGuardarPartido_EquipoLocalNoExiste_LanzaExcepcion() {
                when(equipoService.encontrarEquipoPorId(1L))
                        .thenThrow(new RuntimeException("Equipo no encontrado"));

                assertThrows(RuntimeException.class, () -> partidoService.guardarPartido(crearPartidoCommand));
                verify(equipoService, times(1)).encontrarEquipoPorId(1L);
                verify(partidoRepository, never()).savePartido(any());
            }

            @Test
            @DisplayName("Debe lanzar excepción cuando el equipo visitante no existe")
            void testGuardarPartido_EquipoVisitanteNoExiste_LanzaExcepcion() {
                when(equipoService.encontrarEquipoPorId(1L)).thenReturn(equipoLocal);
                when(equipoService.encontrarEquipoPorId(2L))
                        .thenThrow(new RuntimeException("Equipo no encontrado"));

                assertThrows(RuntimeException.class, () -> partidoService.guardarPartido(crearPartidoCommand));
                verify(equipoService, times(2)).encontrarEquipoPorId(anyLong());
                verify(partidoRepository, never()).savePartido(any());
            }

            @Test
            @DisplayName("Debe inicializar el partido con estado NO_INICIADO")
            void testGuardarPartido_EstadoInicial_NoIniciado() {

                when(equipoService.encontrarEquipoPorId(1L)).thenReturn(equipoLocal);
                when(equipoService.encontrarEquipoPorId(2L)).thenReturn(equipoVisitante);
                when(partidoRepository.savePartido(any(Partido.class))).thenReturn(partido);


                Partido resultado = partidoService.guardarPartido(crearPartidoCommand);


                assertEquals(StatusPartido.NO_INICIADO, resultado.getStatus());
                assertEquals(0, resultado.getGolLocal());
                assertEquals(0, resultado.getGolVisitante());
            }
        }

        @Nested
        @DisplayName("Tests para encontrarPartido()")
        class EncontrarPartidoTests {

            @Test
            @DisplayName("Debe encontrar un partido por ID exitosamente")
            void testEncontrarPartido_ConIdValido_Exitoso() {

                when(partidoRepository.findPartidoById(1L)).thenReturn(Optional.of(partido));


                Partido resultado = partidoService.encontrarPartido(1L);


                assertNotNull(resultado);
                assertEquals(partido.getId(), resultado.getId());
                assertEquals(equipoLocal, resultado.getEquipoLocal());
                verify(partidoRepository, times(1)).findPartidoById(1L);
            }

            @Test
            @DisplayName("Debe lanzar PartidoNotFoundException cuando el partido no existe")
            void testEncontrarPartido_PartidoNoExiste_LanzaExcepcion() {

                when(partidoRepository.findPartidoById(999L)).thenReturn(Optional.empty());


                assertThrows(PartidoNotFoundException.class, () -> partidoService.encontrarPartido(999L));
                verify(partidoRepository, times(1)).findPartidoById(999L);
            }

            @Test
            @DisplayName("El mensaje de error debe ser descriptivo")
            void testEncontrarPartido_MensajeError_Descriptivo() {

                when(partidoRepository.findPartidoById(999L)).thenReturn(Optional.empty());

                PartidoNotFoundException exception = assertThrows(
                        PartidoNotFoundException.class,
                        () -> partidoService.encontrarPartido(999L)
                );
                assertTrue(exception.getMessage().contains("no se encuentra"));
            }
        }

        @Nested
        @DisplayName("Tests para borrarPartido()")
        class BorrarPartidoTests {

            @Test
            @DisplayName("Debe borrar un partido exitosamente")
            void testBorrarPartido_ConIdValido_Exitoso() {
                doNothing().when(partidoRepository).deletePartido(1L);

                assertDoesNotThrow(() -> partidoService.borrarPartido(1L));

                verify(partidoRepository, times(1)).deletePartido(1L);
            }

            @Test
            @DisplayName("Debe lanzar PartidoNotFoundException cuando hay error en la eliminación")
            void testBorrarPartido_ErrorEnEliminacion_LanzaExcepcion() {
                doThrow(new RuntimeException("Error BD")).when(partidoRepository).deletePartido(999L);

                assertThrows(PartidoNotFoundException.class, () -> partidoService.borrarPartido(999L));
                verify(partidoRepository, times(1)).deletePartido(999L);
            }
        }

        @Nested
        @DisplayName("Tests para actualizarDatosDePartido()")
        class ActualizarDatosPartidoTests {

            @Test
            @DisplayName("Debe actualizar los datos del partido exitosamente")
            void testActualizarDatosDePartido_ConDatosValidos_Exitoso() {
                Partido partidoActualizado = crearPartido(1L, equipoLocal, equipoVisitante, 2, 1, StatusPartido.PRIMER_TIEMPO);
                when(partidoRepository.savePartido(any(Partido.class))).thenReturn(partidoActualizado);

                Partido resultado = partidoService.actualizarDatosDePartido(partidoActualizado);

                assertNotNull(resultado);
                assertEquals(2, resultado.getGolLocal());
                assertEquals(1, resultado.getGolVisitante());
                assertEquals(StatusPartido.PRIMER_TIEMPO, resultado.getStatus());
                verify(partidoRepository, times(1)).savePartido(any(Partido.class));
            }

            @Test
            @DisplayName("Debe preservar la integridad del partido original")
            void testActualizarDatosDePartido_PreservaIntegridad() {
                Partido original = crearPartido(1L, equipoLocal, equipoVisitante, 0, 0, StatusPartido.NO_INICIADO);
                when(partidoRepository.savePartido(original)).thenReturn(original);

                Partido resultado = partidoService.actualizarDatosDePartido(original);

                assertEquals(original.getId(), resultado.getId());
                assertEquals(original.getEquipoLocal(), resultado.getEquipoLocal());
                assertEquals(original.getEquipoVisitante(), resultado.getEquipoVisitante());
            }
        }

        @Nested
        @DisplayName("Tests para encontrarTodosLosPartidosEnVivo()")
        class EncontrarTodosLosPartidosEnVivoTests {


            @Test
            @DisplayName("Debe retornar lista de partidos en vivo desde API")
            void testEncontrarTodosLosPartidosEnVivo_ConPartidos_Exitoso() {

                FixtureData fixture1 = crearFixtureData(1L, local1, visit1, 0, 2, liga1);
                FixtureData fixture2 = crearFixtureData(2L, local2, visit2, 2, 1, liga2);

                when(fixtureProvider.proveerDatosFixture()).thenReturn(List.of(fixture1, fixture2));
                when(equipoService.resolverExistenciaEquipo(any())).thenReturn(equipoLocal, equipoVisitante);
                when(ligaService.resolverExistenciaLiga(any())).thenReturn(liga);
                when(partidoRepository.savePartido(any(Partido.class))).thenReturn(partido);
                when(jugadorService.listarJugadoresPorEquipo(any())).thenReturn(Collections.emptyList());

                List<Partido> resultado = partidoService.encontrarTodosLosPartidosEnVivo();


                assertNotNull(resultado);
                assertEquals(2, resultado.size());
                verify(fixtureProvider, times(1)).proveerDatosFixture();
                verify(equipoService, times(4)).resolverExistenciaEquipo(any());
                verify(ligaService, times(2)).resolverExistenciaLiga(any());
            }

            @Test
            @DisplayName("Debe retornar lista vacía cuando no hay partidos en vivo")
            void testEncontrarTodosLosPartidosEnVivo_SinPartidos_RetornaVacio() {
                when(fixtureProvider.proveerDatosFixture()).thenReturn(Collections.emptyList());

                List<Partido> resultado = partidoService.encontrarTodosLosPartidosEnVivo();

                assertNotNull(resultado);
                assertTrue(resultado.isEmpty());
                verify(fixtureProvider, times(1)).proveerDatosFixture();
            }

            @Test
            @DisplayName("Debe cachear partidos después de procesarlos")
            void testEncontrarTodosLosPartidosEnVivo_DebeCache() {
                FixtureData fixture = crearFixtureData(1L, local1, visit1, 1, 0, liga1);
                Partido partidoEnCache = crearPartido(1L, equipoLocal, equipoVisitante, 1, 0, StatusPartido.PRIMER_TIEMPO);

                when(fixtureProvider.proveerDatosFixture()).thenReturn(List.of(fixture));
                when(equipoService.resolverExistenciaEquipo(any())).thenReturn(equipoLocal, equipoVisitante);
                when(ligaService.resolverExistenciaLiga(any())).thenReturn(liga);
                when(partidoRepository.savePartido(any(Partido.class))).thenReturn(partidoEnCache);
                when(jugadorService.listarJugadoresPorEquipo(any())).thenReturn(Collections.emptyList());


                List<Partido> resultado = partidoService.encontrarTodosLosPartidosEnVivo();
                List<Partido> resultadoEnVivo = partidoService.partidosEnVivo();

                assertEquals(1, resultadoEnVivo.size());
                assertEquals(partidoEnCache.getId(), resultadoEnVivo.get(0).getId());
            }

            @Test
            @DisplayName("Debe inicializar contexto del partido con jugadores")
            void testEncontrarTodosLosPartidosEnVivo_InicializaContexto() {
                FixtureData fixture = crearFixtureData(1L, local1, visit1, 0, 0, liga1);
                List<Jugador> jugadores = Arrays.asList(
                        crearJugador(1L, "Tevez", equipoLocal),
                        crearJugador(2L, "Berbatov", equipoVisitante)
                );

                when(fixtureProvider.proveerDatosFixture()).thenReturn(List.of(fixture));
                when(equipoService.resolverExistenciaEquipo(any())).thenReturn(equipoLocal, equipoVisitante);
                when(ligaService.resolverExistenciaLiga(any())).thenReturn(liga);
                when(partidoRepository.savePartido(any(Partido.class))).thenReturn(partido);
                when(jugadorService.listarJugadoresPorEquipo(any())).thenReturn(jugadores);

                partidoService.encontrarTodosLosPartidosEnVivo();

                ArgumentCaptor<List<Jugador>> jugadoresCaptor = ArgumentCaptor.forClass(List.class);
                verify(partidoContextService, times(1)).inicializarContexto(eq(partido), jugadoresCaptor.capture());
                assertEquals(4, jugadoresCaptor.getValue().size());
            }
        }

        @Nested
        @DisplayName("Tests para partidosEnVivo()")
        class PartidosEnVivoTests {

            @Test
            @DisplayName("Debe retornar partidos cacheados en vivo")
            void testPartidosEnVivo_RetornaPartidosCacheados() {
                FixtureData fixture = crearFixtureData(1L,local1 , visit1, 1, 0, liga1);
                Partido partidoEnCache = crearPartido(1L, equipoLocal, equipoVisitante, 1, 0, StatusPartido.PRIMER_TIEMPO);

                when(fixtureProvider.proveerDatosFixture()).thenReturn(List.of(fixture));
                when(equipoService.resolverExistenciaEquipo(any())).thenReturn(equipoLocal, equipoVisitante);
                when(ligaService.resolverExistenciaLiga(any())).thenReturn(liga);
                when(partidoRepository.savePartido(any(Partido.class))).thenReturn(partidoEnCache);
                when(jugadorService.listarJugadoresPorEquipo(any())).thenReturn(Collections.emptyList());

                partidoService.encontrarTodosLosPartidosEnVivo();
                List<Partido> resultado = partidoService.partidosEnVivo();


                assertEquals(1, resultado.size());
                assertEquals(1, resultado.get(0).getGolLocal());
                assertEquals(0, resultado.get(0).getGolVisitante());
            }

            @Test
            @DisplayName("Debe retornar lista vacía cuando no hay partidos en cache")
            void testPartidosEnVivo_SinPartidos_RetornaVacio() {
                List<Partido> resultado = partidoService.partidosEnVivo();

                assertNotNull(resultado);
                assertTrue(resultado.isEmpty());
            }
        }

        @Nested
        @DisplayName("Tests para listarTodosLosPartidos()")
        class ListarTodosLosPartidosTests {

            @Test
            @DisplayName("Debe listar todos los partidos desde repositorio")
            void testListarTodosLosPartidos_Exitoso() {
                List<Partido> partidos = Arrays.asList(
                        crearPartido(1L, equipoLocal, equipoVisitante, 0, 0, StatusPartido.NO_INICIADO),
                        crearPartido(2L, equipoLocal, equipoVisitante, 1, 0, StatusPartido.PRIMER_TIEMPO)
                );
                when(partidoRepository.findAllPartidos()).thenReturn(partidos);

                List<Partido> resultado = partidoService.listarTodosLosPartidos();

                assertEquals(2, resultado.size());
                verify(partidoRepository, times(1)).findAllPartidos();
            }

            @Test
            @DisplayName("Debe retornar lista vacía cuando no hay partidos")
            void testListarTodosLosPartidos_SinPartidos_RetornaVacio() {
                when(partidoRepository.findAllPartidos()).thenReturn(Collections.emptyList());

                List<Partido> resultado = partidoService.listarTodosLosPartidos();


                assertTrue(resultado.isEmpty());
                verify(partidoRepository, times(1)).findAllPartidos();
            }
        }

        @Nested
        @DisplayName("Tests para encontrarPartidoPorFixtureId()")
        class EncontrarPartidoPorFixtureIdTests {

            @Test
            @DisplayName("Debe encontrar partido por Fixture ID exitosamente")
            void testEncontrarPartidoPorFixtureId_Exitoso() {
                when(partidoRepository.findByFixtureId(1L)).thenReturn(Optional.of(partido));

                Optional<Partido> resultado = partidoService.encontrarPartidoPorFixtureId(1L);

                assertTrue(resultado.isPresent());
                assertEquals(partido.getId(), resultado.get().getId());
                verify(partidoRepository, times(1)).findByFixtureId(1L);
            }

            @Test
            @DisplayName("Debe retornar Optional vacío cuando partido no existe")
            void testEncontrarPartidoPorFixtureId_NoExiste_RetornaVacio() {
                when(partidoRepository.findByFixtureId(999L)).thenReturn(Optional.empty());


                Optional<Partido> resultado = partidoService.encontrarPartidoPorFixtureId(999L);

                assertTrue(resultado.isEmpty());
                verify(partidoRepository, times(1)).findByFixtureId(999L);
            }
        }

//        @Nested
//        @DisplayName("Tests para obtenerNuevosEventos()")
//        class ObtenerNuevosEventosTests {
//
//            @Test
//            @DisplayName("Debe obtener nuevos eventos del partido")
//            void testObtenerNuevosEventos_Exitoso() {
//                EventoDelPartido evento1 = new EventoDelPartido();
//                EventoDelPartido evento2 = new EventoDelPartido();
//                List<EventoDelPartido> eventos = Arrays.asList(evento1, evento2);
//
//                when(eventoDelPartidoService.listarEventosDelPartidoAPI(partido)).thenReturn(eventos);
//
//                List<EventoDelPartido> resultado = partidoService.obtenerNuevosEventos(partido);
//
//                assertEquals(2, resultado.size());
//                verify(eventoDelPartidoService, times(1)).listarEventosDelPartidoAPI(partido);
//            }
//
//            @Test
//            @DisplayName("Debe retornar lista vacía cuando no hay eventos")
//            void testObtenerNuevosEventos_SinEventos_RetornaVacio() {
//                when(eventoDelPartidoService.listarEventosDelPartidoAPI(partido))
//                        .thenReturn(Collections.emptyList());
//
//                List<EventoDelPartido> resultado = partidoService.obtenerNuevosEventos(partido);
//
//                assertTrue(resultado.isEmpty());
//                verify(eventoDelPartidoService, times(1)).listarEventosDelPartidoAPI(partido);
//            }
//        }

        private Equipo crearEquipo(Long id, String nombre, Long fixtureId) {
            Equipo equipo = Equipo.restore(id, nombre,"Paraguay", 1902,  fixtureId, "Logo.url");
            return equipo;
        }

        private Liga crearLiga(Long id, String nombre, String pais) {
            return Liga.restore(id, nombre, pais, id, 2024);
        }

        private Partido crearPartido(Long id, Equipo equipoLocal, Equipo equipoVisitante,
                                     Integer golLocal, Integer golVisitante, StatusPartido status) {
            return Partido.restore(
                    id,
                    status,
                    equipoLocal,
                    equipoVisitante,
                    golLocal,
                    golVisitante,
                    id,
                    liga,
                    0,
                    Instant.now()
            );
        }

        private FixtureData crearFixtureData(Long id, TeamDataDto local, TeamDataDto visitante, Integer golLocal, Integer golVisitante, LigaDataDto liga) {
            return new FixtureData(
                    id,
                    local,
                    visitante,
                    golLocal,
                    golVisitante,
                    12 ,
                    StatusPartidoFixture.FIRST_HALF,
                    4,
                    liga
            );
        }

        private TeamDataDto crearTeamDataDto(Long id){
            return new TeamDataDto(id, "a", "sd", "paraugya", 1000);
        }

        private LigaDataDto crearLigaDataDto(Long id){
            return new LigaDataDto(id, "as", "loreto", 2026);
        }

        private Jugador crearJugador(Long id, String nombre, Equipo equipo) {
            Jugador jugador = Jugador.restore(id, nombre, 10, 100L, equipo);
            return jugador;
        }
    }

