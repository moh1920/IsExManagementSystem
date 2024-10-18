package org.sayari.isexmanagementsystem.reposetry;

import org.sayari.isexmanagementsystem.entity.Expense;
import org.sayari.isexmanagementsystem.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepo extends JpaRepository<Income,Long> {

    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT sum(i.amount) from Income i")
    Double SumAllAmounts();
    Optional<Income> findFirstByOrderByDateDesc();

}
