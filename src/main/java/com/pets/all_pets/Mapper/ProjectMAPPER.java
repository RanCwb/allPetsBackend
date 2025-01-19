package com.pets.all_pets.Mapper;

import com.pets.all_pets.DTO.CustomerDTO;
import com.pets.all_pets.models.CustomerModel;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProjectMAPPER {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) {return modelMapper.map(origin, destination);}

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();
        for (O o: origin) {
            destinationObjects.add(modelMapper.map(o, destination));
        }
        return destinationObjects;
    }

    public static CustomerDTO parseListObjectsDTO(CustomerModel customer, Class<CustomerDTO> customerDTOClass) {
        return modelMapper.map(customer, customerDTOClass);
    }
}
