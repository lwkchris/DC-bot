import event.Translator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("token")
                .build();

        jda.addEventListener(new Translator());
    }
}
