

/*
 * SnmpWalker.java
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

package net.itransformers.idiscover.discoverers;

import net.itransformers.idiscover.core.Resource;
import net.itransformers.snmptoolkit.messagedispacher.DefaultMessageDispatcherFactory;
import net.itransformers.snmptoolkit.transport.UdpTransportMappingFactory;
//import net.percederberg.mibble.MibLoaderException;

//import java.io.IOException;

public class SnmpWalker extends SnmpWalkerBase {
    public SnmpWalker(Resource resource) throws Exception {//IOException, MibLoaderException {
        super(resource, new UdpTransportMappingFactory(), new DefaultMessageDispatcherFactory());
    }
}
