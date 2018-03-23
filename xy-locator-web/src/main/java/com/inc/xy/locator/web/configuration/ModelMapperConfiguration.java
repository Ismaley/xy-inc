package com.inc.xy.locator.web.configuration;

import com.inc.xy.locator.web.converter.InterestPointTOToInterestPoint;
import com.inc.xy.locator.web.converter.InterestPointToInterestPointTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(false)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        modelMapper.addMappings(new InterestPointTOToInterestPoint());
        modelMapper.addMappings(new InterestPointToInterestPointTO());
        return modelMapper;
    }
}
