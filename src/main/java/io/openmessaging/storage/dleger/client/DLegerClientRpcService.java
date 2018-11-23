package io.openmessaging.storage.dleger.client;

import io.openmessaging.storage.dleger.protocol.DLegerClientProtocol;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DLegerClientRpcService implements DLegerClientProtocol {
    private Map<String, String> peerMap = new ConcurrentHashMap<>();
    public void updatePeers(String peers) {
        for (String peerInfo: peers.split(";")) {
            peerMap.put(peerInfo.split("-")[0], peerInfo.split("-")[1]);
        }
    }

    public void updatePeers(Map<String, String> peers) {
        peerMap.putAll(peers);
    }

    public String getPeerAddr(String id) {
        return peerMap.get(id);
    }

    public abstract void startup();
    public abstract void shutdown();
}