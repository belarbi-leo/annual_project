package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.MaterielsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.MaterielsDTORead;
import com.pa2aresgi.pa2a.modele.Materiels;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-27T21:40:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class MaterielsMapperImpl implements MaterielsMapper {

    @Override
    public MaterielsDTORead toDtoRead(Materiels materiel) {
        if ( materiel == null ) {
            return null;
        }

        MaterielsDTORead materielsDTORead = new MaterielsDTORead();

        materielsDTORead.setDescriptionMateriel( materiel.getDescriptionMateriel() );
        materielsDTORead.setIdMateriel( materiel.getIdMateriel() );
        materielsDTORead.setNameMateriel( materiel.getNameMateriel() );
        materielsDTORead.setSvc( materiel.getSvc() );

        return materielsDTORead;
    }

    @Override
    public Materiels fromDtoCreate(MaterielsDTOCreate materielDtoCreate) {
        if ( materielDtoCreate == null ) {
            return null;
        }

        Materiels materiels = new Materiels();

        materiels.setDescriptionMateriel( materielDtoCreate.getDescriptionMateriel() );
        materiels.setNameMateriel( materielDtoCreate.getNameMateriel() );
        materiels.setSvc( materielDtoCreate.getSvc() );

        return materiels;
    }
}
