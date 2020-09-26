package org.vasileva.crud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.StaffDto;
import org.vasileva.crud.entity.Staff;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface StaffMapper {
    StaffDto toStaffDto(Staff staff);

    Staff toStaff (StaffDto staffDto);

    List<StaffDto> toListStaffDto(List<Staff> staff);

}
