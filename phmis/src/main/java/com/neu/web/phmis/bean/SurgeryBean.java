/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "surgery")
@Component
public class SurgeryBean implements Serializable {

    private int id;
    private UserBean doctor;
    private OperatingRoomBean operatingRoomBean;
    private Date scheduleDate;
    private String type;
    private String patientName;
    private Date creationDate;
    private SurgeryRequestBean surgeryRequestBean;
    private List<MedicalStaffBean> medicalTeam;

    @ManyToMany(mappedBy = "surgeryList")
    public List<MedicalStaffBean> getMedicalTeam() {
        return medicalTeam;
    }

    public void setMedicalTeam(List<MedicalStaffBean> medicalTeam) {
        this.medicalTeam = medicalTeam;
    }

    @OneToOne(mappedBy = "surgeryBean", cascade = CascadeType.ALL)
    public SurgeryRequestBean getSurgeryRequestBean() {
        return surgeryRequestBean;
    }

    public void setSurgeryRequestBean(SurgeryRequestBean surgeryRequestBean) {
        this.surgeryRequestBean = surgeryRequestBean;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    public UserBean getDoctor() {
        return doctor;
    }

    public void setDoctor(UserBean doctor) {
        this.doctor = doctor;
    }

    @ManyToOne
    @JoinColumn(name = "operatingRoomId")
    public OperatingRoomBean getOperatingRoomBean() {
        return operatingRoomBean;
    }

    public void setOperatingRoomBean(OperatingRoomBean operatingRoomBean) {
        this.operatingRoomBean = operatingRoomBean;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
