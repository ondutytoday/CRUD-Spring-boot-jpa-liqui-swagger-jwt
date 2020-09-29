package org.vasileva.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.Supply;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuppliersDto {
    private String supplierName;

    private String supplierAddress;

    private Integer inn;

    private String phoneNumber;

    private String email;

    private String information;

    private Set<Supply> supplies;
}
