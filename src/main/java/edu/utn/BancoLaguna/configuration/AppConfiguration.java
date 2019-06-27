package edu.utn.BancoLaguna.configuration;


import edu.utn.BancoLaguna.model.Account;
import edu.utn.BancoLaguna.model.Transfer;
import edu.utn.BancoLaguna.model.TransferDTO;
import edu.utn.BancoLaguna.model.TransferHistoryDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean("ModelMapperTransfer")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Transfer, TransferHistoryDTO>() {
            @Override
            protected void configure() {

                map().setCbuOrigin(source.getOrigin().getCbu());
                map().setCbuDestination(source.getDestination().getCbu());
                map().setSum(source.getSum());
                map().setType(source.getOrigin().getType());
            }
        });

        modelMapper.addMappings(new PropertyMap<TransferDTO, Transfer>() {
            @Override
            protected void configure() {
                map().setSum(source.getSum());
            }
        });
        return modelMapper;
    }
}
