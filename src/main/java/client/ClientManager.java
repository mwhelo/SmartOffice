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
    private final LightsClient client = new LightsClient();
    private final PrinterClient client1 = new PrinterClient();
    private final ProjectorClient client2 = new ProjectorClient();
    private final MonitorClient client3 = new MonitorClient();

    public ClientManager() {
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());
            jmdns.addServiceListener(client.getServiceType(), this);
            jmdns.addServiceListener(client1.getServiceType(), this);
            jmdns.addServiceListener(client2.getServiceType(), this);
            jmdns.addServiceListener(client3.getServiceType(), this);
            

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
    
    
    public void serviceAdded(ServiceEvent arg0) {
        System.out.println(arg0);
        arg0.getDNS().requestServiceInfo(arg0.getType(), arg0.getName(), 0);
        
    }
    
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
        
        //PrinterClient
        if (client1.getServiceType().equals(type) && client1.hasMultiple()) {
            if (client1.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
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
        
        //Projector
        if (client2.getServiceType().equals(type) && client2.hasMultiple()) {
            if (client2.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                client2.switchService(newService);
            }
            client2.remove(name);
        } else if (client2.getServiceType().equals(type)) {
            ui.removePanel(client2.returnUI());
            client2.disable();
            client2.initialized = false;
        }
        
        //Monitor
        if (client3.getServiceType().equals(type) && client3.hasMultiple()) {
            if (client3.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                client3.switchService(newService);
            }
            client3.remove(name);
        } else if (client3.getServiceType().equals(type)) {
            ui.removePanel(client3.returnUI());
            client3.disable();
            client3.initialized = false;
        }
    }
    
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
        
        //PrinterClient
        if (client1.getServiceType().equals(type) && !client1.isInitialized()) {
            client1.setUp(address, port);
            ui.addPanel(client1.returnUI(), client1.getName());
            client1.setCurrent(arg0.getInfo());
            client1.addChoice(arg0.getInfo());
        } else if (client1.getServiceType().equals(type)
                && client1.isInitialized()) {
            client1.addChoice(arg0.getInfo());

        }
        
        //ProjectorClient
        if (client2.getServiceType().equals(type) && !client2.isInitialized()) {
            client2.setUp(address, port);
            ui.addPanel(client2.returnUI(), client2.getName());
            client2.setCurrent(arg0.getInfo());
            client2.addChoice(arg0.getInfo());
        } else if (client2.getServiceType().equals(type)
                && client2.isInitialized()) {
            client2.addChoice(arg0.getInfo());

        }
        
        //MonitorClient
        if (client3.getServiceType().equals(type) && !client3.isInitialized()) {
            client3.setUp(address, port);
            ui.addPanel(client3.returnUI(), client3.getName());
            client3.setCurrent(arg0.getInfo());
            client3.addChoice(arg0.getInfo());
        } else if (client3.getServiceType().equals(type)
                && client3.isInitialized()) {
            client3.addChoice(arg0.getInfo());

        }
        
    }

    public static void main(String[] args) {
        new ClientManager();

    }
}
