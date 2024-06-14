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
    public void testProcessFileSuccess()  {

        String content = """
                0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308
                0000000075                                  Bobbie Batz00000007980000000002     1578.5720211116
                0000000049                               Ken Wintheiser00000005230000000003      586.7420210903
                """;

        MockMultipartFile file = new MockMultipartFile("file", "test-files/orders.txt", "text/plain", content.getBytes());

        List<User> users = orderService.processFile(file);

        assertEquals(3, users.size());
        assertEquals("Ken Wintheiser", users.get(0).getName());
        assertEquals(1, users.get(0).getOrders().size());
        assertEquals("Palmer Prosacco", users.get(1).getName());
        assertEquals(1, users.get(1).getOrders().size());
    }

    @Test
    public void testProcessFileInvalidFormat() {
        MockMultipartFile file = new MockMultipartFile("file", "test-files/invalid.txt", "text/plain", "Invalid Content".getBytes());
        assertThrows(CustomException.class, () -> orderService.processFile(file));
    }
}
