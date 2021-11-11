package com.deloitte.nextgen.framework.persistence.junit;

import com.deloitte.nextgen.framework.persistence.repository.TypeZeroEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The class <code> TypeZeroCRUDRepositoryTests </code> is used
 * to test CRUD operations for Type Zero Entity type class.
 *
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrudRepository.class, TypeZeroEntityRepository.class}, locations = "classpath:config/namespace-application-context.xml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TypeZeroCRUDRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TypeZeroEntityRepository typeZeroEntityRepository;

    private TypeZeroEntity typeZero1;
    private TypeZeroEntity typeZero2;

    /**
     * Tests Create operation for Type Zero Entity.
     */

    @Test
    void CreateOperationForTypeZeroEntity() {

        System.out.println("Inside CreateOperationForTypeZeroEntity method");

        String extractedName1 = null;
        String extractedName2 = null;

        String extractedCreateUserId1 = null;
        String extractedUpdateUserId1 = null;
        LocalDateTime extractedCreateDt1 = null;
        LocalDateTime extractedUpdateDt1 = null;

        String extractedCreateUserId2 = null;
        String extractedUpdateUserId2 = null;
        LocalDateTime extractedCreateDt2 = null;
        LocalDateTime extractedUpdateDt2 = null;

        EntityManager entityManager = testEntityManager.getEntityManager();

//      Create two instances of Type Zero Entity
        typeZero1 = new TypeZeroEntity();
        typeZero2 = new TypeZeroEntity();

        LocalDate aDate = LocalDate.now();

        typeZero1.setName("Rahul");
        typeZero1.setAge(32);
        typeZero1.setActive(true);
        typeZero1.setEffBeginDt(aDate);
        typeZero1.setDailyIncome(5.50);
        typeZero1.setAvgMonthlyIncome((float) 5.2);
        typeZero1.setMiddleInitial('N');
        typeZero1.setSiblings((short) 2);

        typeZeroEntityRepository.save(typeZero1);

        typeZero2.setName("Rathore");
        typeZero2.setAge(32);
        typeZero2.setActive(true);
        typeZero2.setEffBeginDt(aDate);
        typeZero2.setDailyIncome(5.50);
        typeZero2.setAvgMonthlyIncome((float) 5.2);
        typeZero2.setMiddleInitial('N');
        typeZero2.setSiblings((short) 2);

        typeZeroEntityRepository.save(typeZero2);

        System.out.println("typeZero1: "+ typeZero1);
        System.out.println("typeZero1: "+ typeZero2);

//      Count the no. of records in Database
        BigInteger rowCnt= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE").getSingleResult();
        System.out.println("Count of records in Database: " + rowCnt);

        List<TypeZeroEntity> aTypeZero1Extract1 = typeZeroEntityRepository.findByName("Rahul");

        for (TypeZeroEntity type : aTypeZero1Extract1) {
            extractedName1 = type.getName();
            extractedCreateUserId1 = type.getCreateUserId();
            extractedUpdateUserId1 = type.getUpdateUserId();
            extractedCreateDt1 = type.getCreateDt();
            extractedUpdateDt1 = type.getUpdateDt();
            System.out.println("aTypeZero1Extract1.getId(): " + type.getId());
            System.out.println("aTypeZero1Extract1.getEffBeginDt(): " + type.getEffBeginDt());
            System.out.println("aTypeZero1Extract1.getName(): " + type.getName());
            System.out.println("aTypeZero1Extract1.getAge(): " + type.getAge());
            System.out.println("aTypeZero1Extract1.isActive(): " + type.isActive());
            System.out.println("aTypeZero1Extract1.getDailyIncome(): " + type.getDailyIncome());
            System.out.println("aTypeZero1Extract1.getAvgMonthlyIncome(): " + type.getAvgMonthlyIncome());
            System.out.println("aTypeZero1Extract1.getMiddleInitial(): " + type.getMiddleInitial());
            System.out.println("aTypeZero1Extract1.getSiblings(): " + type.getSiblings());
            System.out.println("aTypeZero1Extract1.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeZero1Extract1.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeZero1Extract1.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeZero1Extract1.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeZero1Extract1.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeZero1Extract1.getArchiveDt(): " + type.getArchiveDt());
        }

        List<TypeZeroEntity> aTypeZero1Extract2 = typeZeroEntityRepository.findByName("Rathore");

        for (TypeZeroEntity type : aTypeZero1Extract2) {
            extractedName2 = type.getName();
            extractedCreateUserId2 = type.getCreateUserId();
            extractedUpdateUserId2 = type.getUpdateUserId();
            extractedCreateDt2 = type.getCreateDt();
            extractedUpdateDt2 = type.getUpdateDt();
            System.out.println("aTypeZero1Extract2.getId(): " + type.getId());
            System.out.println("aTypeZero1Extract2.getEffBeginDt(): " + type.getEffBeginDt());
            System.out.println("aTypeZero1Extract2.getName(): " + type.getName());
            System.out.println("aTypeZero1Extract2.getAge(): " + type.getAge());
            System.out.println("aTypeZero1Extract2.isActive(): " + type.isActive());
            System.out.println("aTypeZero1Extract2.getDailyIncome(): " + type.getDailyIncome());
            System.out.println("aTypeZero1Extract2.getAvgMonthlyIncome(): " + type.getAvgMonthlyIncome());
            System.out.println("aTypeZero1Extract2.getMiddleInitial(): " + type.getMiddleInitial());
            System.out.println("aTypeZero1Extract2.getSiblings(): " + type.getSiblings());
            System.out.println("aTypeZero1Extract2.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeZero1Extract2.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeZero1Extract2.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeZero1Extract2.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeZero1Extract2.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeZero1Extract2.getArchiveDt(): " + type.getArchiveDt());
        }

        Assert.isTrue("Rahul".equals(extractedName1));
        Assert.isTrue("Rathore".equals(extractedName2));

        Assert.notNull(extractedCreateUserId1);
        Assert.notNull(extractedCreateDt1);
        Assert.isNull(extractedUpdateUserId1);
        Assert.notNull(extractedUpdateDt1);

        Assert.notNull(extractedCreateUserId2);
        Assert.notNull(extractedCreateDt2);
        Assert.isNull(extractedUpdateUserId2);
        Assert.notNull(extractedUpdateDt2);
    }

    /**
     * Tests Read and Update operation for Type Zero Entity.
     */

    @Test
    void UpdateOperationForTypeZeroEntity() {

        System.out.println("Inside UpdateOperationForTypeZeroEntity method");

        long uniqueTranIdBeforeUpdate = 0;
        long uniqueTranIdAfterUpdate = 0;


        EntityManager entityManager = testEntityManager.getEntityManager();

//      Create two instances of Type Zero Entity
        typeZero1 = new TypeZeroEntity();
        typeZero2 = new TypeZeroEntity();

        LocalDate aDate = LocalDate.now();

        typeZero1.setName("Rahul");
        typeZero1.setAge(32);
        typeZero1.setActive(true);
        typeZero1.setEffBeginDt(aDate);

        typeZeroEntityRepository.save(typeZero1);

        typeZero2.setName("Rathore");
        typeZero2.setAge(32);
        typeZero2.setActive(true);
        typeZero2.setEffBeginDt(aDate);

        typeZeroEntityRepository.save(typeZero2);

        System.out.println("typeZero1: "+ typeZero1);
        System.out.println("typeZero1: "+ typeZero2);

//      Count the no. of records in Database
        BigInteger rowCnt= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE").getSingleResult();
        System.out.println("Count of records in Database: " + rowCnt);

        List<TypeZeroEntity> aTypeZero1Extract1 = typeZeroEntityRepository.findByName("Rahul");

        for (TypeZeroEntity type : aTypeZero1Extract1) {
            System.out.println("aTypeZero1Extract1.getId(): " + type.getId());
            System.out.println("aTypeZero1Extract1.getEffBeginDt(): " + type.getEffBeginDt());
            System.out.println("aTypeZero1Extract1.getName(): " + type.getName());
            System.out.println("aTypeZero1Extract1.getAge(): " + type.getAge());
            System.out.println("aTypeZero1Extract1.isActive(): " + type.isActive());
            System.out.println("aTypeZero1Extract1.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeZero1Extract1.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeZero1Extract1.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeZero1Extract1.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeZero1Extract1.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeZero1Extract1.getArchiveDt(): " + type.getArchiveDt());
            uniqueTranIdBeforeUpdate = (long) type.getUniqueTransId();
            System.out.println("uniqueTranIdBeforeUpdate: " + uniqueTranIdBeforeUpdate);
        }

        for (TypeZeroEntity type : aTypeZero1Extract1){
            type.setAge(22);
            typeZeroEntityRepository.save(type);
            System.out.println("Age property is updated.");
        }

//      Count the no. of records in Database post Update
        BigInteger rowCntPostUpdate = (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE").getSingleResult();
        System.out.println("Count of records in Database Post Update: " + rowCntPostUpdate);

        List<TypeZeroEntity> aTypeZero1Extract1PostUpdate = typeZeroEntityRepository.findByName("Rahul");

        for (TypeZeroEntity type : aTypeZero1Extract1PostUpdate) {
            System.out.println("aTypeZero1Extract1PostUpdate.getId(): " + type.getId());
            System.out.println("aTypeZero1Extract1PostUpdate.getEffBeginDt(): " + type.getEffBeginDt());
            System.out.println("aTypeZero1Extract1PostUpdate.getName(): " + type.getName());
            System.out.println("aTypeZero1Extract1PostUpdate.getAge(): " + type.getAge());
            System.out.println("aTypeZero1Extract1PostUpdate.isActive(): " + type.isActive());
            System.out.println("aTypeZero1Extract1PostUpdate.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeZero1Extract1PostUpdate.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeZero1Extract1PostUpdate.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeZero1Extract1PostUpdate.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeZero1Extract1PostUpdate.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeZero1Extract1PostUpdate.getArchiveDt(): " + type.getArchiveDt());
            uniqueTranIdAfterUpdate = (long)type.getUniqueTransId();
            System.out.println("uniqueTranIdAfterUpdate: " + uniqueTranIdAfterUpdate);
            Assert.isTrue(22 == type.getAge());
            Assert.notNull(type.getCreateUserId());
            Assert.notNull(type.getCreateDt());
        }

        Assert.isTrue(uniqueTranIdAfterUpdate > uniqueTranIdBeforeUpdate);

    }

    /**
     * Tests Delete operation for Type Zero Entity.
     */

    @Test
    void DeleteOperationForTypeZeroEntity() {

        System.out.println("Inside DeleteOperationForTypeZeroEntity method");

        EntityManager entityManager = testEntityManager.getEntityManager();

//      Create two instances of Type Zero Entity
        typeZero1 = new TypeZeroEntity();
        typeZero2 = new TypeZeroEntity();

        LocalDate aDate = LocalDate.now();

        typeZero1.setName("Rahul");
        typeZero1.setAge(32);
        typeZero1.setActive(true);
        typeZero1.setEffBeginDt(aDate);

        typeZeroEntityRepository.save(typeZero1);

        typeZero2.setName("Rathore");
        typeZero2.setAge(34);
        typeZero2.setActive(true);
        typeZero2.setEffBeginDt(aDate);

        typeZeroEntityRepository.save(typeZero2);

        System.out.println("typeZero1: "+ typeZero1);
        System.out.println("typeZero1: "+ typeZero2);

//      Count the no. of records in Database
        BigInteger rowCnt= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE").getSingleResult();
        System.out.println("Count of records in Database: " + rowCnt);

        List<TypeZeroEntity> aTypeZero1Extract1 = typeZeroEntityRepository.findByName("Rahul");

        for (TypeZeroEntity type : aTypeZero1Extract1) {
            System.out.println("aTypeZero1Extract1.getId(): " + type.getId());
            System.out.println("aTypeZero1Extract1.getEffBeginDt(): " + type.getEffBeginDt());
            System.out.println("aTypeZero1Extract1.getName(): " + type.getName());
            System.out.println("aTypeZero1Extract1.getAge(): " + type.getAge());
            System.out.println("aTypeZero1Extract1.isActive(): " + type.isActive());
            System.out.println("aTypeZero1Extract1.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeZero1Extract1.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeZero1Extract1.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeZero1Extract1.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeZero1Extract1.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeZero1Extract1.getArchiveDt(): " + type.getArchiveDt());
        }

        List<TypeZeroEntity> aTypeZero1Extract2 = typeZeroEntityRepository.findByName("Rathore");

        for (TypeZeroEntity type : aTypeZero1Extract2) {
            System.out.println("aTypeZero1Extract2.getId(): " + type.getId());
            System.out.println("aTypeZero1Extract2.getEffBeginDt(): " + type.getEffBeginDt());
            System.out.println("aTypeZero1Extract2.getName(): " + type.getName());
            System.out.println("aTypeZero1Extract2.getAge(): " + type.getAge());
            System.out.println("aTypeZero1Extract2.isActive(): " + type.isActive());
            System.out.println("aTypeZero1Extract2.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeZero1Extract2.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeZero1Extract2.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeZero1Extract2.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeZero1Extract2.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeZero1Extract2.getArchiveDt(): " + type.getArchiveDt());
        }

//      Delete the record from Database
        for (TypeZeroEntity type : aTypeZero1Extract1) {
            typeZeroEntityRepository.delete(type);
        }
        //      Count the no. of records in Database
        BigInteger rowCntPostExtract = (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE").getSingleResult();
        System.out.println("Count of records in Database post Extract: " + rowCntPostExtract);

        List<TypeZeroEntity> aTypeZero1Extract1PostExtract = typeZeroEntityRepository.findByName("Rahul");

        for (TypeZeroEntity type : aTypeZero1Extract1PostExtract) {
            Assert.isNull(type);
        }
    }

}
