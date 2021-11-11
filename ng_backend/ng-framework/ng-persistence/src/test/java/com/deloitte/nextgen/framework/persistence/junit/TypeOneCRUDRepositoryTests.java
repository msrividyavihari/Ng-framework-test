package com.deloitte.nextgen.framework.persistence.junit;

import com.deloitte.nextgen.framework.persistence.repository.TypeOneEntityRepository;
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
 * The class <code> TypeOneCRUDRepositoryTests </code> is used
 * to test CRUD operations for Type One Entity type class.
 *
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrudRepository.class, TypeOneEntityRepository.class}, locations = "classpath:config/namespace-application-context.xml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TypeOneCRUDRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TypeOneEntityRepository typeOneEntityRepository;

    private TypeOneEntity typeOne1;
    private TypeOneEntity typeOne2;

    /**
     * Tests Create operation for Type One Entity.
     */

    @Test
    void CreateOperationForTypeOneEntity() {

        System.out.println("Inside CreateOperationForTypeOneEntity method");

        String extractedTitle1 = null;
        String extractedTitle2 = null;

        String extractedCreateUserId1 = null;
        String extractedUpdateUserId1 = null;
        LocalDateTime extractedCreateDt1 = null;
        LocalDateTime extractedUpdateDt1 = null;

        String extractedCreateUserId2 = null;
        String extractedUpdateUserId2 = null;
        LocalDateTime extractedCreateDt2 = null;
        LocalDateTime extractedUpdateDt2 = null;

        EntityManager entityManager = testEntityManager.getEntityManager();

//      Create two instances of Type One Entity
        typeOne1 = new TypeOneEntity();
        typeOne2 = new TypeOneEntity();

        typeOne1.setTitle("Rahul");
        typeOne1.setTesting("Testing One");
        typeOne1.setIndivId(1234567);
        typeOne1.setActive(true);
        typeOne1.setDailyIncome(5.50);
        typeOne1.setAvgMonthlyIncome((float) 5.2);
        typeOne1.setMiddleInitial('N');
        typeOne1.setSiblings((short) 2);

        typeOneEntityRepository.save(typeOne1);

        typeOne2.setTitle("Rathore");
        typeOne2.setTesting("Testing Two");
        typeOne2.setIndivId(7654321);
        typeOne2.setActive(true);
        typeOne2.setDailyIncome(5.50);
        typeOne2.setAvgMonthlyIncome((float) 5.2);
        typeOne2.setMiddleInitial('N');
        typeOne2.setSiblings((short) 2);

        typeOneEntityRepository.save(typeOne2);

        System.out.println("typeOne1: "+ typeOne1);
        System.out.println("typeOne2: "+ typeOne2);

//      Count the no. of records in Database
        BigInteger rowCnt= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE_ONE").getSingleResult();
        System.out.println("Count of records in Database: " + rowCnt);

        List<TypeOneEntity> aTypeOne1Extract1 = typeOneEntityRepository.findByTitle("Rahul");

        for (TypeOneEntity type : aTypeOne1Extract1) {
            extractedTitle1 = type.getTitle();
            extractedCreateUserId1 = type.getCreateUserId();
            extractedUpdateUserId1 = type.getUpdateUserId();
            extractedCreateDt1 = type.getCreateDt();
            extractedUpdateDt1 = type.getUpdateDt();
            System.out.println("aTypeOne1Extract1.getId(): " + type.getId());
            System.out.println("aTypeOne1Extract1.getSeqNum(): " + type.getSeqNum());
            System.out.println("aTypeOne1Extract1.getTitle(): " + type.getTitle());
            System.out.println("aTypeOne1Extract1.getTesting(): " + type.getTesting());
            System.out.println("aTypeOne1Extract1.getIndivId(): " + type.getIndivId());
            System.out.println("aTypeOne1Extract1.isActive(): " + type.isActive());
            System.out.println("aTypeOne1Extract1.getDailyIncome(): " + type.getDailyIncome());
            System.out.println("aTypeOne1Extract1.getAvgMonthlyIncome(): " + type.getAvgMonthlyIncome());
            System.out.println("aTypeOne1Extract1.getMiddleInitial(): " + type.getMiddleInitial());
            System.out.println("aTypeOne1Extract1.getSiblings(): " + type.getSiblings());
            System.out.println("aTypeOne1Extract1.getHistorySeq(): " + type.getHistorySeq());
            System.out.println("aTypeOne1Extract1.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeOne1Extract1.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeOne1Extract1.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeOne1Extract1.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeOne1Extract1.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeOne1Extract1.getArchiveDt(): " + type.getArchiveDt());
        }

        List<TypeOneEntity> aTypeOne1Extract2 = typeOneEntityRepository.findByTitle("Rathore");

        for (TypeOneEntity type : aTypeOne1Extract2) {
            extractedTitle2 = type.getTitle();
            extractedCreateUserId2 = type.getCreateUserId();
            extractedUpdateUserId2 = type.getUpdateUserId();
            extractedCreateDt2 = type.getCreateDt();
            extractedUpdateDt2 = type.getUpdateDt();
            System.out.println("aTypeOne1Extract2.getId(): " + type.getId());
            System.out.println("aTypeOne1Extract2.getSeqNum(): " + type.getSeqNum());
            System.out.println("aTypeOne1Extract2.getTitle(): " + type.getTitle());
            System.out.println("aTypeOne1Extract2.getTesting(): " + type.getTesting());
            System.out.println("aTypeOne1Extract2.getIndivId(): " + type.getIndivId());
            System.out.println("aTypeOne1Extract2.isActive(): " + type.isActive());
            System.out.println("aTypeOne1Extract2.getDailyIncome(): " + type.getDailyIncome());
            System.out.println("aTypeOne1Extract2.getAvgMonthlyIncome(): " + type.getAvgMonthlyIncome());
            System.out.println("aTypeOne1Extract2.getMiddleInitial(): " + type.getMiddleInitial());
            System.out.println("aTypeOne1Extract2.getSiblings(): " + type.getSiblings());
            System.out.println("aTypeOne1Extract2.getHistorySeq(): " + type.getHistorySeq());
            System.out.println("aTypeOne1Extract2.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeOne1Extract2.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeOne1Extract2.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeOne1Extract2.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeOne1Extract2.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeOne1Extract2.getArchiveDt(): " + type.getArchiveDt());
        }

        Assert.isTrue("Rahul".equals(extractedTitle1));
        Assert.isTrue("Rathore".equals(extractedTitle2));

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
     * Tests Read and Update operation for Type One Entity.
     */

    @Test
    void UpdateOperationForTypeOneEntity() {

        System.out.println("Inside UpdateOperationForTypeOneEntity method");

        long uniqueTranIdBeforeUpdate = 0;
        long uniqueTranIdAfterUpdate = 0;
        long histSeqBeforeUpdate = 0;
        long histSeqAfterUpdate = 0;

        EntityManager entityManager = testEntityManager.getEntityManager();

//      Create two instances of Type One Entity
        typeOne1 = new TypeOneEntity();
        typeOne2 = new TypeOneEntity();

        typeOne1.setTitle("Rahul");
        typeOne1.setTesting("Testing One");
        typeOne1.setIndivId(1234567);
        typeOne1.setActive(true);

        typeOneEntityRepository.save(typeOne1);

        typeOne2.setTitle("Rathore");
        typeOne2.setTesting("Testing Two");
        typeOne2.setIndivId(7654321);
        typeOne2.setActive(true);

        typeOneEntityRepository.save(typeOne2);

        System.out.println("typeOne1: "+ typeOne1);
        System.out.println("typeOne2: "+ typeOne2);

//      Count the no. of records in Database
        BigInteger rowCnt= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE_ONE").getSingleResult();
        System.out.println("Count of records in Database: " + rowCnt);

        List<TypeOneEntity> aTypeOne1Extract1 = typeOneEntityRepository.findByTitle("Rahul");

        for (TypeOneEntity type : aTypeOne1Extract1) {
            System.out.println("aTypeOne1Extract1.getId(): " + type.getId());
            System.out.println("aTypeOne1Extract1.getSeqNum(): " + type.getSeqNum());
            System.out.println("aTypeOne1Extract1.getTitle(): " + type.getTitle());
            System.out.println("aTypeOne1Extract1.getTesting(): " + type.getTesting());
            System.out.println("aTypeOne1Extract1.getIndivId(): " + type.getIndivId());
            System.out.println("aTypeOne1Extract1.isActive(): " + type.isActive());
            System.out.println("aTypeOne1Extract1.getHistorySeq(): " + type.getHistorySeq());
            System.out.println("aTypeOne1Extract1.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeOne1Extract1.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeOne1Extract1.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeOne1Extract1.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeOne1Extract1.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeOne1Extract1.getArchiveDt(): " + type.getArchiveDt());
            uniqueTranIdBeforeUpdate = (long) type.getUniqueTransId();
            histSeqBeforeUpdate = (long) type.getHistorySeq();
            System.out.println("uniqueTranIdBeforeUpdate: " + uniqueTranIdBeforeUpdate);
            System.out.println("histSeqBeforeUpdate: " + histSeqBeforeUpdate);
        }

        for (TypeOneEntity type : aTypeOne1Extract1) {
            type.setTesting("Update Testing");
            typeOneEntityRepository.save(type);
            System.out.println("Testing property is updated.");
        }

//      Count the no. of records in Database Post Update
        BigInteger rowCntPostUpdate = (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE_ONE").getSingleResult();
        System.out.println("Count of records in Database Post Update: " + rowCntPostUpdate);

//      Count the no. of records in Database Post Update
//        BigInteger rowCntPostUpdateA= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE_ONE_A").getSingleResult();
//        System.out.println("Count of records in Database A Post Update: " + rowCntPostUpdateA);

        List<TypeOneEntity> aTypeOne1Extract1PostUpdate = typeOneEntityRepository.findByTitle("Rahul");

        for (TypeOneEntity type : aTypeOne1Extract1PostUpdate) {
            System.out.println("aTypeOne1Extract1PostUpdate.getId(): " + type.getId());
            System.out.println("aTypeOne1Extract1PostUpdate.getSeqNum(): " + type.getSeqNum());
            System.out.println("aTypeOne1Extract1PostUpdate.getTitle(): " + type.getTitle());
            System.out.println("aTypeOne1Extract1PostUpdate.getTesting(): " + type.getTesting());
            System.out.println("aTypeOne1Extract1PostUpdate.getIndivId(): " + type.getIndivId());
            System.out.println("aTypeOne1Extract1PostUpdate.isActive(): " + type.isActive());
            System.out.println("aTypeOne1Extract1PostUpdate.getHistorySeq(): " + type.getHistorySeq());
            System.out.println("aTypeOne1Extract1PostUpdate.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeOne1Extract1PostUpdate.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeOne1Extract1PostUpdate.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeOne1Extract1PostUpdate.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeOne1Extract1PostUpdate.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeOne1Extract1PostUpdate.getArchiveDt(): " + type.getArchiveDt());
            uniqueTranIdAfterUpdate = (long) type.getUniqueTransId();
            histSeqAfterUpdate = (long) type.getHistorySeq();
            System.out.println("uniqueTranIdBeforeUpdate: " + uniqueTranIdAfterUpdate);
            System.out.println("histSeqBeforeUpdate: " + histSeqAfterUpdate);
            Assert.isTrue("Update Testing".equals(type.getTesting()));
        }

        Assert.isTrue(uniqueTranIdAfterUpdate > uniqueTranIdBeforeUpdate);
        Assert.isTrue(histSeqAfterUpdate > histSeqBeforeUpdate);
    }

    /**
     * Tests Delete operation for Type One Entity.
     */

    @Test
    void DeleteOperationForTypeOneEntity() {

        System.out.println("Inside DeleteOperationForTypeOneEntity method");

        EntityManager entityManager = testEntityManager.getEntityManager();

//      Create two instances of Type One Entity
        typeOne1 = new TypeOneEntity();
        typeOne2 = new TypeOneEntity();

        LocalDate aDate = LocalDate.now();

        typeOne1.setTitle("Rahul");
        typeOne1.setTesting("Testing One");
        typeOne1.setIndivId(1234567);
        typeOne1.setActive(true);

        typeOneEntityRepository.save(typeOne1);

        typeOne2.setTitle("Rathore");
        typeOne2.setTesting("Testing Two");
        typeOne2.setIndivId(7654321);
        typeOne2.setActive(true);

        typeOneEntityRepository.save(typeOne2);

        System.out.println("typeOne1: "+ typeOne1);
        System.out.println("typeOne2: "+ typeOne2);

//      Count the no. of records in Database
        BigInteger rowCnt= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE_ONE").getSingleResult();
        System.out.println("Count of records in Database: " + rowCnt);

        List<TypeOneEntity> aTypeOne1Extract1 = typeOneEntityRepository.findByTitle("Rahul");

        for (TypeOneEntity type : aTypeOne1Extract1) {
            System.out.println("aTypeOne1Extract1.getId(): " + type.getId());
            System.out.println("aTypeOne1Extract1.getSeqNum(): " + type.getSeqNum());
            System.out.println("aTypeOne1Extract1.getTitle(): " + type.getTitle());
            System.out.println("aTypeOne1Extract1.getTesting(): " + type.getTesting());
            System.out.println("aTypeOne1Extract1.getIndivId(): " + type.getIndivId());
            System.out.println("aTypeOne1Extract1.isActive(): " + type.isActive());
            System.out.println("aTypeOne1Extract1.getHistorySeq(): " + type.getHistorySeq());
            System.out.println("aTypeOne1Extract1.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeOne1Extract1.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeOne1Extract1.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeOne1Extract1.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeOne1Extract1.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeOne1Extract1.getArchiveDt(): " + type.getArchiveDt());
        }

        List<TypeOneEntity> aTypeOne1Extract2 = typeOneEntityRepository.findByTitle("Rathore");

        for (TypeOneEntity type : aTypeOne1Extract2) {
            System.out.println("aTypeOne1Extract2.getId(): " + type.getId());
            System.out.println("aTypeOne1Extract2.getSeqNum(): " + type.getSeqNum());
            System.out.println("aTypeOne1Extract2.getTitle(): " + type.getTitle());
            System.out.println("aTypeOne1Extract2.getTesting(): " + type.getTesting());
            System.out.println("aTypeOne1Extract2.getIndivId(): " + type.getIndivId());
            System.out.println("aTypeOne1Extract2.isActive(): " + type.isActive());
            System.out.println("aTypeOne1Extract2.getHistorySeq(): " + type.getHistorySeq());
            System.out.println("aTypeOne1Extract2.getCreateUserId(): " + type.getCreateUserId());
            System.out.println("aTypeOne1Extract2.getCreateDt(): " + type.getCreateDt());
            System.out.println("aTypeOne1Extract2.getUpdateUserId(): " + type.getUpdateUserId());
            System.out.println("aTypeOne1Extract2.getUpdateDt(): " + type.getUpdateDt());
            System.out.println("aTypeOne1Extract2.getUniqueTransId(): " + type.getUniqueTransId());
            System.out.println("aTypeOne1Extract2.getArchiveDt(): " + type.getArchiveDt());
        }

        for (TypeOneEntity type : aTypeOne1Extract1) {
            typeOneEntityRepository.delete(type);
            System.out.println("Delete the record from database");
        }

//      Count the no. of records in Database Post Delete
        BigInteger rowCntPostDelete= (BigInteger)entityManager.createNativeQuery("SELECT count(1) FROM SAMPLE_ONE").getSingleResult();
        System.out.println("Count of records in Database Post Delete: " + rowCntPostDelete);

        List<TypeOneEntity> aTypeOne1Extract1PostDelete = typeOneEntityRepository.findByTitle("Rahul");

        for (TypeOneEntity type : aTypeOne1Extract1PostDelete) {
            Assert.isNull(type);
        }
    }
}
