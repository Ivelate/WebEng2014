
package ivelate.p1.soapserver;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8-b131 
 * Generated source version: 2.1
 * 
 */
@WebService(name = "TodoWebService", targetNamespace = "http://soapserver.p1.ivelate/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TodoWebService {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "removeToDo", targetNamespace = "http://soapserver.p1.ivelate/", className = "ivelate.p1.soapserver.RemoveToDo")
    @ResponseWrapper(localName = "removeToDoResponse", targetNamespace = "http://soapserver.p1.ivelate/", className = "ivelate.p1.soapserver.RemoveToDoResponse")
    public boolean removeToDo(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<ivelate.p1.soapserver.ToDo>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listToDos", targetNamespace = "http://soapserver.p1.ivelate/", className = "ivelate.p1.soapserver.ListToDos")
    @ResponseWrapper(localName = "listToDosResponse", targetNamespace = "http://soapserver.p1.ivelate/", className = "ivelate.p1.soapserver.ListToDosResponse")
    public List<ToDo> listToDos();

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addToDo", targetNamespace = "http://soapserver.p1.ivelate/", className = "ivelate.p1.soapserver.AddToDo")
    @ResponseWrapper(localName = "addToDoResponse", targetNamespace = "http://soapserver.p1.ivelate/", className = "ivelate.p1.soapserver.AddToDoResponse")
    public void addToDo(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        Priority arg3);

}
