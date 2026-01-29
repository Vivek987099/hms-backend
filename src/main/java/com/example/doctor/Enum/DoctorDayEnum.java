package com.example.doctor.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DoctorDayEnum {
	public enum DoctorDay{
		SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY;
		
		@JsonCreator
		public static DoctorDay from(String value) {
			if(value == null || value.trim().isEmpty()) {
				return null;
			}
			return DoctorDay.valueOf(value.toUpperCase());
		}
	}

}
