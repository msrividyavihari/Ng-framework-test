package com.deloitte.nextgen.dc.common.mapstruct.mappers;

import com.deloitte.nextgen.dc.common.dto.IndividualNameDto;
import com.deloitte.nextgen.dc.entities.DcAuthRep;
import com.deloitte.nextgen.dc.entities.DcIndv;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class IndividualNameDtoMapper {

    @Mapping(target = "suffixName", source = "individual.sufxName")
    public abstract IndividualNameDto map(DcIndv individual);

    /**
     * There were many attributes in DcIndv which can't be mapped .. rather than changing
     * the reporting policy to WARN/IGNORE.. simply manually populating the DcIndv name attributes
     * from IndividualNameDto
     */
    public DcIndv map(IndividualNameDto nameDto, @MappingTarget DcIndv individual){
        if(individual == null) individual = new DcIndv();
        if(nameDto == null) return individual;
        individual.setFirstName(nameDto.getFirstName());
        individual.setLastName(nameDto.getLastName());
        individual.setSufxName(nameDto.getSuffixName());
        individual.setMidName(nameDto.getMidName());
        return individual;
    }

    public abstract List<IndividualNameDto> mapIndividuals(List<DcIndv> individuals);

    @Mapping(source = "authRep.authrepFirstName", target = "firstName")
    @Mapping(source = "authRep.authrepMidName", target = "midName")
    @Mapping(source = "authRep.authrepLastName", target = "lastName")
    @Mapping(source = "authRep.authrepSufxName", target = "suffixName")
    public abstract IndividualNameDto map(DcAuthRep authRep);
}
