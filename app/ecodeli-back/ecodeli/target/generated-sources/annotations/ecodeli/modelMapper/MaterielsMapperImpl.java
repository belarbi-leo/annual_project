package ecodeli.modelMapper;

import ecodeli.DTO.create.MaterielsDTOCreate;
import ecodeli.DTO.read.MaterielsDTORead;
import ecodeli.modele.Materiels;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-04T00:51:10+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class MaterielsMapperImpl implements MaterielsMapper {

    @Override
    public MaterielsDTORead toDtoRead(Materiels materiel) {
        if ( materiel == null ) {
            return null;
        }

        MaterielsDTORead materielsDTORead = new MaterielsDTORead();

        materielsDTORead.setIdMateriel( materiel.getIdMateriel() );
        materielsDTORead.setSvc( materiel.getSvc() );
        materielsDTORead.setNameMateriel( materiel.getNameMateriel() );
        materielsDTORead.setDescriptionMateriel( materiel.getDescriptionMateriel() );

        return materielsDTORead;
    }

    @Override
    public Materiels fromDtoCreate(MaterielsDTOCreate materielDtoCreate) {
        if ( materielDtoCreate == null ) {
            return null;
        }

        Materiels materiels = new Materiels();

        materiels.setNameMateriel( materielDtoCreate.getNameMateriel() );
        materiels.setDescriptionMateriel( materielDtoCreate.getDescriptionMateriel() );

        return materiels;
    }

    @Override
    public Materiels updateMaterielFromDtoCreate(MaterielsDTOCreate materielDtoCreate, Materiels materiel) {
        if ( materielDtoCreate == null ) {
            return materiel;
        }

        if ( materielDtoCreate.getNameMateriel() != null ) {
            materiel.setNameMateriel( materielDtoCreate.getNameMateriel() );
        }
        if ( materielDtoCreate.getDescriptionMateriel() != null ) {
            materiel.setDescriptionMateriel( materielDtoCreate.getDescriptionMateriel() );
        }

        return materiel;
    }
}
