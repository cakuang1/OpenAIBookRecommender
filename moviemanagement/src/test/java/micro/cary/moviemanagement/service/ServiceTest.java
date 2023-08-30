package micro.cary.moviemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.HashMap;
import java.util.Map;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import micro.cary.moviemanagement.utils.RedisSessionManager;
@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    

    @Mock
    private RedisSessionManager sessionManager;

    @InjectMocks
    private SessionService sessionService;

    @Test
    public void testStoreSessionData() {
        // Define your test data
        String sessionId = "session123";
        Map<String, String> sessionData = new HashMap<>();
        int sessionDurationInSeconds = 3600; 


        doNothing().when(sessionManager).storeSession(sessionId, sessionData, sessionDurationInSeconds);


        sessionService.storeSessionData(sessionId, sessionData, sessionDurationInSeconds);


        verify(sessionManager).storeSession(sessionId, sessionData, sessionDurationInSeconds);
    }

    @Test
    public void testGetSessionData() {

        String sessionId = "session123";
        Map<String, String> expectedSessionData = new HashMap<>();

        when(sessionManager.getSessionData(sessionId)).thenReturn(expectedSessionData);


        Map<String, String> actualSessionData = sessionService.getSessionData(sessionId);


        assertEquals(expectedSessionData, actualSessionData);
    }

    @Test
    public void testAddKeyToSessionData() {
        // Define your test data
        String sessionId = "session123";
        String key = "someKey";
        String value = "someValue";

        // Mock the behavior of the session manager
        doNothing().when(sessionManager).addKeyToSession(sessionId, key, value);

        // Call the method being tested
        sessionService.addKeyToSessionData(sessionId, key, value);

        // Verify that the method was called with the expected arguments
        verify(sessionManager).addKeyToSession(sessionId, key, value);
    }

    @Test
    public void testDeleteKeyFromSessionData() {
        // Define your test data
        String sessionId = "session123";
        String keyToDelete = "keyToDelete";

        // Mock the behavior of the session manager
        doNothing().when(sessionManager).deleteKeyFromSession(sessionId, keyToDelete);

        // Call the method being tested
        sessionService.deleteKeyFromSessionData(sessionId, keyToDelete);

        // Verify that the method was called with the expected arguments
        verify(sessionManager).deleteKeyFromSession(sessionId, keyToDelete);
    }
}
