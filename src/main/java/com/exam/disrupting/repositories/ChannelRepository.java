package com.exam.disrupting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.disrupting.model.Channel;


@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {
    List<Channel> findByScheduledAndSpecifyChannel(String scheduled, Integer specifyChannel);
}
