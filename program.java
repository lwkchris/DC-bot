package event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
public class Translator extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        EmbedBuilder block = new EmbedBuilder();
        String title, msg, dialogue;

        switch (command) {
            case "e-hentai":
                OptionMapping str1 = event.getOption("神的語言");
                msg = str1.getAsString();
                title = GetTitle_eHentai_nhentai(msg, "ehentai");

                if(!title.equals("Not Found")){
                    dialogue = SendMessage(msg);
                }
                else {
                    dialogue = SendMessageNull();
                }

                block.setTitle(dialogue + "\n\n" + title);
                block.setColor(new Color(0x7C1515));
                block.setThumbnail("https://www.popo8.com/host/data/202110/06/12/p1633552048_743type_jpeg_size_225_100_end.jpg_b.jpg");
                String URL1 = "https://ehentai.to/g/" + msg;
                block.addField("URL:", URL1, true);
                block.setImage(GetCoverPage_eHentai_nhentai(msg,"ehentai"));

                event.replyEmbeds(block.build())
                        .setEphemeral(true).queue();
                break;

            case "nhentai":
                OptionMapping str2 = event.getOption("神的語言");
                msg = str2.getAsString();
                title = GetTitle_eHentai_nhentai(msg, "nhentai");

                if(!title.equals("Not Found")){
                    dialogue = SendMessage(msg);
                }
                else {
                    dialogue = SendMessageNull();
                }

                block.setTitle(dialogue + "\n\n" + title);
                block.setColor(new Color(0xFF315E));
                block.setThumbnail("https://archive.org/download/nhentai-logo-3/nhentai-logo-3.jpg");
                String URL2 = "https://nhentai.to/g/" + msg;
                block.addField("URL:", URL2, true);
                block.setImage(GetCoverPage_eHentai_nhentai(msg, "nhentai"));

                event.replyEmbeds(block.build())
                        .setEphemeral(true).queue();
                break;

            case "pixiv":
                OptionMapping str3 = event.getOption("神的語言");
                msg = str3.getAsString();

                block.setTitle(SendMessage(msg) + "\n\n" + "Pixiv");
                block.setColor(new Color(0x00ACFF));
                block.setThumbnail("https://www.easyatm.com.tw/img/6/245/n5GcuM3XyUTM5QDO2MTO4ATO2QTM5YzMxETOzQTNwAzMwIzLzkzLyYzLt92YucmbvRWdo5Cd0FmLxE2LvoDc0RHa.jpg");
                String URL3 = "https://www.pixiv.net/artworks/" + msg;
                block.addField("URL:", URL3, true);

                event.replyEmbeds(block.build())
                        .setEphemeral(true).queue();
                break;
        }
    }
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        OptionData op1 = new OptionData(OptionType.STRING, "神的語言", " ", true);
        commandData.add(Commands.slash("e-hentai", "E-Hentai傳送門")
                .addOptions(op1));

        OptionData op2 = new OptionData(OptionType.STRING, "神的語言", " ", true);
        commandData.add(Commands.slash("nhentai", "nHentai傳送門")
                .addOptions(op2));

        OptionData op3 = new OptionData(OptionType.STRING, "神的語言", " ", true);
        commandData.add(Commands.slash("pixiv", "Pixiv傳送門")
                .addOptions(op3));

        event.getGuild()
                .updateCommands()
                .addCommands(commandData).queue();
    }
    public String GetTitle_eHentai_nhentai(String code, String web) {
        try {
            String url = "https://" + web + ".to/g/" + code;
            Document doc = Jsoup.parse(new URL(url), 10000);
            Element content = doc.getElementById("bigcontainer")
                    .getElementById("info-block")
                    .getElementById("info");
            String title = content.getElementsByTag("h1").get(0).text();

            if(!title.equals("")) {
                title += "\n\n";
            }
            title += content.getElementsByTag("h2").get(0).text();

            return title;
        }
        catch (Exception e) {
            return "Not Found";
        }
    }
    public String GetCoverPage_eHentai_nhentai(String code, String web) {
        try {
            String url = "https://" + web + ".to/g/" + code + "/1";
            Document doc = Jsoup.parse(new URL(url), 10000);
            String pic = doc.getElementById("page-container")
                    .getElementById("image-container")
                    .getElementsByTag("img").get(0)
                    .attr("src");

            return pic;
        }
        catch (Exception e) {
            return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4sL0yEksBQCvRgm_47IrlR8cXSISNwOu6Zh5VUnUTLFm3ufYxdJoGRZOCl2gTp41ENqY&usqp=CAU";
        }
    }
    public String SendMessage(String code) {
        Random rand = new Random();
        int msg_code = rand.nextInt(3);
        String msg = "你看到這句就是出Bug了啦 \n error: position 1";

        switch (msg_code) {
            case 0:
                msg = "神，已降下啟示。";
                break;
            case 1:
                msg = "讓我們打開聖典，翻到第" + code + "頁進行禱告吧。";
                break;
            case 2:
                msg = "願你獲得自我安慰。";
                break;
        }

        return msg;
    }
    public String SendMessageNull() {
        Random rand = new Random();
        int msg_code = rand.nextInt(10);
        String msg = "你看到這句就是出Bug了啦 \n error: position 2";

        switch (msg_code){
            case 0:
            case 1:
            case 2:
                msg = "汝，偏離了神之性理。";
                break;
            case 3:
            case 4:
            case 5:
                msg = "迷途羔羊啊，吾等龐大胸懷等待著您。";
                break;
            case 6:
            case 7:
            case 8:
                msg = "此地的伊甸園之果尚未成熟。";
                break;
            case 9:
                msg = "LWK: 打錯號碼了啦。(PS: 這句只有10%機率出現";
                break;
        }
        return msg;
    }
}
