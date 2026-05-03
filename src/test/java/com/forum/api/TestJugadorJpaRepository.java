package com.forum.api;

import com.forum.api.infra.adapter.out.persistence.repository.JugadorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Set;

@DataJpaTest
public class TestJugadorJpaRepository {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private JugadorJpaRepository repository;

//    @Test
//    void shouldReturnAllPlayersFromTeam(){
//        EquipoEntityJpa equipo1 = new EquipoEntityJpa();
//        equipo1.setNombre("Olimpia");
//        equipo1.setPais("El decano");
//
//        em.persist(equipo1);
//
//        JugadorEntityJpa jugador1 = new JugadorEntityJpa();
//        jugador1.setId(null);
//        jugador1.setNombre("Lionel Messi");
//        jugador1.setEdad(38);
//        jugador1.setNacionalidad("Argentina");
//        jugador1.setEquipo(equipo1);
//
//        JugadorEntityJpa jugador2 = new JugadorEntityJpa();
//        jugador2.setId(null);
//        jugador2.setNombre("Kylian Mbappé");
//        jugador2.setEdad(27);
//        jugador2.setNacionalidad("Francia");
//
//        jugador2.setEquipo(equipo1);
//        JugadorEntityJpa jugador3 = new JugadorEntityJpa();
//        jugador3.setId(null);
//        jugador3.setNombre("Virgil van Dijk");
//        jugador3.setEdad(34);
//        jugador3.setNacionalidad("Países Bajos");
//        jugador3.setEquipo(equipo1);
//
//        JugadorEntityJpa jugador4 = new JugadorEntityJpa();
//        jugador4.setId(null);
//        jugador4.setNombre("Jude Bellingham");
//        jugador4.setEdad(22);
//        jugador4.setNacionalidad("Inglaterra");
//        jugador4.setEquipo(equipo1);
//        JugadorEntityJpa jugador5 = new JugadorEntityJpa();
//        jugador5.setId(null);
//        jugador5.setNombre("Erling Haaland");
//        jugador5.setEdad(25);
//        jugador5.setNacionalidad("Noruega");
//        jugador5.setEquipo(equipo1);
//        em.persist(jugador1);
//        em.persist(jugador2);
//        em.persist(jugador3);
//        em.persist(jugador4);
//        em.persist(jugador5);
//
//        em.flush();
//        em.clear();
//
//        Set<JugadorEntityJpa> jugadores = repository.findAllJugadoresFromEquipo();
//
//
//        for(JugadorEntityJpa jugador : jugadores){
//            System.out.println("Jugador " + jugador.getNombre()+ ", Equipo: " + jugador.getEquipo().getNombre());
//        }
//    }
}
