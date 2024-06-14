package com.verticallogistica.service;

import com.verticallogistica.exception.CustomException;
import com.verticallogistica.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessFileSuccess() throws Exception {
        String content = "0000000001                                      Zarelli00000001230000000111512.2420211201\n" +
                "0000000001                                      Zarelli00000001230000000122512.2420211201\n" +
                "0000000002                                     Medeiros00000123450000000111256.2420201201\n" +
                "0000000002                                     Medeiros0000012345000000012256.2420201201";
        MockMultipartFile file = new MockMultipartFile("file", "orders.txt", "text/plain", content.getBytes());

        List<User> users = orderService.processFile(file);

        assertEquals(2, users.size());
        assertEquals("Zarelli", users.get(0).getName());
        assertEquals(2, users.get(0).getOrders().size());
        assertEquals("Medeiros", users.get(1).getName());
        assertEquals(1, users.get(1).getOrders().size());
    }

    @Test
    public void testProcessFileInvalidFormat() {
        String content = "Invalid Content";
        MockMultipartFile file = new MockMultipartFile("file", "orders.txt", "text/plain", content.getBytes());

        assertThrows(CustomException.class, () -> orderService.processFile(file));
    }
}
