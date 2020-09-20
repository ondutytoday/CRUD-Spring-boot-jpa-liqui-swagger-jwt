package org.vasileva.crud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vasileva.crud.entity.Staff;
import org.vasileva.crud.repository.StaffRepository;

import java.util.List;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public Staff getById(Long id) {
        log.info("IN StaffServiceImpl getById {}", id);
        return staffRepository.getOne(id);
    }

    @Override
    public void save(Staff staff) {
        log.info("IN StaffServiceImpl save {}", staff);
        staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        log.info("IN StaffServiceImpl delete {}", id);
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> getAll() {
        log.info("IN StaffServiceImpl getAll");
        return staffRepository.findAll();
    }
}
