package im.kuka.springboot.demo.repository;

import im.kuka.springboot.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cliff
 */
@Service("participantRepository")
public class ParticipantRepository {

    private Map<String, User> activeSessions = new ConcurrentHashMap<>();

    public void add(String sessionId, User event) {
        activeSessions.put(sessionId, event);
    }

    public User getParticipant(String sessionId) {
        return activeSessions.get(sessionId);
    }

    public void removeParticipant(String sessionId) {
        activeSessions.remove(sessionId);
    }

    public Map<String, User> getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(Map<String, User> activeSessions) {
        this.activeSessions = activeSessions;
    }
}
