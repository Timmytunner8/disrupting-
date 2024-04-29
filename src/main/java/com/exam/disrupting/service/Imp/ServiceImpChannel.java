package com.exam.disrupting.service.Imp;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exam.disrupting.model.Channel;
import com.exam.disrupting.repositories.ChannelRepository;
import com.exam.disrupting.service.ServiceChannel;

@Service
public class ServiceImpChannel implements ServiceChannel{

    @Autowired
    ChannelRepository channelRepository;

    @Override
    public Channel create(Channel channel) {
        try {    
            List<Channel> exChannels = channelRepository.findByScheduledAndSpecifyChannel(channel.getScheduled(), channel.getSpecifyChannel());
            exChannels.stream().forEach(e -> {
                if (channel.getScheduled().equals(e.getScheduled())
                && channel.getSpecifyChannel().equals(e.getSpecifyChannel())) {
                    throw new RuntimeException("No se pueden duplicados");
                }
            });

            if (getChannels().size() > 4) {
                return null;
            }
            return channelRepository.save(channel);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        channelRepository.findById(id).ifPresent(channelRepository::delete);
    }

    @Override
    public Channel update(Integer id, Channel newChannel) {
        try {
            List<Channel> exChannels = channelRepository.findByScheduledAndSpecifyChannel(newChannel.getScheduled(), newChannel.getSpecifyChannel());
            exChannels.stream().forEach(e -> {
                if (newChannel.getScheduled().equals(e.getScheduled())
                && newChannel.getSpecifyChannel().equals(e.getSpecifyChannel())) {
                    throw new RuntimeException("No se puede modificar");
                }
            });
            Channel channel = getChannelById(id);
            channel.setName(newChannel.getName());
            channel.setScheduled(newChannel.getScheduled());
            channel.setDuration(newChannel.getDuration());
            channel.setSpecifyChannel(newChannel.getSpecifyChannel());
            return channelRepository.save(channel);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Channel> getChannels() {
        return (List<Channel>) channelRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Channel getChannelById(Integer id) {
        return channelRepository.findById(id).orElse(null);
    }

}
