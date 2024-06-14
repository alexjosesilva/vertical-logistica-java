package com.verticallogistica.service;

import com.verticallogistica.exception.CustomException;
import com.verticallogistica.model.Order;
import com.verticallogistica.model.Product;
import com.verticallogistica.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    public List<User> processFile(MultipartFile file)  {
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            String[] lines = content.split("\\r?\\n");

            Map<String, User> usersMap = new HashMap<>();

            for (String line : lines) {
                if (line.length() < 95) {
                    throw new CustomException("A linha não possui o comprimento esperado de 95 caracteres: " + line);
                }

                String userId = line.substring(0, 10).trim();
                String userName = line.substring(10, 55).trim();
                String orderId = line.substring(55, 65).trim();
                String productId = line.substring(65, 75).trim();
                String value = line.substring(75, 87).trim();
                String date = line.substring(87, 95).trim();

                User user = usersMap.getOrDefault(userId, new User(userId, userName, new ArrayList<>()));
                Order order = user.getOrders().stream().filter(o -> o.getOrderId().equals(orderId)).findFirst()
                        .orElse(new Order(orderId, 0.0, LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE), new ArrayList<>()));
                Product product = new Product(productId, Double.parseDouble(value));

                order.getProducts().add(product);
                order.setTotal(order.getTotal() + product.getValue());

                if (!user.getOrders().contains(order)) {
                    user.getOrders().add(order);
                }

                usersMap.put(userId, user);
            }

            return new ArrayList<>(usersMap.values());

        } catch (IOException e) {
            // Lidar com exceção de leitura do arquivo, se necessário
            throw new CustomException("Erro ao ler o conteúdo do arquivo");
        }
    }
}
