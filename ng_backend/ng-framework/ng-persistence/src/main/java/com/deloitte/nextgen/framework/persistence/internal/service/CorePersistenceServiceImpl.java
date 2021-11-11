package com.deloitte.nextgen.framework.persistence.internal.service;

import com.deloitte.nextgen.framework.persistence.annotations.Auditor;
import com.deloitte.nextgen.framework.persistence.internal.EntityTypeManager;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.AuditorInfo;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.reflection.ReflectionManager;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
@Slf4j
public class CorePersistenceServiceImpl implements CorePersistenceService {

    private boolean isInitiated;

    private transient ReflectionManager reflectionManager;

    private transient MetadataImplementor metadataImplementor;
    private transient EntitiesConfigurations entitiesConfigurator;
    private transient EntityTypeManager entityTypeManager;
    private transient Optional<AuditorInfo> auditorInfo = Optional.empty();

    public CorePersistenceServiceImpl(ServiceRegistryImplementor registry) {

    }

    @Override
    public boolean isInitiated() {
        return isInitiated;
    }

    @Override
    public void initiate(MetadataImplementor metadata, ServiceRegistry serviceRegistry) {

        if (isInitiated) {
            return;
        }

        isInitiated = true;

        this.reflectionManager = metadata.getMetadataBuildingOptions().getReflectionManager();
        this.metadataImplementor = metadata;
        this.entityTypeManager = new EntityTypeManager();
        this.entitiesConfigurator = new EntitiesConfigurations(this);

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forJavaClassPath())
                        .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner())
        );

        Set<Class<?>> auditorSet = reflections.getTypesAnnotatedWith(Auditor.class);
        auditorSet.forEach(auditor -> {
            try {
                auditorInfo = Optional.of((AuditorInfo) auditor.getConstructor().newInstance());
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        });
        if (!auditorInfo.isPresent()) {
            log.error("No class found with @Auditor annotation. Auditor information (createdBy and updatedBy) will be populated with null");
        }
    }

    @Override
    public ReflectionManager getReflectionManager() {
        return reflectionManager;
    }

    @Override
    public MetadataImplementor getMetadata() {
        return metadataImplementor;
    }

    @Override
    public EntityTypeManager getEntityTypeManager() {
        return entityTypeManager;
    }

    @Override
    public EntitiesConfigurations getEntityConfigurations() {
        return entitiesConfigurator;
    }

    @Override
    public String getAuditorName() {
        String value = null;
        if(auditorInfo.isPresent()) {
            value = auditorInfo.get().getAuditor().orElse(null);
        }
        return value;
    }
}
