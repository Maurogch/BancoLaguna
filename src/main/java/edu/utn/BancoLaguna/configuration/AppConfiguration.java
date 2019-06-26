package edu.utn.BancoLaguna.configuration;


import edu.utn.BancoLaguna.model.Transfer;
import edu.utn.BancoLaguna.model.TransferDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Configuration
public class AppConfiguration {
    @Bean("ModelMapperTransfer")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Transfer, TransferDTO>() {
            @Override
            protected void configure() {

                map().setCbuOrigin(source.getOrigin().getCbu());
                map().setCbuDestination(source.getDestination().getCbu());
                map().setSum(source.getSum());
                map().setType(source.getOrigin().getType());
            }
        });
        return modelMapper;
    }
}
