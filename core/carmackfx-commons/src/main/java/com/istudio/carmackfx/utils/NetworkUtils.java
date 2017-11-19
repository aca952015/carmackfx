package com.istudio.carmackfx.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkUtils {

    private static String localAddress;
    private static String hostName;

    public static synchronized String getLocalAddress() {

        if (localAddress == null) {

            try {

                for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {

                    NetworkInterface item = e.nextElement();

                    for (InterfaceAddress address : item.getInterfaceAddresses()) {

                        if (address.getAddress() instanceof Inet4Address) {

                            Inet4Address inet4Address = (Inet4Address) address.getAddress();
                            if(inet4Address.isLoopbackAddress() == false) {

                                localAddress = inet4Address.getHostAddress();
                                break;
                            }
                        }
                    }
                }
            } catch (IOException e) {

                localAddress = "";
            }

        }

        return localAddress;
    }

    public synchronized static String getLocalHostName() {

        if (hostName == null) {

            try {

                InetAddress addr = InetAddress.getLocalHost();

                hostName = addr.getHostName();
            } catch (Exception e) {

                hostName = "";
            }
        }

        return hostName;
    }
}
