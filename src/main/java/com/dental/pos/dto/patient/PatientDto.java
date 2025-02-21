package com.dental.pos.dto.patient;

import com.dental.pos.entity.Patient;
import com.dental.pos.util.common.CommonConstants;
import com.dental.pos.util.common.CommonUtil;

import com.dental.pos.util.enums.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

	private Long patientId;

	private String ref;

	private String name;

	private String phone;

	private String age;

	private String address;

	private String contactDetail;

	private Integer doctorId;

	private String doctorName;

	private String updatedDateTime;
	
	public PatientDto(Patient entity) {

		if (entity != null) {

			this.patientId = entity.getPatientId();

			this.ref = entity.getRef();

			this.name = entity.getName();

			this.phone = entity.getPhone();

			this.age = entity.getAge();

			this.address = entity.getAddress();

			this.contactDetail = entity.getContactDetail();

			this.updatedDateTime = CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getCreatedTime());

			this.doctorId= entity.getDoctorId();

			this.doctorName = Doctor.getDescriptionByCode(entity.getDoctorId());
		}
	}
}
