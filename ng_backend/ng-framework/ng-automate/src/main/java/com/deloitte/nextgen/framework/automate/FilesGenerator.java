package com.deloitte.nextgen.framework.automate;

import com.deloitte.nextgen.framework.automate.spi.EntityTypeFileGenerator;
import com.deloitte.nextgen.framework.automate.spi.Generator;
import com.squareup.javapoet.TypeSpec;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nishmehta
 * @since 06/07/2021 12:00 PM
 */
public class FilesGenerator {

    private Generator repositoryInterface;
    private Generator serviceInterface;
    private Generator serviceImpl;
    private Generator resourceImpl;

    private final List<FileData> typeSpecs = new ArrayList<>();

    public FilesGenerator(EntityTypeFileGenerator entityTypeFileGenerator) {
        repositoryInterface = entityTypeFileGenerator.createRepositoryInterface();
        repositoryInterface.generate();

        serviceInterface = entityTypeFileGenerator.createServiceInterface();
        serviceInterface.generate();

        serviceImpl = entityTypeFileGenerator.createServiceImpl(serviceInterface.type(), repositoryInterface.type());
        serviceImpl.generate();

        resourceImpl = entityTypeFileGenerator.createResourceImpl(serviceInterface.type());
        resourceImpl.generate();
    }

    public List<FileData> collect() {
        typeSpecs.add(new FileData(repositoryInterface.getMetadata().packageName(), repositoryInterface.type()));
        typeSpecs.add(new FileData(serviceInterface.getMetadata().packageName(), serviceInterface.type()));
        typeSpecs.add(new FileData(serviceImpl.getMetadata().packageName(), serviceImpl.type()));
        typeSpecs.add(new FileData(resourceImpl.getMetadata().packageName(), resourceImpl.type()));

        return typeSpecs;
    }

    @Getter
    @AllArgsConstructor
    public class FileData {

        String packageName;

        TypeSpec typeSpec;
    }
}
