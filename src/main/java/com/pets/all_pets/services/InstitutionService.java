package com.pets.all_pets.services;
import com.pets.all_pets.DTO.InstitutionDTO;
import com.pets.all_pets.Mapper.ProjectMAPPER;
import com.pets.all_pets.models.InstitutionModel;
import com.pets.all_pets.repository.InstitutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InstitutionService {


    private static final Logger LOG = LoggerFactory.getLogger(InstitutionService.class);

    @Autowired
    InstitutionRepository institutionRepository;

    public void createInstitution(InstitutionDTO InstitutionDTO) {

        if (!isEmailAlreadyRegistered(InstitutionDTO.getEmail())) {
            InstitutionModel institutionModel = new InstitutionModel();
            institutionModel.setInstitutionName(InstitutionDTO.getInstitutionName());
            institutionModel.setResponsibleName(InstitutionDTO.getResponsibleName());
            institutionModel.setResponsiblePhone(InstitutionDTO.getResponsiblePhone());
            institutionModel.setCnpj(InstitutionDTO.getCnpj());
            institutionModel.setEmail(InstitutionDTO.getEmail());
            institutionModel.setAddress(InstitutionDTO.getAddress());
            institutionModel.setIsPaying(InstitutionDTO.getIsPaying());
            institutionModel.setPassword(InstitutionDTO.getPassword());;
            institutionRepository.save(institutionModel);
            InstitutionDTO.setIdInstitution(institutionModel.getIdInstitution());

        } else {
            LOG.error("Email already registered");
            throw new RuntimeException("Email already registered");
            }
        }

    public InstitutionDTO getInstitutionByIdl(Integer id, InstitutionDTO institutionDTO) {

        Optional<InstitutionModel> institutionModel = institutionRepository.findById(id);

        if (institutionModel.isPresent()) {
            return ProjectMAPPER.parseObject(institutionModel.get(), InstitutionDTO.class);
        } else {
            throw new RuntimeException("Institution not found");
        }
    }



    public InstitutionDTO deleteInstitutionById(Integer id, InstitutionDTO institutionDTO) {
            Optional<InstitutionModel> institutionModel = institutionRepository.findById(id);
            if (institutionModel.isPresent()) {
                institutionRepository.delete(institutionModel.get());
                LOG.info("Institution deleted successfully");
                return ProjectMAPPER.parseObject(institutionModel.get(),InstitutionDTO.class);
            } else {
                throw new RuntimeException("Institution not found for deletion");
            }
        }

        public InstitutionDTO updateInstitution(Integer id, InstitutionDTO institution) {
            Optional<InstitutionModel> institutionModel = institutionRepository.findById(id);
            if (institutionModel.isPresent()) {
                InstitutionModel updatedInstitution = institutionModel.get();
                updatedInstitution.setInstitutionName(institution.getInstitutionName());
                updatedInstitution.setResponsibleName(institution.getResponsibleName());
                updatedInstitution.setResponsiblePhone(institution.getResponsiblePhone());
                updatedInstitution.setCnpj(institution.getCnpj());
                updatedInstitution.setEmail(institution.getEmail());
                updatedInstitution.setAddress(institution.getAddress());
                updatedInstitution.setIsPaying(institution.getIsPaying());
                updatedInstitution.setToken(institution.getToken());
                updatedInstitution.setPassword(institution.getPassword());
                institutionRepository.save(updatedInstitution);
                return ProjectMAPPER.parseObject(updatedInstitution,InstitutionDTO.class);
            } else {
                throw new RuntimeException("Institution not found for update");
            }
        }


        boolean isEmailAlreadyRegistered(String email) {
            return institutionRepository.existsByEmail(email);
        }

    }









