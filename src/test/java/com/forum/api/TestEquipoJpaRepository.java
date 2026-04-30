package com.forum.api;

import com.forum.api.domain.model.Competencias;
import com.forum.api.infra.adapter.out.persistence.entities.EquipoEntityJpa;
import com.forum.api.infra.adapter.out.persistence.repository.EquipoJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
public class TestEquipoJpaRepository {


    @Autowired
    private EquipoJpaRepository equipoRepository;

    @Autowired
    private TestEntityManager entityManager;


//    @Test
//    void shouldFindNombreEquipo(){
//        EquipoEntityJpa equipoEntity = new EquipoEntityJpa();
//        equipoEntity.setId(null);
//                equipoEntity.setNombre("Olimpia");
//                equipoEntity.setApodo("El decano");
//                equipoEntity.setFundacion(
//                        LocalDate.of(
//                        1902,
//                        7,
//                        5)
//                );
//        entityManager.persist(equipoEntity);
//        entityManager.flush();
//
//
//        EquipoEntityJpa result = equipoRepository.findByNombre("Olimpia")
//                .orElseThrow(() -> new EquipoNotFoundException("El equipo no existe"));
//
//        System.out.println("RESULTADO: " + result.getNombre());
//        assertEquals("Resultado ", "Olimpia", result.getNombre());
//    }

//    @Test
//    void shouldFindAllTeamsWithCompetitions(){
//        EquipoEntityJpa equipo2 = new EquipoEntityJpa();
//        EquipoEntityJpa equipo3 = new EquipoEntityJpa();
//
//        EquipoEntityJpa equipo1 = new EquipoEntityJpa();
//        equipo1.setId(null);
//        equipo1.setNombre("Olimpia");
//        equipo1.setApodo("El decano");
//        Set<Competencias> competencias1 = new HashSet<>();
//        Set<Competencias> competencias2= new HashSet<>();
//        Set<Competencias> competencias3 = new HashSet<>();
//
//        competencias1.add(Competencias.APERTURA);
//        competencias1.add(Competencias.CLAUSURA);
//        competencias1.add(Competencias.INTEGRADO);
//
//        competencias2.add(Competencias.APERTURA);
//        competencias2.add(Competencias.INTEGRADO);
//        competencias2.add(Competencias.CLAUSURA);
//
//        competencias3.add(Competencias.APERTURA);
//        competencias3.add(Competencias.CLAUSURA);
//        competencias3.add(Competencias.INTEGRADO);
//
//        equipo1.setCompetencias(competencias1);
//
//        equipo2.setId(null);
//        equipo2.setNombre("Cerro");
//        equipo2.setApodo("El ciclon");
//        equipo2.setCompetencias(competencias2);
//
//        equipo3.setId(null);
//        equipo3.setNombre("2 mayo");
//        equipo3.setApodo("El gallo nortenho");
//        equipo3.setCompetencias(competencias3);
//
//
//        entityManager.persist(equipo1);
//        entityManager.persist(equipo2);
//        entityManager.persist(equipo3);
//
//        entityManager.flush();
//        entityManager.clear();
//
//        List<EquipoEntityJpa> teams =
//                equipoRepository.findEquiposWithCompetencias();
//
//        System.out.println("Resultado: " + teams.stream().toList());
//    }

}
