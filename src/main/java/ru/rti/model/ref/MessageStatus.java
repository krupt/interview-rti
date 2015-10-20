package ru.rti.model.ref;

import javax.persistence.*;

import ru.rti.model.Message;
import ru.rti.model.ref.core.MappedEnum;
import ru.rti.model.ref.core.Reference;

@Entity
@Table(name = Reference.PREFIX + "MessageStatus")
@MappedEnum(Message.Status.class)
public class MessageStatus extends Reference {
	/*
	 * Не добавляем доп. полей
	 */
}