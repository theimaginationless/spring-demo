package com.example.springdemo.repository;

import com.example.springdemo.DAO.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {
    Message findByMessageId(long messageId);

    @Query(value = "from Message m where m.created between :startDate and :endDate and m.mqName = :mqName and m.messageId = :messageId")
    List<Message> findByAll(@Param("messageId") long messageId,
                            @Param("startDate") Date startDate,
                            @Param("endDate") Date endDate,
                            @Param("mqName") String mqName);

    @Query(value = "from Message m where m.created between :startDate and :endDate and m.mqName = :mqName")
    List<Message> findByAll(@Param("startDate") Date startDate,
                            @Param("endDate") Date endDate,
                            @Param("mqName") String mqName);
}
