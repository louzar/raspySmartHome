package com.dor.smarthome.app.db.persistance;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 15.06.2014.
 */
@Entity
@Table(name = "TemperatureHistory")
public class TemperatureHistoryPO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id of the protocol.
     */
    private Long id;

    private Date measureDate;

    private Integer temperature;

    /**
     * Get id of the protocol.
     *
     * @return id of the protocol
     */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGeneratorKoNu")
    @SequenceGenerator(name = "seqGeneratorKoNu", sequenceName = "TemperatureHistory_seq", initialValue = 1)
    public Long getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param newVal
     *            id
     */
    public void setId(Long newVal) {
        id = newVal;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "measure_date")
    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    @Column(name = "temperature")
    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}
