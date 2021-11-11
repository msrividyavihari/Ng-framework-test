package com.deloitte.nextgen.framework.commons.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author nishmehta on 09/11/2020 11:46 AM
 * @since v0.0.1-SNAPSHOT
 */
public interface RequestResponseMapper<R, T, E> {

    E toEntity(R request);

    T toResponse(E entity);

    List<E> toEntity(List<R> request);

    List<T> toResponse(List<E> entity);

    void updateEntity(R request, @MappingTarget E entity);

}
