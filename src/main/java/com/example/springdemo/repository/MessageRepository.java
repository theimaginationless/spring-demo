package com.example.springdemo.repository;

import com.example.springdemo.dao.MessageDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface MessageRepository extends CrudRepository<MessageDAO, Long> {
    MessageDAO findByMessageId(long messageId);

    @Query(value = "from MessageDAO m where m.created between :startDate and :endDate and m.mqName = :mqName and m.messageId = :messageId")
    MessageDAO findBy(@Param("messageId") long messageId,
                            @Param("startDate") Date startDate,
                            @Param("endDate") Date endDate,
                            @Param("mqName") String mqName);

    @Query(value = "from MessageDAO m where m.created between :startDate and :endDate and m.mqName = :mqName")
    List<MessageDAO> findAllBy(@Param("startDate") Date startDate,
                            @Param("endDate") Date endDate,
                            @Param("mqName") String mqName);
}
