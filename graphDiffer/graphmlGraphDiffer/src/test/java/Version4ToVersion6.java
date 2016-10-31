import net.itransformers.idiscover.api.DiscoveryResult;
import net.itransformers.idiscover.api.models.graphml.GraphmlEdge;
import net.itransformers.idiscover.api.models.graphml.GraphmlGraph;
import net.itransformers.idiscover.api.models.graphml.GraphmlNode;
import net.itransformers.xmlNodeDataProvider.XmlNodeDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class Version4ToVersion6  {
    DiscoveryResult discoveryResult;
    GraphmlGraph graphA;
    GraphmlGraph graphB;
    GraphmlGraph graphC;
    @Before
    public void doInit(){

        discoveryResult = new XmlNodeDataProvider("graphDiffer/graphmlGraphDiffer/src/test/resources/netTransformer123");
        graphA = discoveryResult.getNetwork("version4");
        graphB = discoveryResult.getNetwork("version6");
        GraphmlGraphDiffer differ = new GraphmlGraphDiffer(null, null);
        graphC = differ.doDiff(graphA, graphB);

    }

    @Test
    public void assertTotalNumberOfNodes() {
        Assert.assertEquals(59, graphC.getGraphmlNodes().size());

    }

    @Test
    public void nodeChangesVersion4Version6() {

        int addedNodes = 0;
        int removedNodes = 0;
        int changedNodes = 0;

        List<GraphmlNode> nodes = graphC.getGraphmlNodes();
        for (GraphmlNode node : nodes) {

           Map<String,String> graphmlNodeMetaData = node.getGraphmlNodeData();
           String diff = graphmlNodeMetaData.get("diff");
           if ("added".equals(diff)) {
               addedNodes++;
               System.out.println("Node added: " + node.getId());
           }
            if ("removed".equals(diff)) {
                removedNodes++;
                System.out.println("Node removed: " + node.getId());

            }
            if ("changed".equals(diff)) {
                System.out.println("Node changed: "+node.getId());
                System.out.println(graphmlNodeMetaData.get("diffs"));

                changedNodes++;
            }
        }
        Assert.assertEquals(10,changedNodes);

        Assert.assertEquals(26,addedNodes);

        Assert.assertEquals(22,removedNodes);

        //AssertUnchanged!
        Assert.assertEquals(1,nodes.size()-(changedNodes+addedNodes+removedNodes));


    }


    @Test
    public void edgeChangesVersion4Version6() {

        int addedEdges = 0;
        int removedEdges = 0;
        int changedEdges = 0;

        List<GraphmlEdge> edges = graphC.getGraphmlEdges();
        for (GraphmlEdge edge : edges) {

            Map<String,String> graphmlNodeMetaData = edge.getGraphmlEdgeData();
            String diff = graphmlNodeMetaData.get("diff");
            if ("added".equals(diff)) {
                addedEdges++;
                System.out.println("Edge added: " + edge.getId());
            }
            if ("removed".equals(diff)) {
                removedEdges++;
                System.out.println("Edge removed: " + edge.getId());

            }
            if ("changed".equals(diff)) {
                System.out.println("Edge changed: "+edge.getId());
                System.out.println(graphmlNodeMetaData.get("diffs"));

                changedEdges++;
            }
        }
        Assert.assertEquals(11,changedEdges);

        Assert.assertEquals(31,addedEdges);

        Assert.assertEquals(22,removedEdges);

        //AssertUnchanged!
        Assert.assertEquals(0,edges.size()-(changedEdges+addedEdges+removedEdges));


    }

}