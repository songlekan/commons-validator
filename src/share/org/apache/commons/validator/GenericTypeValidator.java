/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//validator/src/share/org/apache/commons/validator/GenericTypeValidator.java,v 1.12 2004/01/11 23:30:20 dgraham Exp $
 * $Revision: 1.12 $
 * $Date: 2004/01/11 23:30:20 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001-2004 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names, "Apache", "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.validator;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class contains basic methods for performing validations that return the
 * correctly typed class based on the validation performed.
 */
public class GenericTypeValidator implements Serializable {
    /*
    * Logger.
    */
   private static Log log = LogFactory.getLog(GenericTypeValidator.class);

    /**
     * Checks if the value can safely be converted to a byte primitive.
     *
     * @param value The value validation is being performed on.
     */
    public static Byte formatByte(String value) {
        if (value == null) {
            return null;
        }

        try {
            return new Byte(value);
        } catch(NumberFormatException e) {
            return null;
        }

    }

    /**
     * Checks if the value can safely be converted to a short primitive.
     *
     * @param value The value validation is being performed on.
     */
    public static Short formatShort(String value) {
        if (value == null) {
            return null;
        }

        try {
            return new Short(value);
        } catch(NumberFormatException e) {
            return null;
        }

    }

    /**
     * Checks if the value can safely be converted to a int primitive.
     *
     * @param value The value validation is being performed on.
     */
    public static Integer formatInt(String value) {
        if (value == null) {
            return null;
        }

        try {
            return new Integer(value);
        } catch(NumberFormatException e) {
            return null;
        }

    }

    /**
     * Checks if the value can safely be converted to a long primitive.
     *
     * @param value The value validation is being performed on.
     */
    public static Long formatLong(String value) {
        if (value == null) {
            return null;
        }

        try {
            return new Long(value);
        } catch(NumberFormatException e) {
            return null;
        }

    }

    /**
     * Checks if the value can safely be converted to a float primitive.
     *
     * @param value The value validation is being performed on.
     */
    public static Float formatFloat(String value) {
        if (value == null) {
            return null;
        }

        try {
            return new Float(value);
        } catch(NumberFormatException e) {
            return null;
        }

    }

    /**
     * Checks if the value can safely be converted to a double primitive.
     *
     * @param value The value validation is being performed on.
     */
    public static Double formatDouble(String value) {
        if (value == null) {
            return null;
        }

        try {
            return new Double(value);
        } catch(NumberFormatException e) {
            return null;
        }

    }

    /**
     * <p>Checks if the field is a valid date.  The <code>Locale</code> is
     * used with <code>java.text.DateFormat</code>.  The setLenient method
     * is set to <code>false</code> for all.</p>
     *
     * @param value The value validation is being performed on.
     * @param locale The Locale to use to parse the date (system default if null)
     */
    public static Date formatDate(String value, Locale locale) {
        Date date = null;

        if (value == null) {
            return null;
        }

        try {
            DateFormat formatter = null;
            if (locale != null) {
                formatter =
                        DateFormat.getDateInstance(DateFormat.SHORT, locale);
            } else {
                formatter =
                        DateFormat.getDateInstance(
                                DateFormat.SHORT,
                                Locale.getDefault());
            }

            formatter.setLenient(false);

            date = formatter.parse(value);
        } catch(ParseException e) {
            // Bad date so return null
            log.warn(value, e);
        }

        return date;
    }

    /**
     * <p>Checks if the field is a valid date.  The pattern is used with
     * <code>java.text.SimpleDateFormat</code>.  If strict is true, then the
     * length will be checked so '2/12/1999' will not pass validation with
     * the format 'MM/dd/yyyy' because the month isn't two digits.
     * The setLenient method is set to <code>false</code> for all.</p>
     *
     * @param value The value validation is being performed on.
     * @param datePattern The pattern passed to <code>SimpleDateFormat</code>.
     * @param strict Whether or not to have an exact match of the datePattern.
     */
    public static Date formatDate(String value, String datePattern, boolean strict) {
        Date date = null;

        if (value == null
                || datePattern == null
                || datePattern.length() == 0) {
            return null;
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
            formatter.setLenient(false);

            date = formatter.parse(value);

            if (strict) {
                if (datePattern.length() != value.length()) {
                    date = null;
                }
            }
        } catch(ParseException e) {
            // Bad date so return null
            log.warn(value, e);
        }

        return date;
    }

    /**
     * <p>Checks if the field is a valid credit card number.</p>
     * <p>Reference Sean M. Burke's
     * <a href="http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl">script</a>.</p>
     *
     * @param value The value validation is being performed on.
     */
    public static Long formatCreditCard(String value) {
        return GenericValidator.isCreditCard(value) ? new Long(value) : null;
    }

}
