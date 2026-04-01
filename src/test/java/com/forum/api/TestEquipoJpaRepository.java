package com.forum.api;

import com.forum.api.domain.model.Competencias;
import com.forum.api.infra.adapter.persistence.entities.EquipoEntityJpa;
import com.forum.api.infra.adapter.persistence.repository.EquipoJpaRepository;
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

    @Test
    void shouldFindAllTeamsWithCompetitions(){
        EquipoEntityJpa equipo2 = new EquipoEntityJpa();
        EquipoEntityJpa equipo3 = new EquipoEntityJpa();

        EquipoEntityJpa equipo1 = new EquipoEntityJpa();
        equipo1.setNombre("Olimpia");
        equipo1.setApodo("El decano");
        Set<Competencias> competencias = new HashSet<>();
        competencias.add(Competencias.APERTURA);
        competencias.add(Competencias.CLAUSURA);
        competencias.add(Competencias.INTEGRADO);

        equipo1.setCompetencias(competencias);

        equipo2.setNombre("Cerro");
        equipo2.setApodo("El ciclon");
        equipo2.setCompetencias(competencias);

        equipo3.setNombre("2 mayo");
        equipo3.setApodo("El gallo nortenho");
        equipo3.setCompetencias(competencias);


        entityManager.persist(equipo1);
        entityManager.persist(equipo2);
        entityManager.persist(equipo3);

        entityManager.flush();
        entityManager.clear();

        List<EquipoEntityJpa> teams =
                equipoRepository.findCompetenciasEquipos();

        System.out.println("Resultado: " + teams.stream().toList());
    }

}
