/*
 * iTransformer is an open source tool able to discover and transform
 *  IP network infrastructures.
 *  Copyright (C) 2012  http://itransformers.net
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import net.itransformers.idiscover.networkmodel.DiscoveredDeviceData;
import net.itransformers.idiscover.networkmodel.ObjectType;
import net.itransformers.idiscover.networkmodel.ParameterType;
import net.itransformers.idiscover.networkmodel.ParametersType;
import net.itransformers.idiscover.util.JaxbMarshalar;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSetting;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.neo4j.server.configuration.Configurator;
import org.neo4j.server.configuration.ServerConfigurator;
import org.neo4j.shell.ShellSettings;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public class Neo4JDataImporter {
    public static void main(String[] args) throws Exception {
// let the database accept remote neo4j-shell connections
        GraphDatabaseAPI graphdb = (GraphDatabaseAPI) new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder( "target/bigDB" )
                .setConfig( ShellSettings.remote_shell_enabled, GraphDatabaseSetting.TRUE )
                .setConfig( GraphDatabaseSettings.node_keys_indexable, "name,objectType" )
//                .setConfig( GraphDatabaseSettings.relationship_keys_indexable, "relProp1,relProp2" )
                .setConfig( GraphDatabaseSettings.node_auto_indexing, GraphDatabaseSetting.TRUE )
                .setConfig( GraphDatabaseSettings.relationship_auto_indexing, GraphDatabaseSetting.TRUE )
                .newGraphDatabase();
        ServerConfigurator config;
        config = new ServerConfigurator( graphdb );
// let the server endpoint be on a custom port
        config.configuration().setProperty(
                Configurator.WEBSERVER_PORT_PROPERTY_KEY, 7575 );

        WrappingNeoServerBootstrapper srv;
        srv = new WrappingNeoServerBootstrapper( graphdb, config );
        srv.start();
//        IndexManager index = graphdb.index();
//        Index<Node> actors = index.forNodes( "actors" );
//        actors.add( fishburn, "name", fishburn.getProperty( "name" ) );

//       importData(graphdb);
// add some data first
//        File dir = new File("C:\\Documents and Settings\\LYCHO\\My Documents\\4_very_big");

//        Node root = createRootNetworkNode(graphdb);
//        File[] files = dir.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith("device-") && name.endsWith(".xml");
//            }
//        });

//        long start = System.currentTimeMillis();
//        for (File file : files) {
//            importData(graphdb, root, file);
//        }
//        System.out.println("Imported ... "+(System.currentTimeMillis()-start)/1000 + " seconds");
    }
    public static org.neo4j.graphdb.Node createRootNetworkNode(GraphDatabaseService dbApi) throws Exception {
        Transaction tr = dbApi.beginTx();
        try {
            org.neo4j.graphdb.Node node= dbApi.createNode();
            node.setProperty("name","network");
            tr.success();
            return node;
        } catch (Exception e) {
            tr.failure();
            throw e;
        } finally {
            tr.finish();
        }
    }

    public static void importData(GraphDatabaseService dbApi, Node root, File file) throws IOException, JAXBException {
        Transaction tr = dbApi.beginTx();
        try {
            FileInputStream is = new FileInputStream(file);
            DiscoveredDeviceData discoveryManagerType = null;
            try {
                discoveryManagerType = JaxbMarshalar.unmarshal(DiscoveredDeviceData.class, is);
            } finally {
                is.close();
            }
            importDiscoveredDeviceData(dbApi,root, discoveryManagerType);
            tr.success();
        } catch (Exception e) {
            tr.failure();
        } finally {
            tr.finish();
        }
    }
    public static void importDiscoveredDeviceData(GraphDatabaseService dbApi, Node root, DiscoveredDeviceData discoveryManagerType){
        org.neo4j.graphdb.Node node= dbApi.createNode();
        root.createRelationshipTo(node,DynamicRelationshipType.withName("parent"));
        System.out.println("Created node id= "+node.getId());
        node.setProperty("name",discoveryManagerType.getName());
        node.setProperty("objectType","Device"); // Hardcoded because the DiscoveredDeviceData is not natural data type
        ParametersType parameters = discoveryManagerType.getParameters();
        for (ParameterType param : parameters.getParameter()) {
            node.setProperty(param.getName(),param.getValue());
        }
        List<ObjectType> objectTypeList = discoveryManagerType.getObject();
        for (ObjectType objectType : objectTypeList) {
            org.neo4j.graphdb.Node child = importObjectType(dbApi, objectType);
            node.createRelationshipTo(child, DynamicRelationshipType.withName("parent"));
        }
    }
    public static org.neo4j.graphdb.Node importObjectType(GraphDatabaseService dbApi, ObjectType objectType){
        org.neo4j.graphdb.Node node= dbApi.createNode();
        System.out.println("Created node id= "+node.getId());
        if (objectType.getName() != null) node.setProperty("name",objectType.getName());
        node.setProperty("objectType",objectType.getObjectType());
        ParametersType parameters = objectType.getParameters();
        for (ParameterType param : parameters.getParameter()) {
            node.setProperty(param.getName(),param.getValue());
        }
        List<ObjectType> objectTypeList = objectType.getObject();
        for (ObjectType childObjectType : objectTypeList) {
            org.neo4j.graphdb.Node child = importObjectType(dbApi, childObjectType);
            node.createRelationshipTo(child, DynamicRelationshipType.withName("parent"));
        }
        return node;
    }

}