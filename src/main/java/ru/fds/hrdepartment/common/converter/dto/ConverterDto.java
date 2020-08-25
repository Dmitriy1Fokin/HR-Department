package ru.fds.hrdepartment.common.converter.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ConverterDto<E, D> {

    E toEntity(D dto);
    D toDto(E entity);

    default Collection<E> toEntity(Collection<D> dtoList){
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default Collection<D> toDto(Collection<E> entityList){
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
