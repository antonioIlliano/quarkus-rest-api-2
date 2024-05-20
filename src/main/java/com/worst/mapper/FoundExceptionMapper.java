package com.worst.mapper;

import com.worst.dto.ErrorMessage;
import com.worst.error.FoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class FoundExceptionMapper implements ExceptionMapper<FoundException> {
    @Override
    public Response toResponse(FoundException e) {

        return Response.status(Response.Status.FOUND)
                .entity(ErrorMessage.builder()
                        .status(e.getStatus())
                        .message(e.getMessage())
                        .build())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
