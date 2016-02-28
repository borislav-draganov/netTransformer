/*
 * RowRemoveListener.java
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

package net.itransformers.topologyviewer.dialogs.snmpDiscovery;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RowRemoveListener implements ActionListener{
	private final JTable table;
	public RowRemoveListener(JTable table ) {
		this.table = table;
	}
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel deviceTableModel = (DefaultTableModel)table.getModel();
		int rowIndex = table.getSelectedRow();
        if (rowIndex>=0) {
		    deviceTableModel.removeRow(rowIndex);
        }
	}
}
