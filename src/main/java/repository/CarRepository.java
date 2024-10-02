package repository;


import DTOs.AdvancedSearchDTO;
import DTOs.CarAvailabilityDTO;
import models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<CarAvailabilityDTO> findCarAvailabilityByDate(LocalDate date);

    List<AdvancedSearchDTO> advancedSearch(String searchTerm);
}
