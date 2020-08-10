package com.topov.accessorycompatibility.mapper;

import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CompatibilityResultMapper {
    private final ModelMapper mapper;

    @Autowired
    public CompatibilityResultMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void configure() {
        mapper.addMappings(new CompatibilityResultPropertyMap());
    }

    public CompatibilityResultDto toDto(CompatibilityResult result) {
        return mapper.map(result, CompatibilityResultDto.class);
    }

    private static final class CompatibilityResultPropertyMap
        extends PropertyMap<CompatibilityResult, CompatibilityResultDto> {

        @Override
        protected void configure() {
            map().setCompatibilityName(source.getCompatibilityName());
            map().setCompatibilityStatus(source.getCompatibilityStatus());
            map().setEvaluationStatus(source.getEvaluationStatus());
            map().setIncompatibilities(source.getIncompatibilities());
        }
    }
}
