package com.deloitte.nextgen.framework.commons.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author nishmehta
 * @since v0.0.1-SNAPSHOT
 */
public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dto);

    List<D> toDto(List<E> entity);

    void updateEntity(D dto, @MappingTarget E entity);

}
