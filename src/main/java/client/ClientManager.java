/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author x12431142
 */
import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import clientui.ClientManagerUI;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager implements ServiceListener {

    private final ClientManagerUI ui;
    private JmDNS jmdns;
    private final LightsClient client1 = new LightsClient();
    private final PrinterClient client = new PrinterClient();
    private final ProjectorClient client2 = new ProjectorClient();

    public ClientManager() {
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());
            jmdns.addServiceListener(client.getServiceType(), this);
            jmdns.addServiceListener(client1.getServiceType(), this);
            jmdns.addServiceListener(client2.getServiceType(), this);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
        ui = new ClientManagerUI(this);
    }

    public void end() {
        try {
            jmdns.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //PrinterClient
    public void serviceAdded(ServiceEvent arg0) {
        System.out.println(arg0);
        arg0.getDNS().requestServiceInfo(arg0.getType(), arg0.getName(), 0);
        
    }
    
    //LightsClient
    public void serviceAdded1(ServiceEvent arg1) {
        System.out.println(arg1);
        arg1.getDNS().requestServiceInfo(arg1.getType(), arg1.getName(), 0);
        
    }

    //printerclient
    public void serviceRemoved(ServiceEvent arg0) {
        System.out.println(arg0);
        String type = arg0.getType();
        String name = arg0.getName();
        ServiceInfo newService = null;
        if (client.getServiceType().equals(type) && client.hasMultiple()) {
            if (client.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                client.switchService(newService);
            }
            client.remove(name);
        } else if (client.getServiceType().equals(type)) {
            ui.removePanel(client.returnUI());
            client.disable();
            client.initialized = false;
        }
    }
    
    //lightsclient
    public void serviceRemoved1(ServiceEvent arg1) {
        System.out.println(arg1);
        String type = arg1.getType();
        String name = arg1.getName();
        ServiceInfo newService = null;
        if (client1.getServiceType().equals(type) && client1.hasMultiple()) {
            if (client1.isCurrent(name)) {
                ServiceInfo[] b = jmdns.list(type);
                for (ServiceInfo in : b) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                client1.switchService(newService);
            }
            client1.remove(name);
        } else if (client1.getServiceType().equals(type)) {
            ui.removePanel(client1.returnUI());
            client1.disable();
            client1.initialized = false;
        }
    }

    //printerclient
    public void serviceResolved(ServiceEvent arg0) {
        System.out.println(arg0);
        String address = arg0.getInfo().getHostAddress();
        int port = arg0.getInfo().getPort();
        String type = arg0.getInfo().getType();

        if (client.getServiceType().equals(type) && !client.isInitialized()) {
            client.setUp(address, port);
            ui.addPanel(client.returnUI(), client.getName());
            client.setCurrent(arg0.getInfo());
            client.addChoice(arg0.getInfo());
        } else if (client.getServiceType().equals(type)
                && client.isInitialized()) {
            client.addChoice(arg0.getInfo());

        }
    }
    
    //lightclient
    public void serviceResolved1(ServiceEvent arg1) {
        System.out.println(arg1);
        String address = arg1.getInfo().getHostAddress();
        int port = arg1.getInfo().getPort();
        String type = arg1.getInfo().getType();

        if (client1.getServiceType().equals(type) && !client1.isInitialized()) {
            client1.setUp(address, port);
            ui.addPanel1(client1.returnUI(), client1.getName());
            client1.setCurrent(arg1.getInfo());
            client1.addChoice(arg1.getInfo());
        } else if (client1.getServiceType().equals(type)
                && client1.isInitialized()) {
            client1.addChoice(arg1.getInfo());

        }
    }

    public static void main(String[] args) {
        new ClientManager();

    }
}
