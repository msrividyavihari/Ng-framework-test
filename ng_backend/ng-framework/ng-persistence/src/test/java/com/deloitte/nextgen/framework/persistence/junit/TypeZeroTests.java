package com.deloitte.nextgen.framework.persistence.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:config/namespace-application-context.xml")
@DataJpaTest
class TypeZeroTests {

    //    @Autowired private DataSource dataSource;
//    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired
    private TestEntityManager entityManager;

    //@Autowired private TypeZeroEntityRepository typeZeroEntityRepository;

    private TypeZeroEntity typeZero1;
    private TypeZeroEntity typeZero2;
    private TypeZeroEntity typeZero3;

    @BeforeEach
    void setUp() {

        //typeZero1 = new TypeZeroEntity("Mukesh", 30, true);

        //typeZero2 = new TypeZeroEntity("Nishant", 25, true);
        //typeZero3 = new TypeZeroEntity("Axit", 35, false);
        //typeZeroEntityRepository.save(typeZero1);
        System.out.println("typeZero1 = " + typeZero1);
    }

    @AfterEach
    void clearUp() {

        //typeZeroEntityRepository.deleteAll();
        System.out.println("Deleted all entries");

    }

    @Test
    void testCreation() {

        //Query countQuery = entityManager.createQuery("select count(u) from TypeZeroEntity T0");
        //Long before = (Long) countQuery.getSingleResult();

        //flushTestUsers();

        //assertThat((Long) countQuery.getSingleResult()).isEqualTo(before + 4L);

        assertNotNull(entityManager);
    }

}
