package Utilities.Model;

import Engine.Renderer;
import Engine.States.State;
import Model.ApicalConstrictingCell;
import Model.Cell;
import Physics.Rigidbodies.*;
import Utilities.Geometry.Vector2f;
import Utilities.Geometry.Vector2i;
import Utilities.Math.CustomMath;

import java.util.*;

public class Builder {
    /**
     * Makes four cells with simple box structure where the cells have four edges, each with a length of 1.
     * Three are Basic Edges, one is an Apical Edge.
     * @return list of cells in the structure.
     */
    public static List<Cell> getSimpleFourCellBox() throws InstantiationException, IllegalAccessException {
        int numberOfCells = 5;
        int lateralResolution = 4;
        Vector2f nullPosition = new Vector2f(5000);

        List<Edge> sideB = new ArrayList<>();
        List<Cell> cells = new ArrayList<>();
        for(int i =0; i < numberOfCells; i++)
        {
            List<Edge> sideA = new ArrayList<>();
            Node oldNode = new Node(nullPosition);
            for(int j = 0; j <= lateralResolution; j++)
            {
                Node n = new Node((i*100) + 200, (j*100)+ 200);
                if(oldNode.getPosition()!= nullPosition) {
                    sideA.add(new LateralEdge(oldNode, n));
                }
                oldNode = n;
            }
            if(i != 0){
                Cell c = createCell(sideB, sideA,ApicalConstrictingCell.class);
                cells.add(c);
            }
            sideB = sideA;

        }
        return cells;
    }

    /**
     * Makes 80 cells in a ring structure
     * Four per side are Lateral Edges, one is an Apical Edge, one is Basal Edge.
     * @return list of cells in the structure.
     */
    public static List<Cell> getCellRingSplitBuild(int numberOfSegmentsInTotalCircle,
                                                   int lateralResolution,
                                                   int innerRadius,
                                                   int outerRadius,
                                                   Vector2i boundingBox)
            throws InstantiationException, IllegalAccessException {
        Vector2f position, unitVector;
        ArrayList<Edge> oldEdges = new ArrayList<>();
        ArrayList<Edge> zeroEdge = new ArrayList<>();
        ArrayList<Edge> rearEdge = new ArrayList<>();
        List<Cell> allCells = new ArrayList<>();

        //make lateral edges
        for (int i = 0; i < (numberOfSegmentsInTotalCircle / 2); i++) {
            ArrayList<Edge> edges = new ArrayList<>();
            unitVector = CustomMath.GetUnitVectorOnCircle(i, numberOfSegmentsInTotalCircle, lateralResolution);
            Node lastNode = new Node();   // Create null vertex to be used to create edges later.
            for (int j = 0; j <= lateralResolution; j++) {
                float nodeRadius = outerRadius + (innerRadius - outerRadius) / lateralResolution * j;
                // Transform polar to world coordinates
                position = CustomMath.TransformToWorldSpace(unitVector, nodeRadius, boundingBox.asFloat());
                Node currentNode = new Node(position);
                if (j >= 1) {
                    edges.add(new LateralEdge(currentNode, lastNode));
                }
                lastNode = currentNode;
            }
            if (i > 0) {
                Cell newCell;
                if (i <= 10) newCell = createCell(oldEdges, edges, ApicalConstrictingCell.class);
                else newCell = createCell(oldEdges, edges, Cell.class);
                newCell.setRingLocation(i);
                allCells.add(newCell);

            } else if (i == 0) {
                zeroEdge = edges;
            }
            if(i == 41) rearEdge = edges;

            oldEdges = edges;
        }
        for (int i = 79; i > numberOfSegmentsInTotalCircle / 2; i--) {
            ArrayList<Edge> edges = new ArrayList<>();
            unitVector = CustomMath.GetUnitVectorOnCircle(i, numberOfSegmentsInTotalCircle, lateralResolution);
            Node lastNode = new Node();   // Create null vertex to be used to create edges later.
            for (int j = 0; j <= lateralResolution; j++) {
                float nodeRadius = outerRadius + (innerRadius - outerRadius) / lateralResolution * j;
                // Transform polar to world coordinates
                position = CustomMath.TransformToWorldSpace(unitVector, nodeRadius, boundingBox.asFloat());
                Node currentNode = new Node(position);
                if (j >= 1) {
                    edges.add(new LateralEdge(currentNode, lastNode));
                }
                lastNode = currentNode;
            }
            if (i < 79) {
                Cell newCell;
                if (i >= 70) newCell = createCell(edges, oldEdges, ApicalConstrictingCell.class);
                //if(i == 40) newCell = createCell(edges, rearEdge, Cell.class);
                else newCell = createCell(oldEdges, edges, Cell.class);
                newCell.setRingLocation(80 - (i - 1));
                allCells.add(newCell);

            } else if (i == 79) {
                Cell newCell = createCell(edges, zeroEdge, ApicalConstrictingCell.class);
                newCell.setRingLocation(1);
                allCells.add(newCell);
            }

            oldEdges = edges;
        }
        return allCells;
    }


    public static Cell createCell(List<Edge> sideA, List<Edge> sideB, Class cellClass) throws InstantiationException, IllegalAccessException {

        // Get vertices from edge segments, which make up the lateral edges
        List<Node> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        List<Edge> sideBr = reverse(sideB);
        List<Edge> sideAf = sideA; //sideAf.get(0).flip();
        //sideBr.get(0).flip();
        nodes.addAll(addVerticesFromEdgeList(sideBr, true));
        nodes.addAll(addVerticesFromEdgeList(sideAf, false));
        /*for (Node node:nodes
             ) {
            System.out.println(node.getPosition().x +"::"+ node.getPosition().y);

        }*/
        edges.addAll(sideAf);

        edges.addAll(sideBr);
        /*for(Edge edge: sideAf){
            System.out.println("POS: " + edge.getPositions()[0].x + "," + edge.getPositions()[0].y + "::"
                    + edge.getPositions()[1].x + "," + edge.getPositions()[1].y);
        }
        System.out.println("B");
        for(Edge edge: sideBr){
            System.out.println("POS: " + edge.getPositions()[0].x + "," + edge.getPositions()[0].y + "::"
                    + edge.getPositions()[1].x + "," + edge.getPositions()[1].y);
        }*/
        // Create internal lattice:
        //  0   4
        //  1   3
        //  2   2
        //  3   1
        //  4   0
        Edge edgeA;
        Edge edgeB;
        List<Edge> internalEdges = new ArrayList<>();
        int n = 4;

        for(int i = 0; i < sideA.size(); i++)
        {
            edgeA = sideAf.get(i);
            edgeB = sideBr.get(n - 1 - i);
            Node a = edgeA.getNodes()[0];
            Node b = edgeA.getNodes()[1];
            Node c = edgeB.getNodes()[0];
            Node d = edgeB.getNodes()[1];

            internalEdges.add(new BasicEdge(a,d));
            internalEdges.add(new BasicEdge(b,c));
        }

        // Create the apical edges of the cell
        //Node apicalA = nodes.get(1);
        //Node apicalB = nodes.get(9);
        Node apicalA = sideAf.get(n-1).getNodes()[1];
        Node apicalB = sideBr.get(0).getNodes()[1];
        Edge apicalEdge = new BasalEdge(apicalA, apicalB);
        edges.add(apicalEdge);

        // Create the basal edges of the cell
        Node basalB = sideAf.get(0).getNodes()[0];
        Node basalA = sideBr.get(n-1).getNodes()[0];
        Edge basalEdge = new ApicalEdge(basalA, basalB);
        edges.add(basalEdge);

        // compile and create the cell object
        Cell cell = (Cell)State.create(cellClass);
        cell.setNodes(nodes);
        cell.setEdges(edges);
        cell.setInternalEdges(internalEdges);
        State.setFlagToRender(cell);
        cell.setColor(Renderer.defaultColor);
        return cell;
    }

    private static List <Edge> reverse(List<Edge> sideB) {
        List<Edge> c = new ArrayList<>();
        int len = sideB.size();
        for(int i = 0; i < len; i++){
            Edge e = sideB.get(len - i - 1);
            //e.flip();
            c.add(e);
        }
        return c;
    }

    private static List<Node> addVerticesFromEdgeList(List<Edge> edgeList, boolean reversed) {
        List<Node> vertices = new ArrayList<>();
        Node[] vtxArray = new Node[2];
        int len = edgeList.size();
        if(reversed) {
            vtxArray = edgeList.get(0).getNodes();
                vertices.add(vtxArray[1]);
        }
        for (int i = 0; i < len; i++)
        {
             vtxArray = edgeList.get(i).getNodes();

            if (!vertices.contains(vtxArray[0])) {
                vertices.add(vtxArray[0]);
            }
        }
        if(!reversed) {
            if (!vertices.contains(vtxArray[1])) {
                vertices.add(vtxArray[1]);
            }
        }
        return vertices;
    }

    private static void buildCellAsEdgeMono(){

    }

    /*private List<Edge> clone(List<Edge> edges){
        List<Edge> e = new ArrayList<>();
        for(Edge edge: edges){
            if(edge instanceof BasicEdge) e.add(BasicEdge.clone());
        }
        return e;
    }*/

}
