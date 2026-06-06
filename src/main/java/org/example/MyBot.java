package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {

    // 👇 ADMIN ID SHU YERDA TURADI
    private final String ADMIN_ID = "8779857029";

    @Override
    public String getBotUsername() {
        return "kinolar_uz_rubot";
    }

    @Override
    public String getBotToken() {
        return "8367432620:AAGyEErCwX_reS8yQxGhlEYfOWIMI50Itf8";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String text = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            String fileId = VideoDB.getVideo(text);

            if (fileId != null) {

                SendVideo video = new SendVideo();
                video.setChatId(chatId);
                video.setVideo(new InputFile(fileId));

                try {
                    execute(video);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                SendMessage msg = new SendMessage();
                msg.setChatId(chatId);
                msg.setText("❌ Video topilmadi");

                try {
                    execute(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
