package de.hemme.bachelorarbeit.collector.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * A SystemEvent is a simple entity, which is used in this WizBook demo application.
 */
public class SystemEvent {

	private String eventId;
	private String systemId;
	private String eventFlowID;
	private long processKey;

	@NotNull
	private Date date;
	private STATE_TYPE state;
	private String messagedetails;
	private String errormessage;


	public SystemEvent(String eventId, String systemId, String eventFlowID, long processKey, Date date, STATE_TYPE state) {
		this(eventId, systemId, eventFlowID, processKey, date, state, "", "");
	}

	public SystemEvent(String eventId, String systemId, String eventFlowID, long processKey, Date date, STATE_TYPE state, String messagedetails, String errormessage) {
		this.eventId = eventId;
		this.systemId = systemId;
		this.eventFlowID = eventFlowID;
		this.processKey = processKey;
		this.date = date;
		this.state = state;
		this.messagedetails = messagedetails;
		this.errormessage = errormessage;
	}

	public STATE_TYPE getState() {
		return state;
	}

	public String getEventId() {
		return eventId;
	}

	public String getSystemId() {
		return systemId;
	}

	public String getEventFlowID() {
		return eventFlowID;
	}

	public long getProcessKey() {
		return processKey;
	}

	public String getMessagedetails() {
		return messagedetails;
	}

	public Date getDate() {
		return date;
	}

	public String getErrormessage() {
		return errormessage;
	}

	@Override
	public String toString() {
		return "SystemEvent [" +
				"eventId=" + eventId +
				", systemId=" + systemId +
				", eventFlowID=" + eventFlowID +
				", processKey=" + processKey +
				", date=" + date +
				", messagedetails='" + messagedetails + '\'' +
				", errormessage='" + errormessage + '\'' +
				']';
	}
}
