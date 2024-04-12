package com.beautyclan.eventease.dtos;

import com.beautyclan.eventease.data.models.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class GetUserEventsResponse {
    private List<Event> events;
}
