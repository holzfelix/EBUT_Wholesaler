/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

/**
 *
 * @author Felix
 */
public class ReportDTO {

    /**
     * ReportType can be true or false.
     */
    private Boolean type;
    /**
     * Report Message.
     */
    private String message;

    /**
     * Construktor.
     *
     * @param typeIN boolean
     * @param messageIN String
     */
    public ReportDTO(final Boolean typeIN, final String messageIN) {
        this.type = typeIN;
        this.message = messageIN;
    }

    /**
     * Returns the Report Type.
     *
     * @return Boolean
     */
    public final Boolean getType() {
        return type;
    }

    /**
     * Set the Report Type.
     *
     * @param typeIN Boolean
     */
    public final void setType(final Boolean typeIN) {
        this.type = typeIN;
    }

    /**
     * Returns the Report Message.
     *
     * @return String
     */
    public final String getMessage() {
        return message;
    }

    /**
     * Report Message setter.
     *
     * @param messageIN String
     */
    public final void setMessage(final String messageIN) {
        this.message = messageIN;
    }

}
