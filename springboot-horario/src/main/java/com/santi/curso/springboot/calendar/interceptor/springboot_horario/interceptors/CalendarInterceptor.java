package com.santi.curso.springboot.calendar.interceptor.springboot_horario.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close;

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour >= open && hour < close) {
            StringBuilder message = new StringBuilder("Bienvenidos al horario de atención a clientes");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append(" h");
            message.append(" hasta las ");
            message.append(close);
            message.append(" h.");
            message.append("Gracias por su visita!");
            request.setAttribute("message", message.toString());
            return true;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = new HashMap<>();
        StringBuilder message = new StringBuilder("Cerrado, fuera del horario de atención. ");
        message.append("Por favor, visítenos entre las ");
        message.append(open);
        message.append(" h");
        message.append(" y las ");
        message.append(close);
        message.append(" h. Gracias!");
        data.put("message", message.toString());
        data.put("date", new Date().toString());

        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(data));
        return false;
    }

    @SuppressWarnings("null")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
    }
}
