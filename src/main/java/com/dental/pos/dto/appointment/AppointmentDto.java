package com.dental.pos.dto.appointment;

import com.dental.pos.entity.Appointment;
import com.dental.pos.util.common.CommonConstants;
import com.dental.pos.util.common.CommonUtil;
import com.dental.pos.util.enums.Doctor;
import com.dental.pos.util.enums.Status;
import com.dental.pos.util.enums.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private Long appointmentId;

    private Long patientId;

    private String patientName;

    private String patientPhone;

    private Integer doctorId;

    private String doctorName;

    private Date date;

    private String dateDesc;

    private Integer timeId;

    private String time;

    private Integer status;

    private String statusName;

    public AppointmentDto(Appointment entity) {

        if (entity != null) {

            this.appointmentId = entity.getAppointmentId();

            if (entity.getPatient() != null) {
                this.patientId = entity.getPatient().getPatientId();
                this.patientName = entity.getPatient().getName();
                this.patientPhone = entity.getPatient().getPhone();
            }

            this.doctorId = entity.getDoctorId();

            this.doctorName = Doctor.getDescriptionByCode(this.doctorId);

            this.dateDesc = CommonUtil.dateToString(CommonConstants.MYSQL_DATE_FORMAT, entity.getDate());

            this.timeId = entity.getTimeId();

            this.time = Time.getDescriptionByCode(this.timeId);

            this.status = entity.getStatus();

            this.statusName = Status.getDescriptionByCode(this.status);
        }
    }
}