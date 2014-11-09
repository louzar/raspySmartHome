package com.dor.smarthome.app.db.persistance;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by andrew on 09.11.14.
 */
@Entity
@Table(schema = "PUBLIC", name = "HumidityHistory")
public class HumidityHistoryPO implements Serializable {
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id of the protocol.
     */
    private Long id;

    private Date measureDate;

    private Integer humidity;

    /**
     * Get id of the protocol.
     *
     * @return id of the protocol
     */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGeneratorKoNu")
    @SequenceGenerator(name = "seqGeneratorKoNu", sequenceName = "TemperatureHistory_seq")
    public Long getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param newVal id
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

    @Column(name = "humidity")
    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
