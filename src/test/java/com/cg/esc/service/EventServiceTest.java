package com.cg.esc.service;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;

import org.junit.Test;

import com.cg.esc.exception.EventManagementException;
import com.cg.esc.model.Event;

public class EventServiceTest  {
	EventServiceImpl service;
	
	@Test
	@BeforeEach
	void runBeforeAnyTestCase() throws EventManagementException {
		service = new EventServiceImpl();
	}
	
	@AfterEach
	void cleanAfterEachTestCase() {
		service = null;
	}
	
	@Test
	@DisplayName("should add the event")
	void testAddEvent() throws EventManagementException {
        Event event = new Event("B101","Newyear", LocalDate.parse("2021-10-21"),"Goa",6000.0);
        String actual = service.add(event);
        assertEquals("B101", actual);
    }
	@Test
	@DisplayName("should add the event")
	void testAddEvent2() throws EventManagementException {
        Event event = new Event("B102","Party", LocalDate.parse("2021-09-20"),"Banglore",5000.0);
        String actual = service.add(event);
        assertEquals("B101", actual);
    }
	
	
}
