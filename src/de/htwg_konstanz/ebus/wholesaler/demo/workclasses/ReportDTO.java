/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg_konstanz.ebus.wholesaler.demo.workclasses;

import java.io.OutputStream;
import java.util.List;

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
     * Export form Database.
     */
    private OutputStream export;
    /**
     * Exporttype.
     */
    private String exportType;

    private List notImported;

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

    /**
     * Returns the Export.
     *
     * @return ExportProductsFromDatabase
     */
    public final OutputStream getExport() {
        return export;
    }

    /**
     * Set the export.
     *
     * @param exportIN ExportProductsFromDatabase
     */
    public final void setExport(final OutputStream exportIN) {
        this.export = exportIN;
    }

    /**
     * Get the Exporttype.
     *
     * @return String
     */
    public final String getExportType() {
        return exportType;
    }

    /**
     * Set the exportType.
     *
     * @param exportTypeIN String
     */
    public final void setExportType(final String exportTypeIN) {
        this.exportType = exportTypeIN;
    }

    /**
     * Returns the List of not imported products.
     *
     * @return List
     */
    public final List getNotImported() {
        return notImported;
    }

    /**
     * Set the List of notImported Products.
     *
     * @param notImportedIN List
     */
    public final void setNotImported(final List notImportedIN) {
        this.notImported = notImportedIN;
    }

}
