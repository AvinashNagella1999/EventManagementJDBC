package com.cg.esc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.esc.dao.EventDaoJDBCImpl;
import com.cg.esc.dao.IEventDao;
import com.cg.esc.exception.EventManagementException;
import com.cg.esc.model.Event;


public class EventServiceImpl implements IEventService{
	private IEventDao eventDao;

	public IEventDao getDAO(){
		return eventDao;
	}
	public EventServiceImpl() throws EventManagementException { 
		eventDao = new EventDaoJDBCImpl();
	}
	//public boolean isValidId(String id){
		
		/*
		 * First letter must be capital
		 * Followed by three digits
		 */
		//Pattern idPattern = Pattern.compile("[A-Z]\\d{3}");
		//Matcher idMatcher = idPattern.matcher(id);
		
		//return idMatcher.matches();
	//}
	
	public boolean isValidTitle(String title){
		/*
		 * First Letter should be capital
		 * Minimum length is 4 chars
		 * Maximum length is 20 chars.		
		 */
		Pattern titlePattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher titleMatcher = titlePattern.matcher(title);
		
		return titleMatcher.matches();
	}

	public boolean isValidDateScheduled(LocalDate dateScheduled){
		/*
		 * publish date should be greater than or equal to  the current day.
		 */
		LocalDate today = LocalDate.now();
		return today.isBefore(dateScheduled);
	}
	
	public boolean isValidCost(double cost){
		/*
		 * cost is between 1000 and 50000
		 */
		return cost>=1000 && cost<=50000;
	}
	
	public boolean isValidBook(Event event) throws EventManagementException{
		boolean isValid=false;
		
		List<String> errMsgs = new ArrayList<>();
		
		//if(!isValidId(event.getId()))
			//errMsgs.add("Id should start with a capital alphabet followed by 3 digits");
		
		if(!isValidTitle(event.getTitle()))
			errMsgs.add("Title must start with capital and must be in between 4 to 20 chars");
		
		if(!isValidCost(event.getCost()))
			errMsgs.add("Price must be between Rs.1000 and Rs.5000");
		
		if(!isValidDateScheduled(event.getDateScheduled()))
			errMsgs.add("Publish Date cannot be a future date");
		
		
		if(errMsgs.isEmpty())
			isValid=true;
		else
			throw new EventManagementException(errMsgs.toString());
		
		return isValid;
	}
	@Override
	public String add(Event event) throws EventManagementException {
		String id=null;
		if(event!=null /*&& isValidId(id)*/){
			id=eventDao.add(event);
		}
		return id;
	}

	@Override
	public Boolean remove(String id) throws EventManagementException {
		boolean isDone=false;
		if(id!=null /*&& isValidId(id)*/){
			eventDao.remove(id);
			isDone = true;
		} else{
			throw new EventManagementException("bcode should be a capital letter followed by 3 digits");
		}
		return isDone;
	}

	@Override
	public List<Event> getAll() throws EventManagementException {
		return eventDao.getAll();
	}

	@Override
	public void persist() throws EventManagementException {
		eventDao.persist();
	}
	@Override
	public List<Event> listALLEvents(String location) throws EventManagementException {
		return eventDao.getAll();
	}
	@Override
	public List<Event> listALLEvents(LocalDate dateScheduled) throws EventManagementException {
		return eventDao.getAll();
	}
	

}
