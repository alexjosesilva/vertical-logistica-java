package com.verticallogistica.controller;

import com.verticallogistica.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testUploadFileSuccess() throws Exception {
        String content = "0000000001                                      Zarelli00000001230000000111512.2420211201\n" +
                "0000000001                                      Zarelli00000001230000000122512.2420211201\n" +
                "0000000002                                     Medeiros00000123450000000111256.2420201201\n" +
                "0000000002                                     Medeiros0000012345000000012256.2420201201";
        MockMultipartFile file = new MockMultipartFile("file", "orders.txt", "text/plain", content.getBytes());

        when(orderService.processFile(file)).thenReturn(List.of());

        mockMvc.perform(multipart("/api/orders/upload")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    public void testUploadFileInvalid() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "", "text/plain", (byte[]) null);

        mockMvc.perform(multipart("/api/orders/upload")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }
}
