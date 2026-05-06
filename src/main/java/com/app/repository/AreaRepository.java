package com.app.repository;

import com.app.entity.evaluation.Agent;
import com.app.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
  //  @Query("SELECT ag FROM Area a JOIN a.agent ag" +
    //"where ag.id=:id")
    //List<Agent> searchAgent(@Param("id") long id);

    List<Area> findByPinCode(String pinCode);
}