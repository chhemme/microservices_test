package de.hemme.bachelorarbeit.summarizer.entity;

import java.util.Date;
import java.util.UUID;

import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * A SystemEvent is a simple entity, which is used in this WizBook demo application.
 */
public class SystemEvent {

	private long eventId;
	private long systemId;
	private long eventFlowID;
	private long processKey;

	@NotNull
	private Date date;
	private String messagedetails;
	private String errormessage;

	public SystemEvent() {
	}

	public SystemEvent(long eventId, long systemId, long processKey, Date date) {
		this(eventId, systemId, processKey, date, "", "");
	}

	public SystemEvent(long eventId, long systemId, long processKey, Date date, String messagedetails, String errormessage) {
		this.eventId = eventId;
		this.systemId = systemId;
		this.processKey = processKey;
		this.date = date;
		this.messagedetails = messagedetails;
		this.errormessage = errormessage;
	}



	public long getEventId() {
		return eventId;
	}


	public String getMessagedetails() {
		return messagedetails;
	}

	public long getSystemId() {
		return systemId;
	}

	public long getEventFlowID() {
		return eventFlowID;
	}

	public long getProcessKey() {
		return processKey;
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
