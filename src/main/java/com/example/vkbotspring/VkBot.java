package com.example.vkbotspring;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.groups.IsMember;
import api.longpoll.bots.methods.impl.messages.GetConversations;
import api.longpoll.bots.methods.impl.messages.GetInviteLink;
import api.longpoll.bots.model.events.likes.LikeEvent;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.events.messages.MessageTypingStateEvent;
import api.longpoll.bots.model.events.users.GroupJoinEvent;
import api.longpoll.bots.model.events.users.GroupLeaveEvent;
import api.longpoll.bots.model.events.wall.comments.WallReplyEvent;
import api.longpoll.bots.model.objects.additional.EventData;
import api.longpoll.bots.model.objects.basic.Message;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class VkBot extends LongPollBot {
  /*  public String[] censorList = { "куй", "кек", "лол", "кринж", "чел" };
    public String[] pinList = {"важно","срочно", "сбор", "@all"};

    public boolean pinCheck(String input){
        boolean pin=false;
        for (String splitMessage : input.split(" ")) {
            pin = Arrays.asList(pinList).contains(splitMessage.toLowerCase());
            if (pin) {
                break;
            }
        }
        return pin;
    }

    public boolean censorCheck(String inputMessage){
        boolean censor=false;
        for (String splitMessage : inputMessage.split(" ")) {
            censor = Arrays.asList(censorList).contains(splitMessage.toLowerCase());
            if (censor) {
                break;
            }
        }
        return censor;
    }*/

    @Override
    public void onMessageEvent(MessageEvent messageEvent) {
        System.out.println(messageEvent.getPayload().toString());
        if (messageEvent.getPayload().toString().equals("{\"1\":\"1\"}")){
            try {
                vkBotsApi.messages().sendEventAnswer()
                        .setUserId(messageEvent.getUserId())
                        .setPeerId(messageEvent.getPeerId())
                        .setEventId(messageEvent.getEventId())
                        .setEventData(new EventData.ShowSnackbar("TEST"))
                        .execute();
            } catch (VkApiException e) {e.printStackTrace();}
        }
        else if (messageEvent.getPayload().toString().equals("{\"2\":\"2\"}")){
            try {
                vkBotsApi.messages().send()
                        .setPeerId(messageEvent.getPeerId())
                        .setMessage("Here it is!")
                        .setKeyboard(Key.getSimpleKeyboard())
                        .execute();
            } catch (VkApiException e) {e.printStackTrace();}
        }
    }


    @Override
    public void onMessageNew(MessageNewEvent messageNewEvent) {
        Message message = messageNewEvent.getMessage();
        if (message.getText().contains("/n")) {
            try {vkBotsApi.messages()
                    .editChat()
                    .setChatId(message.getPeerId() - 2000000000)
                    .setTitle(message.getText().substring(3))
                    .execute();
            } catch (VkApiException e) {e.printStackTrace();}
        } else if (message.getText().equals("Privet")) {
            try {
                vkBotsApi.messages().send().setMessage("PRIIIIIIVET").setPeerId(message.getPeerId()).setKeyboard(Key.getSimpleKeyboard()).execute();
            } catch (VkApiException e) {
                throw new RuntimeException(e);
            }
        }
        /*else if ((censorCheck(message.getText()) | message.hasFwdMessages() | message.hasGeo() | message.hasAttachments() | message.isTemporaryMessage()) && message.getFromId() != 173385256) {
            try {vkBotsApi.messages().delete()
                        .setGroupId(getGroupId())
                        .setPeerId(message.getPeerId())
                        .setConversationMessageIds(message.getConversationMessageId())
                        .setDeleteForAll(true)
                        .execute();
                vkBotsApi.messages().send()
                        .setPeerId(message.getPeerId())
                        .setMessage("В чате запрещено использовать мат.")
                        .execute();
                vkBotsApi.messages().removeChatUser()
                        .setChatId(message.getPeerId() - 2000000000)
                        .setUserId(message.getFromId())
                        .execute();
            } catch (VkApiException e) {e.printStackTrace();}
        }*/
/*        else if (pinCheck(message.getText())) { //закрепить сообщение
            try {vkBotsApi.messages().pin()
                        .setConversationMessageId(message.getConversationMessageId())
                        .setPeerId(message.getPeerId())
                        .execute();
            } catch (VkApiException e) {e.printStackTrace();}
        }*/
        else if (message.getText().equals("Buttons")){
            try {vkBotsApi.messages().send()
                    .setPeerId(message.getPeerId())
                    .setMessage("a")
                    .setKeyboard(Key.getSimpleKeyboard())
                    .execute();
            } catch (VkApiException e) {e.printStackTrace();}
        }
        else if (!message.hasGeo()) {
            String keyCall = message.getText();
            System.out.println(keyCall);
            switch (keyCall) {
                case "Buttons":
                    try {vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage("a")
                            .setKeyboard(Key.getSimpleKeyboard())
                            .execute();
                    } catch (VkApiException e) {e.printStackTrace();}
                    break;
                case "Applications":
                    try {vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage("a")
                            .setKeyboard(Key.getAppKeyboard())
                            .execute();
                    } catch (VkApiException e) {e.printStackTrace();}
                    break;
                case "Links":
                    try {vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage("a")
                            .setKeyboard(Key.getLinkKeyboard())
                            .execute();
                    } catch (VkApiException e) {e.printStackTrace();}
                    break;
                case "CallBack":
                    try {vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage("a")
                            .setKeyboard(Key.callBackKeyboard())
                            .execute();
                    } catch (VkApiException e) {e.printStackTrace();}
                    break;
                case "Carousel":
                    try {vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage("a")
                            .setTemplate(Key.callCarouselKeyboard())
                            .execute();
                    } catch (VkApiException e) {e.printStackTrace();}
                    break;
                case "VkPay and location":
                    try {vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage("a")
                            .setKeyboard(Key.vkPayAndLocationKeyboard())
                            .execute();
                    } catch (VkApiException e) {e.printStackTrace();}
                    break;
            }
        }
        else if (message.hasGeo()){
            try {vkBotsApi.messages().send()
                    .setPeerId(message.getPeerId())
                    .setMessage(message.getGeo().getCoordinates().toString()+"\n"+
                            message.getGeo().getType()+"\n"+
                            message.getGeo().getPlace())
                    .execute();
            } catch (VkApiException e) {e.printStackTrace();}
        }
        else if (message.getText().toLowerCase().contains("/s")){
            String testMessage = message.getText().substring(3);
            try {
                GetConversations.Response response = vkBotsApi.messages().getConversations()
                        .setGroupId(getGroupId())
                        .execute();
                response.getResponseObject().getItems().forEach(
                        item ->
                        {
                            try {
                                vkBotsApi.messages().send()
                                        .setPeerId(item.getConversation().getPeer().getId())
                                        .setMessage(testMessage)
                                        .execute();
                            } catch (VkApiException e) {
                                e.printStackTrace();
                            }
                        }
                );
            } catch (VkApiException e) {e.printStackTrace();}
        }
/*        else if (message.getText().toLowerCase().equals("хочу в беседу")){
            try {
                IsMember.Response response = vkBotsApi.groups().isMember()
                        .setGroupId(getGroupId())
                        .setUserId(message.getFromId())
                        .execute();
                if (response.getResponseObject().toString().equals("true")) {
                    GetInviteLink.Response link = vkBotsApi.messages().getInviteLink()
                            .setPeerId(2000000013)
                            .setGroupId(getGroupId())
                            .execute();
                    vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage(link.getResponseObject().getLink())
                            .execute();}
                else {
                    vkBotsApi.messages().send()
                            .setPeerId(message.getPeerId())
                            .setMessage("Join Group")
                            .execute();}
            } catch (VkApiException e) {e.printStackTrace();}
        }*/
    }

/*    @Override
    public void onWallReplyNew(WallReplyEvent wallReplyEvent) {
        if (wallReplyEvent.getText().toLowerCase().equals("хочу в беседу")){
            try {
                IsMember.Response response = vkBotsApi.groups().isMember()
                        .setGroupId(getGroupId())
                        .setUserId(wallReplyEvent.getFromId())
                        .execute();
                if (response.getResponseObject().toString().equals("true")) {
                    GetInviteLink.Response link = vkBotsApi.messages().getInviteLink()
                            .setPeerId(2000000013)
                            .setGroupId(getGroupId())
                            .execute();
                    vkBotsApi.wall().createComment()
                            .setPostId(wallReplyEvent.getPostId())
                            .setReplyToComment(wallReplyEvent.getId())
                            .setOwnerId(-getGroupId())
                            .setMessage(link.getResponseObject().getLink())
                            .execute();}
                else {
                    vkBotsApi.wall().createComment()
                            .setPostId(wallReplyEvent.getPostId())
                            .setReplyToComment(wallReplyEvent.getId())
                            .setOwnerId(-getGroupId())
                            .setMessage("Вступи в группу")
                            .execute();}
            }
            catch (VkApiException e) {e.printStackTrace();}
        }
    }*/

    @Override
    public void onLikeAdd(LikeEvent likeEvent) {
        System.out.println(likeEvent);
    }

    @Override
    public void onLikeRemove(LikeEvent likeEvent) {
        System.out.println(likeEvent);
    }

    @Override
    public void onGroupLeave(GroupLeaveEvent groupLeaveEvent) {
        System.out.println(groupLeaveEvent);
    }

    @Override
    public void onGroupJoin(GroupJoinEvent groupJoinEvent) {
        System.out.println(groupJoinEvent);
    }

/*    @Override
    public void onMessageTypingState(MessageTypingStateEvent messageTypingStateEvent) {
        System.out.println(messageTypingStateEvent.getFromId());
        try {
            vkBotsApi.messages().send()
                    .setUserId(messageTypingStateEvent.getFromId())
                    .setMessage("Привет, сообщения используются только для рассылки новостей. Тебе здесь никто не ответит.")
                    .execute();
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public String getAccessToken() {
        return "vk1.a.-0L0z1u3Cbdo3OhNiBEhaV8hLx9YBKXIKTYy21yeaUUbBdY1p2pZMDALY-TeX1J7garKcJMQQ9BfLbHHOo5NrKE6kBwtOxgGp8CAg9FXl9SyiyyyuvC6TImRoK1WEJ2oItYr8Xfn9Bwk30TFdkMAxuxarf56DyD3PzwXOB5HlNW2CQgYxTSTafHkIvnlDFie0xUKi--YGba7DFKpWeNtuw";
    }
    @Override
    public int getGroupId() {
        return 162829564;
    }

/*    public static void main(String[] args) throws VkApiException {
        new BotsLongPoll(new VkBot()).run();
    }*/
}













