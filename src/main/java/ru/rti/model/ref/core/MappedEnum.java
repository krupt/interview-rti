package ru.rti.model.ref.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MappedEnum {

	Class<? extends Enum<?>> value();

}