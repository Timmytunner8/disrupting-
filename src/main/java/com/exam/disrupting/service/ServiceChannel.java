package com.exam.disrupting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exam.disrupting.model.Channel;

@Service
public interface ServiceChannel {

    public Channel create(Channel channel);
    public Channel getChannelById(Integer id);
    public void delete(Integer id);
    public Channel update(Integer id, Channel channel);
    public List<Channel> getChannels();
}