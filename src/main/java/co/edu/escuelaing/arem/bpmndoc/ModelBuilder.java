package co.edu.escuelaing.arem.bpmndoc;

import co.edu.escuelaing.arem.bpmndoc.model.Element;
import co.edu.escuelaing.arem.bpmndoc.model.Lane;
import co.edu.escuelaing.arem.bpmndoc.model.Pool;
import co.edu.escuelaing.arem.bpmndoc.model.elements.EndEvent;
import co.edu.escuelaing.arem.bpmndoc.model.elements.StartEvent;
import co.edu.escuelaing.arem.bpmndoc.model.elements.Task;
import co.edu.escuelaing.arem.bpmndoc.model.elements.XOR;
import java.util.HashMap;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * //TODO
 *
 * @author Daniel Ospina
 */
public class ModelBuilder {

    public static Pool getModel(Node node) {
        Node process = findProcess(node);
        //XMLParser.navigate(process);
        Pool pool = buildModel(process);
        return pool;
    }

    private static Node findProcess(Node node) {

        if (node.getNodeName().equals("model:process")) {
            return node;
        } else {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node result = findProcess(childNodes.item(i));
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private static Pool buildModel(Node process) {
        Pool pool = new Pool();
        NamedNodeMap attributes = process.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.item(i).getNodeName().equals("id")) {
                pool.setId(attributes.item(i).getNodeValue());
            }
            else if (attributes.item(i).getNodeName().equals("name")) {
                pool.setName(attributes.item(i).getNodeValue());
            }
        }
        
        NodeList processChilds = process.getChildNodes();
        for (int i = 0; i < processChilds.getLength(); i++) {
            switch (processChilds.item(i).getNodeName()) {
                case "model:documentation":
                    pool.setDescription(processChilds.item(i).getChildNodes().item(0).getNodeValue());
                    break;
                case "model:laneSet":
                    setLaneSet(processChilds.item(i), pool);
                    break;
                case "model:startEvent":
                    setStartEvent(processChilds.item(i), pool);
                    break;
                case "model:endEvent":
                    setEndEvent(processChilds.item(i), pool);
                    break;
                case "model:userTask":
                    setUserTask(processChilds.item(i), pool);
                    break;
                case "model:serviceTask":
                    setServiceTask(processChilds.item(i), pool);
                    break;
                case "model:receiveTask":
                    setReceiveTask(processChilds.item(i), pool);
                    break;
                case "model:exclusiveGateway":
                    setExclusiveGateway(processChilds.item(i), pool);
                    break;
                case "model:sequenceFlow":
                    setSequenceFlow(processChilds.item(i), pool);
                    break;
                default:
                    break;
            }
        }
        return pool;
    }

    private static void setElement(Node item, Pool pool, Element element) {
        NamedNodeMap startEventAttributes = item.getAttributes();
        for (int i = 0; i < startEventAttributes.getLength(); i++) {
            if (startEventAttributes.item(i).getNodeName().equals("id")) {
                element.setId(startEventAttributes.item(i).getNodeValue());
            }
            else if (startEventAttributes.item(i).getNodeName().equals("name")) {
                element.setName(startEventAttributes.item(i).getNodeValue());
            }
        }
        NodeList startEventChilds = item.getChildNodes();
        for (int i = 0; i < startEventChilds.getLength(); i++) {
            if (startEventChilds.item(i).getNodeName().equals("model:documentation")) {
                element.setDescription(startEventChilds.item(i).getChildNodes().item(0).getNodeValue());
            }
        }
        
        for (Lane lane : pool.getLanes().values()) {
            if (lane.getElements().containsKey(element.getId())) {
                Element check = lane.getElements().put(element.getId(), element); //TODO eliminar return value si no da problemas
                if (check != null) System.out.println("Oh oh!, this wasnt supposed to happen, value: " + check.getName()); //TODO quitar print
            }
        }
    }
    
    private static void setLaneSet(Node item, Pool pool) {
        NodeList laneSetChilds = item.getChildNodes();
        for (int i = 0; i < laneSetChilds.getLength(); i++) {
            if (laneSetChilds.item(i).getNodeName().equals("model:lane")) {
                addLane(laneSetChilds.item(i), pool);
            }
        }
    }

    private static void setStartEvent(Node item, Pool pool) {
        StartEvent event = new StartEvent();
        setElement(item, pool, event);
    }

    private static void setEndEvent(Node item, Pool pool) {
        EndEvent event = new EndEvent();
        setElement(item, pool, event);
    }

    private static void setUserTask(Node item, Pool pool) {
        Task task = new Task("User");
        setElement(item, pool, task);
    }

    private static void setServiceTask(Node item, Pool pool) {
        Task task = new Task("Service");
        setElement(item, pool, task);
    }

    private static void setReceiveTask(Node item, Pool pool) {
        Task task = new Task("Receive");
        setElement(item, pool, task);
    }

    private static void setExclusiveGateway(Node item, Pool pool) {
        XOR gateway = new XOR();
        NamedNodeMap startEventAttributes = item.getAttributes();
        for (int i = 0; i < startEventAttributes.getLength(); i++) {
            if (startEventAttributes.item(i).getNodeName().equals("default")) {
                gateway.setDefaultElementId(startEventAttributes.item(i).getNodeValue());
            }        
        }
        setElement(item, pool, gateway);
    }

    private static void setSequenceFlow(Node item, Pool pool) {
        NamedNodeMap sequenceFlowAttributes = item.getAttributes();
        String id = sequenceFlowAttributes.getNamedItem("id").getNodeValue();
        String sourceRef = sequenceFlowAttributes.getNamedItem("sourceRef").getNodeValue();
        String targetRef = sequenceFlowAttributes.getNamedItem("targetRef").getNodeValue();
        Element sourceElement = searchElement(sourceRef, pool);
        Element targetElement = searchElement(targetRef, pool);
        sourceElement.setNextConnection(targetElement, id);
        targetElement.setPreviousConnection(sourceElement);
    }

    private static void addLane(Node laneNode, Pool pool) {
        Lane lane = new Lane();
        String poolId = "defaultid";
        NamedNodeMap attributesLane = laneNode.getAttributes();
        for (int i = 0; i < attributesLane.getLength(); i++) {
            if (attributesLane.item(i).getNodeName().equals("id")) {
                lane.setId(attributesLane.item(i).getNodeValue());
                poolId = attributesLane.item(i).getNodeValue();
            }
            else if (attributesLane.item(i).getNodeName().equals("name")) {
                lane.setName(attributesLane.item(i).getNodeValue());
            }
        }
        
        NodeList laneChilds = laneNode.getChildNodes();
        for (int i = 0; i < laneChilds.getLength(); i++) {
            if (laneChilds.item(i).getNodeName().equals("model:documentation")) {
                lane.setDescription(laneChilds.item(i).getChildNodes().item(0).getNodeValue());
            }
            else if (laneChilds.item(i).getNodeName().equals("model:flowNodeRef")) {
                lane.addElement(laneChilds.item(i).getChildNodes().item(0).getNodeValue(), null);
            }
        }
        
        pool.addLane(poolId, lane);
    }

    private static Element searchElement(String idElement, Pool pool) {
        Element elem = null;
        
        for (Lane lane : pool.getLanes().values()) {
            if (elem == null) {
                elem = lane.getElements().get(idElement);
            }
        }
        return elem;
    }

}
