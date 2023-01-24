import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(EasyMockExtension.class)
class RaceResultServiceTest {

    @Mock
    private Client client;
    @org.easymock.Mock(type = MockType.NICE)
    private Message message;
    @Mock
    private RaceResultService raceResultService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        message = EasyMock.createMock(Message.class);
    }

    @Test
    public void testOfInstanceClient() {
        assertNotNull(client);
    }

    @Test
    public void testOfInstanceMessage() {
        assertNotNull(message);
    }

    @Test
    public void testOfInstanceRaceResultService() {
        assertNotNull(raceResultService);
    }


    @Test
    public void testAddSubscriberToClientsWithMockito() {
        raceResultService.addSubscriber(client);
        verify(raceResultService, only()).addSubscriber(client);
        when(raceResultService.getSizeOfClients()).thenReturn(1);
        assertEquals(1, raceResultService.getSizeOfClients());

    }

    @Test
    public void testRemoveSubscriberFromClientsWithMockito() {
        raceResultService.removeSubscriber(client);
        verify(raceResultService, atLeastOnce()).removeSubscriber(any());
    }

    @Test
    public void testSendingMessageWithMockito() {
        raceResultService.send(message);
        verify(raceResultService, times(1)).send(message);
    }

    @Test
    public void testAddSubscriberToClientsWithEasyMock() {
        raceResultService = EasyMock.createMock(RaceResultService.class);

        raceResultService.addSubscriber(EasyMock.anyObject());
        EasyMock.expect(raceResultService.getSizeOfClients()).andReturn(1).times(1);
        EasyMock.replay(raceResultService);

        assertEquals(1, raceResultService.getSizeOfClients());

    }

    @Test
    public void testRemoveSubscriberFromClientsWithEasyMock() {
        //TODO
        raceResultService.removeSubscriber(client);
        verify(raceResultService, atLeastOnce()).removeSubscriber(any());
    }

    @Test
    public void testSendingMessageWithEasyMock() {
        raceResultService = EasyMock.createMock(RaceResultService.class);
        message = EasyMock.createMock(Message.class);
        raceResultService.send(message);
        EasyMock.replay(raceResultService, message);

    }


    @AfterEach
    public void close() throws Exception {
        client = null;
        message = null;
        raceResultService = null;
    }


}