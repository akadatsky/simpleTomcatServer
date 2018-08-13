package com.akadatsky.api;

import com.akadatsky.model.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class TestApi {

    private Gson gson = new GsonBuilder().create();

    @POST
    public Response processMessage(String inputJson) {
        String resultText;
        try {
            Message inputMessage = gson.fromJson(inputJson, Message.class);
            resultText = "Client send message: " + inputMessage.getText();
        } catch (Exception e) {
            resultText = "Can not decode message from client";
        }
        Message returnMessage = new Message(resultText);
        String resultJson = gson.toJson(returnMessage);
        return Response.status(Response.Status.OK).entity(resultJson).build();
    }

}
