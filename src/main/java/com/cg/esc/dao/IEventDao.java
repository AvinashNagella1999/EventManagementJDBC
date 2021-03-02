package com.cg.esc.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.esc.exception.EventManagementException;
import com.cg.esc.model.Event;

public interface IEventDao {
	String add(Event event) throws EventManagementException;
	Boolean remove(String id)throws EventManagementException;
	List<Event> getAll()throws EventManagementException;
	void persist()throws EventManagementException;
	List<Event> listAllEvents(String location) throws EventManagementException;
	List<Event> listAllEvents(LocalDate dateScheduled) throws EventManagementException;

}
