/*
 * AssertionsType.java
 *
 * This work is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This work is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 *
 * Copyright (c) 2010-2016 iTransformers Labs. All rights reserved.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.28 at 02:21:35 PM EET 
//


package net.itransformers.assertions.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assertionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assertionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assert" type="{}assertType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="assertTypes" type="{}assertTypesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assertionsType", propOrder = {
    "_assert",
    "assertTypes"
})
public class AssertionsType {

    @XmlElement(name = "assert")
    protected List<AssertType> _assert;
    @XmlElement(required = true)
    protected AssertTypesType assertTypes;

    /**
     * Gets the value of the assert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssertType }
     * 
     * 
     */
    public List<AssertType> getAssert() {
        if (_assert == null) {
            _assert = new ArrayList<AssertType>();
        }
        return this._assert;
    }

    /**
     * Gets the value of the assertTypes property.
     * 
     * @return
     *     possible object is
     *     {@link AssertTypesType }
     *     
     */
    public AssertTypesType getAssertTypes() {
        return assertTypes;
    }

    /**
     * Sets the value of the assertTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssertTypesType }
     *     
     */
    public void setAssertTypes(AssertTypesType value) {
        this.assertTypes = value;
    }

}
